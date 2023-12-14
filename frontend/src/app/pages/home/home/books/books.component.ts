import { Component } from '@angular/core';
import {MatInputModule} from "@angular/material/input";
import {MatIconModule} from "@angular/material/icon";
import {NgIf, NgStyle} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {FormsModule} from "@angular/forms";
import {BookService} from "../../../../core/services/books/book.service";
import {Book} from "../../../../core/data/books/book";
import {MatTableModule} from "@angular/material/table";
import {BookLoanService} from "../../../../core/services/bookloans/book-loan.service";
import {MatSortModule} from "@angular/material/sort";

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [
    MatInputModule,
    MatIconModule,
    NgStyle,
    MatButtonModule,
    FormsModule,
    MatTableModule,
    NgIf,
    MatSortModule
  ],
  templateUrl: './books.component.html',
  styleUrl: './books.component.scss'
})
export class BooksComponent {

  bookName = "";

  bookList : Book[] = [];

  displayedColumns: string[] = ['title', 'author', 'genre', 'description', 'availableNumber', 'actions'];

  constructor(private bookService: BookService,
              private bookLoanService: BookLoanService) { }

  search() {
    this.bookService.searchByBookName(this.bookName).subscribe({
      next: response => this.bookList = response,
      error: error => console.log(error)
    });
  }

  borrowBook(book: Book) {
    const bookId = book.id;
    this.bookLoanService.borrowBook(bookId).subscribe({
      next: response => {
        this.search();
      },
      error: error => console.log(error)
    });
  }
}
