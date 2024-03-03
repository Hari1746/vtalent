package com.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//*Global Exception handling

@RestControllerAdvice
public class AppIrctcHandler 
{
@ExceptionHandler(value = IrctcException.class)
public ResponseEntity<ExceptionBean> handleIrctcException(IrctcException ex)
{
	String message = ex.getMessage();
	ExceptionBean eb = new ExceptionBean();
	eb.setCode("ERROR-XOXO");
	eb.setMsg(message);
	return new ResponseEntity<>(eb, HttpStatus.BAD_REQUEST);
}
}