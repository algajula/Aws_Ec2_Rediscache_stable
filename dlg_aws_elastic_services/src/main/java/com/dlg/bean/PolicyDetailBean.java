package com.dlg.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "policy", propOrder = {"policyId","policyName"})
public class PolicyDetailBean {

	@XmlElement(name="policyId", required=true)
	private String policyId;
	
	@XmlElement(name="policyName", required=true)
	private String policyName;
	
	public String getPolicyId() {
		return policyId;
	}
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	@Override
	public String toString() {
		return "PolicyDetailBean [policyId=" + policyId + ", policyName=" + policyName + "]";
	}
	
}
