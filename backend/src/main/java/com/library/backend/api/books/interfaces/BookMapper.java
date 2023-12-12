package com.library.backend.api.books.interfaces;

import com.library.backend.entities.book.Book;
import com.library.backend.api.books.data.BookResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse bookToBookResponse(Book book);
}
