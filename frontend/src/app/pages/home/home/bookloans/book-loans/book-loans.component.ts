import {Component, OnInit} from '@angular/core';
import {BookLoan} from "../../../../../core/data/bookloans/book-loan";
import {MatTableModule} from "@angular/material/table";
import {MatButtonModule} from "@angular/material/button";
import {BookLoanService} from "../../../../../core/services/bookloans/book-loan.service";
import {StorageService} from "../../../../../core/services/storage/storage.service";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-book-loans',
  standalone: true,
  imports: [
    MatTableModule,
    MatButtonModule,
    NgIf
  ],
  templateUrl: './book-loans.component.html',
  styleUrl: './book-loans.component.scss'
})
export class BookLoansComponent implements OnInit {

  constructor(private bookLoanService: BookLoanService,
              private storage: StorageService) {
  }

  bookLoans : BookLoan[] = [];

  displayedColumns = ["bookTitle", "loanDate", "returnDate", "return"];

  returnBook(loan: BookLoan) {
    this.bookLoanService.returnBook(loan.loanId).subscribe(
      {
        next: next => this.getBookLoans()
      }
    );
  }

  ngOnInit(): void {
    this.getBookLoans();
  }

  getBookLoans() {
    const username = this.storage.getUser();
    if(!username) {
      return;
    }
    this.bookLoanService.getBookLoans(username).subscribe(bookLoans => {
      this.bookLoans = bookLoans;
    });
  }

}
