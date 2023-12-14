package com.library.backend.api.bookloan;

import com.library.backend.api.bookloan.data.BookLoanRequest;
import com.library.backend.api.bookloan.data.BookLoanResponse;
import com.library.backend.api.bookloan.interfaces.BookLoanController;
import com.library.backend.api.bookloan.interfaces.BookLoanService;
import com.library.backend.entities.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/bookloans")
@RequiredArgsConstructor
public class BookLoanControllerImpl implements BookLoanController {

    private final BookLoanService bookLoanService;

    @PostMapping("/add")
    public BookLoanResponse addNewLoan(@RequestParam long bookId,
                                       @AuthenticationPrincipal User user) {

        return bookLoanService.addNewLoan(bookId, user);
    }

    @Override
    @GetMapping("/getAll")
    public List<BookLoanResponse> getLoansForGivenUser(@RequestParam String username,
                                                       @AuthenticationPrincipal User user) {

        return bookLoanService.getLoansForGivenUser(username, user);
    }

    @Override
    @PostMapping("/return")
    public BookLoanResponse returnBook(@RequestParam long loanId,
                                       @AuthenticationPrincipal User user) {

        return bookLoanService.returnBook(loanId, user);
    }
}
