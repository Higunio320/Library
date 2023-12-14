package com.library.backend.api.bookloan.interfaces;

import com.library.backend.api.bookloan.data.BookLoanResponse;
import com.library.backend.entities.user.User;

import java.util.List;

public interface BookLoanController {

    BookLoanResponse addNewLoan(long bookId, User user);

    List<BookLoanResponse> getLoansForGivenUser(String username, User user);

    BookLoanResponse returnBook(long loanId, User user);
}
