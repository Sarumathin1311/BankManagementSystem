package com.banking.exception;
import java.util.List;

public class ExceptionResponse 
{	
	private String message;
	private List<String> details;
	
	public ExceptionResponse(String message, List<String> details) 
	{
		super();
		this.message = message;
		this.details = details;
	}
	
	public void setMessage(String message) 
	{
		this.message = message;
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

