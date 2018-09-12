package com.dlg.controler;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dlg.bean.PolicyDetailBean;
import com.dlg.exception.DLGException;
import com.dlg.service.AWSService;

@Controller
public class AmazonBucketController {

	private static final Logger log = LoggerFactory.getLogger(AmazonBucketController.class);
	
	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";
	
	
	@Autowired
	private AWSService awsService;

	@RequestMapping(value="/getPolicyDetailsFromAwsbucket", method=RequestMethod.GET)
	public String getPolidyDetails(Map<String, Object> model) throws DLGException {
		try{
			log.debug("Hai getPolidyDetails");
			List<PolicyDetailBean> list = awsService.gettransformbuckettorediscache();
			log.debug("getPolidyDetails list:::"+list.size());
			model.put("message", list);
		}catch (Exception e) {
			log.error("getPolidyDetails Error:::"+e.getMessage());
			throw new DLGException(e.getMessage());
		}
		return "welcome";
	}

}

