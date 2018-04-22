package com.publica.microservicesanalyzer.mapping.app;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DataCenterInfo
{
    private String name;

    @JsonProperty("class")
    private String clazz;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

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
        return "ClassPojo [name = "+name+", class = "+clazz+"]";
    }
}