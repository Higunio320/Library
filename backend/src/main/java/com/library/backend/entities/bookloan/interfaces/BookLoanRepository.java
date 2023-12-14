package com.library.backend.entities.bookloan.interfaces;

import com.library.backend.entities.bookloan.BookLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookLoanRepository extends JpaRepository<BookLoan, Long> {

    @Query("SELECT bl FROM BookLoan bl WHERE bl.user.email = :username")
    List<BookLoan> getBookLoanByUser(@Param("username") String username);
}
