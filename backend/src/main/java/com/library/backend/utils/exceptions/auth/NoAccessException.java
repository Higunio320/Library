package com.library.backend.utils.exceptions.auth;

import com.library.backend.utils.exceptions.BackendException;

import java.io.Serial;

public class NoAccessException extends BackendException {

    @Serial
    private static final long serialVersionUID = -2711661575780015283L;

    public NoAccessException(String message) {
        super(message);
    }
}
