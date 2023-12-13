import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginResponse} from "../../data/auth/login-response";
import {StorageService} from "../storage/storage.service";
import {tap} from "rxjs";
import {ApiUrl} from "../../enums/api-url";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = false;

  constructor(private http: HttpClient,
              private storage: StorageService) {}


  login(username: string, password: string) {
    return this.http.post<LoginResponse>(ApiUrl.LOGIN , {email: username, password: password}).pipe(
      tap({
        next: (response) => {
          this.saveToken(response.token);
          this.saveUser(username);
        },
        error: (error) => {
          console.log(error);
        }
      })
    );
  }

  logout() {
    this.storage.deleteToken();
    this.authenticated = false;
  }

  private saveToken(token: string) {
    this.storage.saveToken(token);
    this.authenticated = true;
  }

  isAuthenticated() {
    const token = this.storage.getToken();

    this.authenticated = token != null && token !== '';

    return this.authenticated;
  }

  private saveUser(username: string) {
    this.storage.saveUser(username);
  }

  register(username: string, password: string, firstName: string, lastName: string) {
    return this.http.post<LoginResponse>(ApiUrl.REGISTER,
      {email: username, password: password, firstName: firstName, lastName: lastName}).pipe(
        tap({
          next: response => {
            this.saveToken(response.token);
            this.saveUser(username);
          },
          error: error => console.log(error)
        },
          ));
  }

}
