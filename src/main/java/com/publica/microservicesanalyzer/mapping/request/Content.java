package com.publica.microservicesanalyzer.mapping.request;
                 
public class Content
{
    private String timestamp;

    private Info info;

    public String getTimestamp ()
    {
        return timestamp;
    }

    public void setTimestamp (String timestamp)
    {
        this.timestamp = timestamp;
    }

    public Info getInfo ()
    {
        return info;
    }

    public void setInfo (Info info)
    {
        this.info = info;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [timestamp = "+timestamp+", info = "+info+"]";
    }
}