package com.satish.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleRunTimeException(RuntimeException ex){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error","Not found");
        errorResponse.put("message",ex.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<Map<String,Object>> handleResourceAlreadyExistException(RuntimeException ex){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.CONFLICT.value());
        errorResponse.put("error","Already exist");
        errorResponse.put("message",ex.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String,Object>> handleBadRequestException(RuntimeException ex){
        Map<String,Object> errorResponse=new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("error","Bad request");
        errorResponse.put("message",ex.getMessage());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}
