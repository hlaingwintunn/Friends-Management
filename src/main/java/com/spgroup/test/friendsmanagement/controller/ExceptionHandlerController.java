package com.spgroup.test.friendsmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.spgroup.test.friendsmanagement.common.response.ResponseFriends;
import com.spgroup.test.friendsmanagement.service.FriendException;

@ControllerAdvice
public class ExceptionHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);
	private MessageSource messageSource;
	
	@Autowired
	public ExceptionHandlerController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	  @ExceptionHandler(MethodArgumentNotValidException.class)
	  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public ResponseFriends handleValidationException(MethodArgumentNotValidException ex) {
	    List<String> errorMessages =
	        ex.getBindingResult().getFieldErrors().stream()
	            .map(fe -> fe.getField() + " " + fe.getDefaultMessage())
	            .collect(Collectors.toList());

	    return new ResponseFriends().setSuccess(false).setErrorMessage(errorMessages);
	  }
	  
	  @ExceptionHandler(FriendException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public ResponseFriends friendException(FriendException ex) {
		  
		  List<String> errorMessage = new ArrayList<>();
		  errorMessage.add(messageSource.getMessage(ex.getMessage(), null, Locale.getDefault()));
		  return new ResponseFriends().setSuccess(false).setErrorMessage(errorMessage);

	  }
	  
	  @ExceptionHandler(TransactionSystemException.class)
	  @ResponseStatus(HttpStatus.BAD_REQUEST)
	  @ResponseBody
	  public ResponseFriends transactionSystemException(TransactionSystemException ex) {
	    logger.error(ex.getMessage(), ex);
	    List<String> errorMsg = null;
	    if (ex.getRootCause() instanceof ConstraintViolationException) {
	      errorMsg =
	          ((ConstraintViolationException) ex.getRootCause()).getConstraintViolations().stream()
	              .map(ConstraintViolation::getMessage)
	              .collect(Collectors.toList());
	    } else {
	    	List<String> mesList = new ArrayList<>();
	    	mesList.add(ex.getRootCause().getMessage());
	      errorMsg = mesList;
	    }
	    return new ResponseFriends().setSuccess(false).setErrorMessage(errorMsg);
	  }
}
