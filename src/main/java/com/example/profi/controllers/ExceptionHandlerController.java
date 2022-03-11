package com.example.profi.controllers;

import com.example.profi.exceptions.RequestException;
import com.example.profi.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(RequestException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> errorRequestHandling(RequestException e) {
        return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.getCode(), e.getMessage()));
    }
}
