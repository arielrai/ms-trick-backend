package com.publica.microservicesanalyzer.pojo;

/**
 * 
 * @author Ariel Rai Rodrigues(arielrairodrigues@gmail.com)
 *
 */
public class RegisterPojoToken {

	private String device_token;
	private boolean on;

	public String getDevice_token() {
		return device_token;
	}

	public void setDevice_token(String device_token) {
		this.device_token = device_token;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}
}
