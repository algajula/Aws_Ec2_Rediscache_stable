package com.dlg.dao;

import java.util.List;

import com.dlg.bean.PolicyDetailBean;
import com.dlg.exception.DLGException;

public interface AWSDao {

	public List<PolicyDetailBean> gettransformbuckettorediscache() throws DLGException;
	public List<PolicyDetailBean> getcarwebclaimsById(String claimId) throws DLGException;
}
