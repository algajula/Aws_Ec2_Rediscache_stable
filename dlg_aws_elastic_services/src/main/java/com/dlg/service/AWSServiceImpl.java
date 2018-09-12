package com.dlg.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlg.bean.PolicyDetailBean;
import com.dlg.dao.AWSDao;
import com.dlg.elastic.TransformBuckettoRedisCache;
import com.dlg.elastic.TransformrediscachetoClaims;
import com.dlg.exception.DLGException;

@Service
public class AWSServiceImpl implements AWSService{

	private final static Logger log = LoggerFactory.getLogger(AWSServiceImpl.class);
	
	@Autowired
	private AWSDao awsDao;
	
	@Autowired 
	TransformBuckettoRedisCache transformBuckettoRedisCache;
	
	@Autowired
	TransformrediscachetoClaims transformrediscachetoClaims;
	
	@Override
	public List<PolicyDetailBean> gettransformbuckettorediscache() throws DLGException {
		List<PolicyDetailBean> policyList =null;
		try{
			log.debug("getPolicyList Dao Start");
			policyList = awsDao.gettransformbuckettorediscache();
			
			transformBuckettoRedisCache.gettransformbuckettorediscache(policyList);
			
			log.debug("getPolicyList Dao End");			
		}catch(Exception e){
			log.error("ERROR:::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}	
		return policyList;
	}
	
	@Override
	public List<PolicyDetailBean> getcarwebclaimsById(String claimId) throws DLGException {
		List<PolicyDetailBean> policyList =null;
		try{
			log.debug("getPolicyList Dao Start");
			transformrediscachetoClaims.getcarwebclaimsById(claimId);
			
			log.debug("getPolicyList Dao End");			
		}catch(Exception e){
			log.error("ERROR:::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}	
		return policyList;
	}

}
