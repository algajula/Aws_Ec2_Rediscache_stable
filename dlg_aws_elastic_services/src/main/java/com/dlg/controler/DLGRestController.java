package com.dlg.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dlg.bean.DlgRequestResponseBean;
import com.dlg.bean.PolicyDetailBean;
import com.dlg.exception.DLGException;
import com.dlg.service.AWSService;

@RestController
@RequestMapping("/carwebservices")
public class DLGRestController {

	private final static Logger log = LoggerFactory.getLogger(DLGRestController.class);
	
	@Autowired
	AWSService awsService;
	
	@RequestMapping(value="/vehical/gettransformbuckettorediscache", produces="application/xml", method=RequestMethod.GET)
	@ResponseBody
	public DlgRequestResponseBean Gettransformbuckettorediscache() throws DLGException{
		DlgRequestResponseBean dlgReq=new DlgRequestResponseBean();
		try{
			log.debug("Gettransformbuckettorediscache Start::");
			List<PolicyDetailBean> policyList=awsService.gettransformbuckettorediscache();
			dlgReq.setPolicy(policyList);
			log.debug("Gettransformbuckettorediscache End::");
		}catch (Exception e) {
			log.error("Gettransformbuckettorediscache Error::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}
		return dlgReq;
	}
    
	@RequestMapping(value="/vehical/claimdetailsbyid", consumes="application/json", produces="application/json", method=RequestMethod.POST)
	@ResponseBody
	public DlgRequestResponseBean GetcarwebclaimsById(@RequestBody PolicyDetailBean policy) throws DLGException{
		DlgRequestResponseBean dlgReq=new DlgRequestResponseBean();
		try{
			log.debug("GetcarwebclaimsById Start::"+policy.getPolicyId());
			List<PolicyDetailBean> policyList=awsService.getcarwebclaimsById(policy.getPolicyId());
			dlgReq.setPolicy(policyList);
			log.debug("GetcarwebclaimsById End::"+policy.getPolicyId());
		}catch (Exception e) {
			log.error("GetcarwebclaimsById Error::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}
		return dlgReq;
	}
}