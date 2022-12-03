package com.example.demo.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@RestControllerAdvice
public class ExceptionControllerAdvice {

    @Autowired
    Environment environment;

    public ResponseEntity<ErrorInfo> exceptionHandler(Exception e){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSGE"));
        errorInfo.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorInfo.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecipeException.class)
    public ResponseEntity<ErrorInfo> recpiseExceptionHandler(RecipeException exception){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorMessage(environment.getProperty(exception.getMessage()));
        errorInfo.setTimeStamp(LocalDateTime.now());
        errorInfo.setErrorCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorInfo>(errorInfo,HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> exceptionHandler(MethodArgumentNotValidException exception){
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrorCode(HttpStatus.BAD_REQUEST.value());
        String errorMessage = exception.getBindingResult().getAllErrors().stream().map(e -> e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        errorInfo.setErrorMessage(errorMessage);
        errorInfo.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

}
