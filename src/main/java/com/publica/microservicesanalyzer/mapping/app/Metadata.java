package com.publica.microservicesanalyzer.mapping.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Metadata {

	@JsonProperty("class")
    private String clazz;

    public String getClazz ()
    {
        return clazz;
    }

	public void setClazz (String clazz)
    {
        this.clazz = clazz;
    }

	@Override
    public String toString()
    {
        return "ClassPojo [class = "+clazz+"]";
    }
}