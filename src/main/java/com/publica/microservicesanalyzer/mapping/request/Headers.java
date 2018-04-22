package com.publica.microservicesanalyzer.mapping.request;

import java.util.Map;

public class Headers
{
    private Map response;

    private Map request;

    public Map getResponse ()
    {
        return response;
    }

    public void setResponse(Map response) {
		this.response = response;
	}

    public Map getRequest() {
		return request;
	}
    
    public void setRequest(Map request) {
		this.request = request;
	}
    

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", request = "+request+"]";
    }
}