package com.dlg.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"policy","statusCode","msg"})
@XmlRootElement(name = "dlg_policy")
public class DlgRequestResponseBean {
	
	@XmlElement(name="policy", required=true)
	private List<PolicyDetailBean> policy;
	
	@XmlElement(name="statusCode", required=true)
	private String statusCode="03";

	@XmlElement(name="msg", required=true)
	private String msg="SUCCESS";
	
	public List<PolicyDetailBean> getPolicy() {
		return policy;
	}

	public void setPolicy(List<PolicyDetailBean> policy) {
		this.policy = policy;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "DlgRequestResponseBean [policy=" + policy + ", statusCode=" + statusCode + ", msg=" + msg + "]";
	}

	
}
