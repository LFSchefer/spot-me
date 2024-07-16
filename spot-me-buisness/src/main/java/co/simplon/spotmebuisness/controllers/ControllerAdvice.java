package co.simplon.spotmebuisness.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
	    HttpHeaders headers, HttpStatusCode status, WebRequest request) {
	List<FieldError> error = ex.getFieldErrors();
	List<ObjectError> globalErrors = ex.getGlobalErrors();
	List<CustomFieldError> customFieldErrors = mapErrors(error, null);
	List<CustomFieldError> customGlobalErrors = mapErrors(null, globalErrors);
	HashMap<String, Object> errors = new HashMap<String, Object>();
	Object globalError = handleExceptionInternal(ex, customGlobalErrors, headers, status, request).getBody();
	Object fieldError = handleExceptionInternal(ex, customFieldErrors, headers, status, request).getBody();
	errors.put("globalError", globalError);
	errors.put("fieldError", fieldError);

	return ResponseEntity.status(status).body(errors);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
	    HttpStatusCode statusCode, WebRequest request) {
	return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }

    private List<CustomFieldError> mapErrors(List<FieldError> fieldError, List<ObjectError> globalErrors) {
	if (fieldError != null && globalErrors == null) {
	    List<CustomFieldError> customErrors = new ArrayList<>();
	    for (FieldError fieldErrors : fieldError) {
		String fieldName = fieldErrors.getField();
		String fieldCode = fieldErrors.getCode();
		CustomFieldError customFieldError = new CustomFieldError(fieldName, fieldCode);
		customErrors.add(customFieldError);
	    }
	    return customErrors;
	} else if (fieldError == null && globalErrors != null) {
	    List<CustomFieldError> customErrors = new ArrayList<>();
	    for (ObjectError fieldErrors : globalErrors) {
		String fieldName = fieldErrors.getDefaultMessage();
		String fieldCode = fieldErrors.getCode();
		CustomFieldError customFieldError = new CustomFieldError(fieldName, fieldCode);
		customErrors.add(customFieldError);
	    }
	    return customErrors;
	}
	return null;
    }
}
