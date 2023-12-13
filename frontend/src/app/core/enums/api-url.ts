export enum ApiUrl {
  API_URL = 'http://localhost:8080/api',

  AUTH = API_URL + '/auth',
  BOOKS = API_URL + '/books',
  BOOK_LOANS = API_URL + '/bookloans',

  LOGIN = AUTH + '/login',
  REGISTER = AUTH + '/register',

  BOOKS_SEARCH = BOOKS + '/search',

  BOOK_LOANS_ADD = BOOK_LOANS + '/add',
  BOOK_LOANS_GET_ALL = BOOK_LOANS + '/getAll',
  BOOK_LOANS_RETURN = BOOK_LOANS + '/return'
}
