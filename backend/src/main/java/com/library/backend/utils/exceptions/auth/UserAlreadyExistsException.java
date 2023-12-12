package com.library.backend.utils.exceptions.auth;

import com.library.backend.utils.exceptions.BackendException;

public class UserAlreadyExistsException extends BackendException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
