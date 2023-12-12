package com.library.backend.api.bookloan.data;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record BookLoanResponse(long loanId, long userId, long bookId, LocalDate loanDate, LocalDate returnDate) {
}
