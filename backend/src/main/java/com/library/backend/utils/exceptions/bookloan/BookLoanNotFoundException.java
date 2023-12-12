package com.library.backend.utils.exceptions.bookloan;

import com.library.backend.utils.exceptions.BackendException;

import java.io.Serial;

public class BookLoanNotFoundException extends BackendException {

    @Serial
    private static final long serialVersionUID = 505504488337063162L;

    public BookLoanNotFoundException(String message) {
        super(message);
    }
}
