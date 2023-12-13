package com.library.backend.api.bookloan;

import com.library.backend.api.bookloan.data.BookLoanResponse;
import com.library.backend.api.bookloan.interfaces.BookLoanService;
import com.library.backend.entities.book.Book;
import com.library.backend.entities.book.interfaces.BookRepository;
import com.library.backend.entities.bookloan.BookLoan;
import com.library.backend.entities.bookloan.interfaces.BookLoanMapper;
import com.library.backend.entities.bookloan.interfaces.BookLoanRepository;
import com.library.backend.entities.user.User;
import com.library.backend.entities.user.interfaces.UserRepository;
import com.library.backend.utils.exceptions.auth.NoAccessException;
import com.library.backend.utils.exceptions.book.BookNotFoundException;
import com.library.backend.utils.exceptions.bookloan.BookLoanNotFoundException;
import com.library.backend.utils.exceptions.bookloan.NotEnoughBooksException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookLoanServiceImpl implements BookLoanService {

    private final BookLoanRepository bookLoanRepository;
    private final BookRepository bookRepository;
    private final BookLoanMapper bookLoanMapper;
    private final UserRepository userRepository;

    private static final long LOAN_DAYS = 14L;

    @Override
    public final BookLoanResponse addNewLoan(long bookId, User user) {
        log.info("Getting book of id: {}", bookId);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(String.format("Book of id %d has not been found", bookId)));

        if(book.getAvailableNumber() == 0) {
            throw new NotEnoughBooksException(String.format("There are no available books of title %s", book.getTitle()));
        }

        log.info("Book available number: {}, borrowing book", book.getAvailableNumber());
        book.borrowBook();

        log.info("Saving book: {}", book);
        bookRepository.save(book);

        log.info("Creating new book loan for book: {} and user: {}", book, user);

        BookLoan bookLoan = BookLoan.builder()
                .book(book)
                .user(user)
                .loanDate(LocalDate.now())
                .endDate(LocalDate.now().plusDays(LOAN_DAYS))
                .build();

        log.info("Saving book loan: {}", bookLoan);

        BookLoan savedBookLoan = bookLoanRepository.save(bookLoan);

        log.info("Returning book loan response of: {}", savedBookLoan);

        return bookLoanMapper.bookLoanToBookLoanResponse(savedBookLoan);
    }

    @Override
    public final List<BookLoanResponse> getLoansForGivenUser(String username, User user) {
        //check if given user can access this list
        if(!user.isAdmin() && !user.getUsername().equals(username)) {
            throw new NoAccessException(String.format("User %s has no access to loans of user %s", user.getUsername(), username));
        }

        log.info("Fetching loans for user: {}", username);

        List<BookLoan> bookLoans = bookLoanRepository.getBookLoanByUser(username);

        log.info("Returning book loans list of size: {}", bookLoans.size());

        return bookLoans.stream()
                .map(bookLoanMapper::bookLoanToBookLoanResponse)
                .toList();
    }

    @Override
    public BookLoanResponse returnBook(long loanId, User user) {

        BookLoan bookLoan = bookLoanRepository.findById(loanId)
                .orElseThrow(() -> new BookLoanNotFoundException(
                        String.format("Book loan of id %d has not been found", loanId)));

        //check if loan of given loanId is owned by given user or user is admin
        if(!user.isAdmin() && !bookLoan.getUser().getId().equals(user.getId())) {
            throw new NoAccessException(String.format("User %s has no access to loan of id %d", user.getUsername(), loanId));
        }

        Book book = bookLoan.getBook();

        if(book == null) {
            throw new BookNotFoundException("Book for given loan has not been found");
        }

        log.info("Returning book: {}", book);

        book.returnBook();

        bookRepository.save(book);

        log.info("Returning book loan response of: {}", bookLoan);

        bookLoanRepository.delete(bookLoan);

        return bookLoanMapper.bookLoanToBookLoanResponse(bookLoan);
    }
}
