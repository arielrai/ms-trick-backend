package com.publica.microservicesanalyzer.mapping.app;

public class EurekaApps
{
    private Applications applications;

    public Applications getApplications ()
    {
        return applications;
    }

    public void setApplications (Applications applications)
    {
        this.applications = applications;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [applications = "+applications+"]";
    }
}
	