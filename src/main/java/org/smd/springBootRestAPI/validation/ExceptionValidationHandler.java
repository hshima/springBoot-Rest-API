package org.smd.springBootRestAPI.validation;

import java.util.ArrayList;
import java.util.List;

import org.smd.springBootRestAPI.dto.FormErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionValidationHandler  {
	
	@Autowired
	MessageSource messageSource; // Injects an reference for a message interpretor that allows internationalization 

	@ResponseStatus(code = HttpStatus.BAD_REQUEST) // Informs kind of HTTP response that are sent
	@ExceptionHandler(MethodArgumentNotValidException.class) // Sets types that are dealt by the method
	public List<FormErrorDTO> handler(MethodArgumentNotValidException argumentException) {
		
		List<FormErrorDTO> dto = new ArrayList<>();
		List<FieldError> fieldErrors = argumentException.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach(e ->{
			String msg = messageSource.getMessage(e, LocaleContextHolder.getLocale()); // processes the Exception's message considering the Locale set in the HTTP Header request
			FormErrorDTO err = new FormErrorDTO(e.getField(), msg);
			dto.add(err);
			
		});
		
		return dto;
	}
}
