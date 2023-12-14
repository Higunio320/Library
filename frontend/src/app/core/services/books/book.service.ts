import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Book} from "../../data/books/book";
import {ApiUrl} from "../../enums/api-url";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) {}

  searchByBookName(bookName: string) {
    return this.http.get<Book[]>(`${environment.API_URL}${ApiUrl.BOOKS_SEARCH}`, {params: {title: bookName}});
  }
}
