package com.library.backend.api.books;

import com.library.backend.api.books.data.BookRequest;
import com.library.backend.api.books.data.BookResponse;
import com.library.backend.api.books.interfaces.BookService;
import com.library.backend.entities.book.Book;
import com.library.backend.utils.exceptions.book.BookNotFoundException;
import com.library.backend.api.books.interfaces.BookMapper;
import com.library.backend.entities.book.interfaces.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponse findById(long id) {
        log.info("Getting book of id: {}", id);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id %d has not been found", id)));

        log.info("Returning book response of: {}", book);

        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public final BookResponse addBook(BookRequest bookRequest) {
        if(bookRequest == null) {
            throw new IllegalArgumentException("Book request cannot be null");
        }

        log.info("Creating new book for book request: {}", bookRequest);

        Book bookToAdd = Book.builder()
                .title(bookRequest.title())
                .author(bookRequest.author())
                .genre(bookRequest.genre())
                .totalNumber(bookRequest.availableNumber())
                .availableNumber(bookRequest.availableNumber())
                .description(bookRequest.description())
                .build();

        log.info("Saving book: {}", bookToAdd);

        Book savedBook = bookRepository.save(bookToAdd);

        log.info("Returning book response of: {}", savedBook);

        return bookMapper.bookToBookResponse(savedBook);
    }

    @Override
    public Collection<BookResponse> findAll() {
        log.info("Getting all books");

        Collection<Book> books = bookRepository.findAll();

        log.info("Returning book responses quantity: {}", books.size());

        return books.stream()
                .map(bookMapper::bookToBookResponse)
                .toList();
    }

    @Override
    public Collection<BookResponse> findByTitle(String title) {
        log.info("Searching for books with title: {}", title);

        Collection<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);

        log.info("Returning list of: {} books", books.size());

        return books.stream()
                .map(bookMapper::bookToBookResponse)
                .toList();
    }

    @Override
    public BookResponse deleteById(long id) {
        log.info("Getting book of id: {}", id);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id %d has not been found", id)));

        log.info("Deleting book of id: {}", id);

        bookRepository.deleteById(id);

        return bookMapper.bookToBookResponse(book);
    }

    @Override
    public BookResponse returnBook(long id) {
        return null;
    }

    @Override
    public BookResponse borrowBook(long id) {
        return null;
    }
}
