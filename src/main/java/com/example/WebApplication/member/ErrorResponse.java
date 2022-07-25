package com.example.WebApplication.member;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "error")
public class ErrorResponse
{
    private String message;
    private List<String> fields;
    private List<String> details;

    public ErrorResponse(String message, List<String> fields, List<String> details)
    {
        super();
        this.message = message;
        this.fields = fields;
        this.details = details;
    }

    public ErrorResponse(String message, List<String> details)
    {
        this.message = message;
        this.details = details;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<String> getField()
    {
        return fields;
    }

    public void setField(List<String> fields)
    {
        this.fields = fields;
    }

    public List<String> getDetails()
    {
        return details;
    }

    public void setDetails(List<String> details)
    {
        this.details = details;
    }
}
