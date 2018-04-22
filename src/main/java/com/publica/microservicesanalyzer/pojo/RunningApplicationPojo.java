package com.publica.microservicesanalyzer.pojo;

/**
 * Representa uma aplicação executando
 * @author Ariel Rai Rodrigues(arielrairodrigues@gmail.com)
 *
 */
public class RunningApplicationPojo {

	private String applicationName;
	private String instanceId;
	private String host;
	private String ipAdress;
	private String upSince;

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public String getUpSince() {
		return upSince;
	}

	public void setUpSince(String upSince) {
		this.upSince = upSince;
	}

}
