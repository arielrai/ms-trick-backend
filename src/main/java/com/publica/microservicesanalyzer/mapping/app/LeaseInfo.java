package com.publica.microservicesanalyzer.mapping.app;
public class LeaseInfo
{
    private String registrationTimestamp;

    private String durationInSecs;

    private String lastRenewalTimestamp;

    private String serviceUpTimestamp;

    private String evictionTimestamp;

    private String renewalIntervalInSecs;

    public String getRegistrationTimestamp ()
    {
        return registrationTimestamp;
    }

    public void setRegistrationTimestamp (String registrationTimestamp)
    {
        this.registrationTimestamp = registrationTimestamp;
    }

    public String getDurationInSecs ()
    {
        return durationInSecs;
    }

    public void setDurationInSecs (String durationInSecs)
    {
        this.durationInSecs = durationInSecs;
    }

    public String getLastRenewalTimestamp ()
    {
        return lastRenewalTimestamp;
    }

    public void setLastRenewalTimestamp (String lastRenewalTimestamp)
    {
        this.lastRenewalTimestamp = lastRenewalTimestamp;
    }

    public String getServiceUpTimestamp ()
    {
        return serviceUpTimestamp;
    }

    public void setServiceUpTimestamp (String serviceUpTimestamp)
    {
        this.serviceUpTimestamp = serviceUpTimestamp;
    }

    public String getEvictionTimestamp ()
    {
        return evictionTimestamp;
    }

    public void setEvictionTimestamp (String evictionTimestamp)
    {
        this.evictionTimestamp = evictionTimestamp;
    }

    public String getRenewalIntervalInSecs ()
    {
        return renewalIntervalInSecs;
    }

    public void setRenewalIntervalInSecs (String renewalIntervalInSecs)
    {
        this.renewalIntervalInSecs = renewalIntervalInSecs;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [registrationTimestamp = "+registrationTimestamp+", durationInSecs = "+durationInSecs+", lastRenewalTimestamp = "+lastRenewalTimestamp+", serviceUpTimestamp = "+serviceUpTimestamp+", evictionTimestamp = "+evictionTimestamp+", renewalIntervalInSecs = "+renewalIntervalInSecs+"]";
    }
}