package com.library.backend.api.books.interfaces;

import com.library.backend.api.books.data.BookRequest;
import com.library.backend.api.books.data.BookResponse;

import java.util.Collection;

public interface BookController {

    BookResponse findById(long id);

    BookResponse addBook(BookRequest bookRequest);

    Collection<BookResponse> findAll();

    Collection<BookResponse> findByTitle(String title);

    BookResponse deleteById(long id);
}
