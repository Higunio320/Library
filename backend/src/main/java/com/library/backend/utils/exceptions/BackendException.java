package com.library.backend.utils.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@NoArgsConstructor
@Getter
public  class BackendException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 2756597416924085674L;

    private HttpStatus status = HttpStatus.BAD_REQUEST;

    public BackendException(String message) {
        super(message);
    }

    public BackendException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
