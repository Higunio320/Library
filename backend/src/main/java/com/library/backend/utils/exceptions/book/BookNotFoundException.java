package com.library.backend.utils.exceptions.book;

import com.library.backend.utils.exceptions.BackendException;

import java.io.Serial;

public class BookNotFoundException extends BackendException {
    @Serial
    private static final long serialVersionUID = -6101802158540396893L;

    public BookNotFoundException(String message) {
        super(message);
    }
}
