package com.domain.po.erWeiMa;

public class QRCode {
	int expire_seconds;
	String action_name;
	Action_info action_info;
	public int getExpire_seconds() {
		return expire_seconds;
	}
	public void setExpire_seconds(int expire_seconds) {
		this.expire_seconds = expire_seconds;
	}
	public String getAction_name() {
		return action_name;
	}
	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}
	public Action_info getAction_info() {
		return action_info;
	}
	public void setAction_info(Action_info action_info) {
		this.action_info = action_info;
	}
	
}
