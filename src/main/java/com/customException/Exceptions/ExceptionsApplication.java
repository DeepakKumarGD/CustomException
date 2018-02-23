package com.customException.Exceptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ExceptionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionsApplication.class, args);
	}

	@RequestMapping("/api")
	public String exceptionApi() throws CustomException{
		throw new CustomException();
	}

	private class CustomException extends Exception {
		private static final long serialVersionUID = 1L;

		CustomException(){
			super("Deepak has thrown this exception");
		}
	}

	@ControllerAdvice
	private class ExceptionController{
		@ExceptionHandler(value = CustomException.class)
		public ResponseEntity<Object> customExceptionHandler(CustomException exception){
			return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}