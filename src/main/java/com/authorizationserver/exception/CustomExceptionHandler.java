package com.authorizationserver.exception;

import com.authorizationserver.util.MethodUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {


    @ExceptionHandler(value=ApplicationException.class)
    public ResponseEntity<String> productNotFoundExceptionException(ApplicationException exception){
        HttpStatus status=HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(MethodUtils.prepareErrorJSON(status,exception),status);
    }


}
