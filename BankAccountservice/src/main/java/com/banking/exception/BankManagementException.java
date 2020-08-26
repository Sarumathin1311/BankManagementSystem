package com.banking.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BankManagementException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public BankManagementException(String exception) 
		{
			super(exception);
	    }
}


