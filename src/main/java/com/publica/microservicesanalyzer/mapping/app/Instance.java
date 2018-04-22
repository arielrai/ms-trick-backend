package com.publica.microservicesanalyzer.mapping.app;

public class Instance {
	private Port port;

	private String countryId;

	private String statusPageUrl;

	private String app;

	private String ipAddr;

	private SecurePort securePort;

	private String overriddenstatus;

	private String status;

	private String lastUpdatedTimestamp;

	private String homePageUrl;

	private String instanceId;

	private String actionType;

	private DataCenterInfo dataCenterInfo;

	private String vipAddress;

	private String isCoordinatingDiscoveryServer;

	private String lastDirtyTimestamp;

	private String secureVipAddress;

	private String hostName;

	private LeaseInfo leaseInfo;

	private String healthCheckUrl;

	private Metadata metadata;

	public Port getPort() {
		return port;
	}

	public void setPort(Port port) {
		this.port = port;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getStatusPageUrl() {
		return statusPageUrl;
	}

	public void setStatusPageUrl(String statusPageUrl) {
		this.statusPageUrl = statusPageUrl;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public SecurePort getSecurePort() {
		return securePort;
	}

	public void setSecurePort(SecurePort securePort) {
		this.securePort = securePort;
	}

	public String getOverriddenstatus() {
		return overriddenstatus;
	}

	public void setOverriddenstatus(String overriddenstatus) {
		this.overriddenstatus = overriddenstatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(String lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public String getHomePageUrl() {
		return homePageUrl;
	}

	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public DataCenterInfo getDataCenterInfo() {
		return dataCenterInfo;
	}

	public void setDataCenterInfo(DataCenterInfo dataCenterInfo) {
		this.dataCenterInfo = dataCenterInfo;
	}

	public String getVipAddress() {
		return vipAddress;
	}

	public void setVipAddress(String vipAddress) {
		this.vipAddress = vipAddress;
	}

	public String getIsCoordinatingDiscoveryServer() {
		return isCoordinatingDiscoveryServer;
	}

	public void setIsCoordinatingDiscoveryServer(String isCoordinatingDiscoveryServer) {
		this.isCoordinatingDiscoveryServer = isCoordinatingDiscoveryServer;
	}

	public String getLastDirtyTimestamp() {
		return lastDirtyTimestamp;
	}

	public void setLastDirtyTimestamp(String lastDirtyTimestamp) {
		this.lastDirtyTimestamp = lastDirtyTimestamp;
	}

	public String getSecureVipAddress() {
		return secureVipAddress;
	}

	public void setSecureVipAddress(String secureVipAddress) {
		this.secureVipAddress = secureVipAddress;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public LeaseInfo getLeaseInfo() {
		return leaseInfo;
	}

	public void setLeaseInfo(LeaseInfo leaseInfo) {
		this.leaseInfo = leaseInfo;
	}

	public String getHealthCheckUrl() {
		return healthCheckUrl;
	}

	public void setHealthCheckUrl(String healthCheckUrl) {
		this.healthCheckUrl = healthCheckUrl;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public String toString() {
		return "ClassPojo [port = " + port + ", countryId = " + countryId + ", statusPageUrl = " + statusPageUrl
				+ ", app = " + app + ", ipAddr = " + ipAddr + ", securePort = " + securePort + ", overriddenstatus = "
				+ overriddenstatus + ", status = " + status + ", lastUpdatedTimestamp = " + lastUpdatedTimestamp
				+ ", homePageUrl = " + homePageUrl + ", instanceId = " + instanceId + ", actionType = " + actionType
				+ ", dataCenterInfo = " + dataCenterInfo + ", vipAddress = " + vipAddress
				+ ", isCoordinatingDiscoveryServer = " + isCoordinatingDiscoveryServer + ", lastDirtyTimestamp = "
				+ lastDirtyTimestamp + ", secureVipAddress = " + secureVipAddress + ", hostName = " + hostName
				+ ", leaseInfo = " + leaseInfo + ", healthCheckUrl = " + healthCheckUrl + ", metadata = " + metadata
				+ "]";
	}
}