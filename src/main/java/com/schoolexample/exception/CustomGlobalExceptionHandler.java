package com.schoolexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;


@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    /*@ExceptionHandler(value = RecordNotFoundException.class)
    private ResponseEntity<Object> buildResponseEntity(CustomError customError) {
        customError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.badRequest().build();
    }
*/

}



