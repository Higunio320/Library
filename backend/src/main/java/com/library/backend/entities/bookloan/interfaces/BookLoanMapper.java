package com.library.backend.entities.bookloan.interfaces;

import com.library.backend.api.bookloan.data.BookLoanResponse;
import com.library.backend.entities.bookloan.BookLoan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookLoanMapper {

    @Mapping(target = "loanId", source = "id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "bookTitle", source = "book.title")
    @Mapping(target="returnDate", source = "endDate")
    BookLoanResponse bookLoanToBookLoanResponse(BookLoan bookLoan);
}
