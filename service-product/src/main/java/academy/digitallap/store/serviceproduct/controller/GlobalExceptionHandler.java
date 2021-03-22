package academy.digitallap.store.serviceproduct.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handlerException(ConstraintViolationException e) {
		
		final List<Object> errors = new ArrayList<Object>();
		
		e.getConstraintViolations().stream().forEach(fieldError -> {
			
			Map<String, Object> error = new HashMap<>();
			error.put("path", String.valueOf(fieldError.getPropertyPath()));
			error.put("messgae", fieldError.getMessage());
			errors.add(error);
			
		});
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		Map<String, Object> body = new HashMap<>();
		body.put("error", errors);
		return new ResponseEntity<>(body, httpStatus);
		
	}
	
}
