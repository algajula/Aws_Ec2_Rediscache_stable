package com.dlg.controler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.dlg.exception.DLGException;
import com.dlg.exception.ExceptionResponse;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

	private final static Logger log = LoggerFactory.getLogger(ExceptionHandlerControllerAdvice.class);
	
	@ExceptionHandler(DLGException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final DLGException exception,
			final HttpServletRequest request) {

		log.debug("handleResourceNotFound ERROR");
		ExceptionResponse error = new ExceptionResponse();
		error.setMsg(exception.getMessage());
		return error;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception,
			final HttpServletRequest request) {

		log.debug("handleException ERROR");
		ExceptionResponse error = new ExceptionResponse();
		error.setMsg(exception.getMessage());
		return error;
	}

}
