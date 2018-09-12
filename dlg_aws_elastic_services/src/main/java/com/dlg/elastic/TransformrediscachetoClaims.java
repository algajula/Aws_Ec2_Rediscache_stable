package com.dlg.elastic;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.elasticache.AmazonElastiCacheClient;
import com.amazonaws.services.elasticache.model.CacheCluster;
import com.amazonaws.services.elasticache.model.CacheNode;
import com.amazonaws.services.elasticache.model.DescribeCacheClustersRequest;
import com.amazonaws.services.elasticache.model.DescribeCacheClustersResult;
import com.dlg.aws.AmazonBucketHelper;
import com.dlg.bean.PolicyDetailBean;

import redis.clients.jedis.Jedis;

@Component
public class TransformrediscachetoClaims {
	
	private final static Logger log = LoggerFactory.getLogger(TransformrediscachetoClaims.class);
	
	@Autowired
	AmazonBucketHelper amazonBucketHelper;
	
	public void getcarwebclaimsById(String claimId)throws Exception{
		try{
			log.debug("getElastiCacheNodes Start:::");
			AWSCredentials credentials = amazonBucketHelper.getAWSCredentials();
			
			AmazonElastiCacheClient client = new AmazonElastiCacheClient(credentials);
	        log.debug("Got client, client.getEndpointPrefix() = " + client.getEndpointPrefix());
	        client.setRegion(Region.getRegion(Regions.US_EAST_1));
	     
	        log.debug("setEndpoint passed.");
	        DescribeCacheClustersRequest dccRequest = new DescribeCacheClustersRequest();
	        dccRequest.setShowCacheNodeInfo(true);

	        log.debug("About to call describeCacheClusters() now");
	        DescribeCacheClustersResult clusterResult = client.describeCacheClusters(dccRequest);
	        log.debug("got clusterResult.");

	        log.debug("CacheEngineVersions: " + client.describeCacheEngineVersions());

	        List<CacheCluster> cacheClusters = clusterResult.getCacheClusters();
	        log.debug("About to enter for loop now, cacheClusters.size() = " + cacheClusters.size());
	        for (CacheCluster cacheCluster : cacheClusters) {
	            log.debug("In for loop now.");
	            for (CacheNode cacheNode : cacheCluster.getCacheNodes()) {
	                log.debug("In inner for loop now.");
	                log.debug(cacheNode.toString());
	                String addr = cacheNode.getEndpoint().getAddress();
//					if (!addr.startsWith("hermes-dev")) continue;
	                int port = cacheNode.getEndpoint().getPort();
	                String url =  addr + ":" + port;
	                log.debug("formed url is: " + url);

	                Jedis jedis = new Jedis(addr,port);
	                log.debug("Connection to server sucessfully");
	                
	                log.debug("KEY GET IN REDIS CACHE " + jedis.ping());
            		log.debug("OUTPUT SUCCESS"+jedis.get(claimId));            		
            		
	                // check whether server is running or not
	                log.debug("Server is running: " + jedis.ping());
					log.debug("Server is running: " + jedis.clusterInfo());
	            }
	        }
			log.debug("getElastiCacheNodes End:::");
		}catch (Exception e) {
			log.error("getElastiCacheNodes Error."+e.getMessage());
		}
	}
  
}