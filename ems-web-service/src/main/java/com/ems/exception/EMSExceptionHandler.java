package com.ems.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class EMSExceptionHandler extends ResponseEntityExceptionHandler {
	private static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";
	private static final String NOT_FOUND = "NOT_FOUND";

	@ExceptionHandler(EMSException.class)
	public final ApplicationErrorResponse handleApplicationException(EMSException eMSException) {

		ErrorStatus errorStatus = new ErrorStatus(HttpStatus.INTERNAL_SERVER_ERROR, ServerityCode.Error,
				new ErrorDescriptionStatus(500, INTERNAL_SERVER_ERROR, DescrptionErrorServerity.Error,
						eMSException.getLocalizedMessage()));

		ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse();
		applicationErrorResponse.setErrorStatus(errorStatus);
		return applicationErrorResponse;
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public final ApplicationErrorResponse resourceNotFoundException(ResourceNotFoundException ex) {
		/*
		 * ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
		 * request.getDescription(false)); return new ResponseEntity<>(errorDetails,
		 * HttpStatus.NOT_FOUND);
		 */
		ErrorStatus errorStatus = new ErrorStatus(HttpStatus.NOT_FOUND, ServerityCode.Error,
				new ErrorDescriptionStatus(404, NOT_FOUND, DescrptionErrorServerity.Error,
						ex.getLocalizedMessage()));

		ApplicationErrorResponse applicationErrorResponse = new ApplicationErrorResponse();
		applicationErrorResponse.setErrorStatus(errorStatus);
		return applicationErrorResponse;
	}

	/*
	 * @ExceptionHandler(Exception.class) public final ResponseEntity<?>
	 * globleExcpetionHandler(Exception ex, WebRequest request) { ErrorDetails
	 * errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	 * request.getDescription(false)); return new ResponseEntity<>(errorDetails,
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
}