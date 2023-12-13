import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BookLoan} from "../../data/bookloans/book-loan";
import {ApiUrl} from "../../enums/api-url";

@Injectable({
  providedIn: 'root'
})
export class BookLoanService {

  constructor(private http: HttpClient) { }

  borrowBook(bookId: number) {
    return this.http.post<BookLoan>(ApiUrl.BOOK_LOANS_ADD, null,{params: {bookId: bookId}});
  }

  getBookLoans(username: string) {
    return this.http.get<BookLoan[]>(ApiUrl.BOOK_LOANS_GET_ALL, {params: {username: username}});
  }

  returnBook(bookLoanId: number) {
    return this.http.post<BookLoan>(ApiUrl.BOOK_LOANS_RETURN, null ,{params: {loanId: bookLoanId}});
  }
}
