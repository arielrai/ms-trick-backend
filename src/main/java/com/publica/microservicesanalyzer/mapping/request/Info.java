package com.publica.microservicesanalyzer.mapping.request;
                 
public class Info
{
    private Headers headers;

    private String timeTaken;

    private String path;

    private String method;

    public Headers getHeaders ()
    {
        return headers;
    }

    public void setHeaders (Headers headers)
    {
        this.headers = headers;
    }

    public String getTimeTaken ()
    {
        return timeTaken;
    }

    public void setTimeTaken (String timeTaken)
    {
        this.timeTaken = timeTaken;
    }

    public String getPath ()
    {
        return path;
    }

    public void setPath (String path)
    {
        this.path = path;
    }

    public String getMethod ()
    {
        return method;
    }

    public void setMethod (String method)
    {
        this.method = method;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [headers = "+headers+", timeTaken = "+timeTaken+", path = "+path+", method = "+method+"]";
    }
}