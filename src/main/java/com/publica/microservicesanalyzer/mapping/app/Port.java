package com.publica.microservicesanalyzer.mapping.app;
public class Port
{
    private String content;

    private String enabled;

    public String getContent ()
    {
        return content;
    }

    public void setContent (String content)
    {
        this.content = content;
    }

    public String getEnabled ()
    {
        return enabled;
    }

    public void setEnabled (String enabled)
    {
        this.enabled = enabled;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [content = "+content+", enabled = "+enabled+"]";
    }
}