package com.publica.microservicesanalyzer.mapping.app;

import java.util.List;

public class Application
{
    private String name;

    private List<Instance> instance;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public List<Instance> getInstance() {
		return instance;
	}
    
    public void setInstance(List<Instance> instance) {
		this.instance = instance;
	}

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", instance = "+instance+"]";
    }
}