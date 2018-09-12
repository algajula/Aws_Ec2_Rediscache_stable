package com.dlg.aws;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dlg.bean.PolicyDetailBean;
import com.dlg.exception.DLGException;

@Component
public class AmazonBucketEventCreator {

	private static final Logger log = LoggerFactory.getLogger(AmazonBucketEventCreator.class);
    
	@Autowired
	AmazonBucketHelper amazonBucketHelper;
    
    @Scheduled(cron = "0 15 00 * * ?")
    public void create() throws DLGException{
    	List<PolicyDetailBean> policyList =new ArrayList<PolicyDetailBean>();
    	try{
    		log.debug("Event Creation Start:::");
    		policyList = amazonBucketHelper.readAwsfile();
    		for(PolicyDetailBean policy: policyList){
    			log.debug("Policy:::"+policy.getPolicyId());
    		}
    		log.debug("Event Creation End:::");
    	}catch (Exception e) {
			log.error("Event Creation Error:::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}
    }
}
