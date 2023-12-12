package com.library.backend.utils.exceptions.users;

import com.library.backend.utils.exceptions.BackendException;

public class UserNotFoundException extends BackendException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
