package com.library.backend.entities.bookloan.interaces;

import com.library.backend.api.bookloan.data.BookLoanResponse;
import com.library.backend.entities.bookloan.BookLoan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookLoanMapper {

    BookLoanResponse bookLoanToBookLoanResponse(BookLoan bookLoan);
}
