import {environment} from "../../../environments/environment";

export enum ApiUrl {
  AUTH = '/auth',
  BOOKS = '/books',
  BOOK_LOANS = '/bookloans',

  LOGIN = '/auth/login',
  REGISTER = '/auth/register',

  BOOKS_SEARCH = '/books/search',

  BOOK_LOANS_ADD = '/bookloans/add',
  BOOK_LOANS_GET_ALL = '/bookloans/getAll',
  BOOK_LOANS_RETURN = '/bookloans/return'
};
