package com.library.backend.utils.exceptions;


import com.library.backend.utils.exceptions.data.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class BackendExceptionHandler {

    @ExceptionHandler(BackendException.class)
    public final ResponseEntity<ExceptionResponse> handleBackendException(BackendException exception) {

        String message = exception.getMessage();
        String exceptionName = exception.getClass().getSimpleName();
        HttpStatus status = exception.getStatus();
        LocalDateTime timeStamp = LocalDateTime.now();

        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .exceptionName(exceptionName)
                .message(message)
                .timeStamp(timeStamp)
                .build();

        log.error("Exception occurred with response: {}", exceptionResponse);

        return new ResponseEntity<>(exceptionResponse, status);
    }
}
