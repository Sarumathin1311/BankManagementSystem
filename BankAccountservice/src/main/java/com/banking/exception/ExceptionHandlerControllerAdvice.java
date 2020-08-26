package com.banking.exception;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Controller
@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler 
{
	public ExceptionHandlerControllerAdvice() 
	{
		
	}
	
	@ExceptionHandler(BankManagementException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(BankManagementException ex, WebRequest request) 
	{
		List<String> account = new ArrayList<>();
		account.add(ex.getLocalizedMessage());
		ExceptionResponse error = new ExceptionResponse("Something went wrong", account);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) 
	{
		List<String> account = new ArrayList<>();
		account.add(ex.getLocalizedMessage());
		ExceptionResponse error = new ExceptionResponse("Something went wrong", account);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	HttpHeaders headers, HttpStatus status, WebRequest request)
	{
		List<String> account = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) 
		{
			account.add(error.getDefaultMessage());
	    }
		ExceptionResponse error = new ExceptionResponse("Validation Failed", account);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}

