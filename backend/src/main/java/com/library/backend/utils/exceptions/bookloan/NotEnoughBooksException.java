package com.library.backend.utils.exceptions.bookloan;

import com.library.backend.utils.exceptions.BackendException;

import java.io.Serial;

public class NotEnoughBooksException extends BackendException{

    @Serial
    private static final long serialVersionUID = 6905322206732043754L;

    public NotEnoughBooksException(String message) {
        super(message);
    }
}
