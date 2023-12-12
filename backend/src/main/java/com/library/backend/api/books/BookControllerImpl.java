package com.library.backend.api.books;

import com.library.backend.api.books.data.BookRequest;
import com.library.backend.api.books.data.BookResponse;
import com.library.backend.api.books.interfaces.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/books")
@AllArgsConstructor
public class BookControllerImpl implements com.library.backend.api.books.interfaces.BookController {

    private BookService bookService;

    @Override
    @GetMapping("/{id}")
    public final BookResponse findById(@PathVariable long id) {
        return bookService.findById(id);
    }

    @Override
    @PostMapping
    public BookResponse addBook(@RequestBody BookRequest bookRequest) {
        return bookService.addBook(bookRequest);
    }

    @Override
    @GetMapping
    public Collection<BookResponse> findAll() {
        return bookService.findAll();
    }

    @Override
    @GetMapping("/search")
    public Collection<BookResponse> findByTitle(@RequestParam String title) {
        return bookService.findByTitle(title);
    }

    @Override
    @DeleteMapping()
    public BookResponse deleteById(@RequestParam long id) {
        return bookService.deleteById(id);
    }
}
