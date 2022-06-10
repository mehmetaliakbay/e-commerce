package com.difransel.ecommerce.controller.advice;

import com.difransel.ecommerce.exception.ProductNotFoundException;
import com.difransel.ecommerce.model.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Generic exception occurred.", exception);
        ErrorResponse errorResponse = new ErrorResponse(
                "GenericException",
                System.currentTimeMillis(),
                Collections.singletonList("Generic exception occurred."),
                Collections.emptyMap()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ProductNotFoundException exception) {
        log.warn(exception.getMessage(), exception);
        ErrorResponse errorResponse = new ErrorResponse(
                "ProductNotFoundException",
                System.currentTimeMillis(),
                Collections.singletonList(exception.getMessage()),
                Collections.emptyMap()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
