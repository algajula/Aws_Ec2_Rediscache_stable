package com.dlg.dao;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dlg.aws.AmazonBucketHelper;
import com.dlg.bean.PolicyDetailBean;
import com.dlg.exception.DLGException;

@Repository
//@Transactional
public class AWSDaoImpl implements AWSDao{

	private final static Logger log = LoggerFactory.getLogger(AWSDaoImpl.class);
	
	/*@Autowired
	private SessionFactory sessionFactory;*/
	
	@Autowired
	AmazonBucketHelper amazonBucketHelper; 
	
	@Override
	public List<PolicyDetailBean> gettransformbuckettorediscache() throws DLGException {
		List<PolicyDetailBean> policyList =new ArrayList<PolicyDetailBean>();
		try{
			log.debug("getPolicyList Service Start");
			
			policyList = amazonBucketHelper.readAwsfile();
			
			for(PolicyDetailBean policy: policyList){
				log.debug("Policy:::"+policy.getPolicyId());
			}
			log.debug("getPolicyList Service End==="+policyList.size());			
		}catch(Exception e){
			log.error("ERROR:::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}
		return policyList;
	}

	@Override
	public List<PolicyDetailBean> getcarwebclaimsById(String claimId) throws DLGException {
		List<PolicyDetailBean> policyList =new ArrayList<PolicyDetailBean>();
		try{
			log.debug("getPolicyList Service Start");
			
			policyList = amazonBucketHelper.readAwsfile();
			
			for(PolicyDetailBean policy: policyList){
				log.debug("Policy:::"+policy.getPolicyId());
			}
			log.debug("getPolicyList Service End==="+policyList.size());			
		}catch(Exception e){
			log.error("ERROR:::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}
		return policyList;
	}

	
}
