package com.dlg.aws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.dlg.bean.PolicyDetailBean;
import com.dlg.exception.DLGException;
import com.dlg.util.AWSConstants;

@Component
public class AmazonBucketHelper {

	private final static Logger log = LoggerFactory.getLogger(AmazonBucketHelper.class);
	
	public List<PolicyDetailBean> readAwsfile() throws DLGException {
		List<PolicyDetailBean> list  = new ArrayList<PolicyDetailBean>();
		S3Object objectPortion = null;
		try{
			AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(Regions.US_EAST_1)
                    .withCredentials(new ClasspathPropertiesFileCredentialsProvider())
                    .build();

            // Get a range of bytes from an object and print the bytes.
            GetObjectRequest rangeObjectRequest = new GetObjectRequest(AWSConstants.bucketName, "dlgproduct.csv");
            objectPortion = s3Client.getObject(rangeObjectRequest);
            log.debug("Printing bytes retrieved.");
            list = displayTextInputStream(objectPortion.getObjectContent());
            return list;
		}catch(IOException e){
			log.error("ERROR:::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}catch(AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process 
            // it, so it returned an error response.
			log.error("ERROR:::"+e.getMessage());
			throw new DLGException(e.getMessage());
        }catch(SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
        	log.error("ERROR:::"+e.getMessage());
			throw new DLGException(e.getMessage());
        }finally {
            // To ensure that the network connection doesn't remain open, close any open input streams.
            if(objectPortion != null) {
                try {
					objectPortion.close();
				} catch (IOException e) {
					log.debug("ERROR:::"+e.getMessage());
					throw new DLGException(e.getMessage());
				}
            }
        }
	}
	
	private static List<PolicyDetailBean> displayTextInputStream(InputStream input) throws IOException {
		List<PolicyDetailBean> list  = new ArrayList<PolicyDetailBean>();
        // Read the text input stream one line at a time and display each line.
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line = null;
        while ((line = reader.readLine()) != null) {
        	PolicyDetailBean policy=new PolicyDetailBean();
        	String[] feilds = line.split("\\|",-1);
            log.debug(line);
            policy.setPolicyId(feilds[0]);
            policy.setPolicyName(feilds[1]);
            list.add(policy);
        }
        log.debug("displayTextInputStream End");
        return list;
    }
	
	public static AWSCredentials getAWSCredentials(){
		AWSCredentials credentials=new AWSCredentials() {
			@Override
			public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return AWSConstants.secretkey;
			}
			
			@Override
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return AWSConstants.accesskey;
			}
		};
		return credentials;
	}
	
}
