package com.dlg.service;

import java.util.List;

import com.dlg.bean.PolicyDetailBean;
import com.dlg.exception.DLGException;

public interface AWSService {

	public List<PolicyDetailBean> gettransformbuckettorediscache() throws DLGException;
	public List<PolicyDetailBean> getcarwebclaimsById(String claimId) throws DLGException;
}
