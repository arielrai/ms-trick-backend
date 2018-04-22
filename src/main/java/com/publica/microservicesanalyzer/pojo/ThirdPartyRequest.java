package com.publica.microservicesanalyzer.pojo;

/**
 * ThirdParty request
 * 
 * @author Ariel Rai Rodrigues(arielrairodrigues@gmail.com)
 *
 */
public class ThirdPartyRequest {

	// used for third party requests
	private String host;
	private String protocol;
	private String path;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
