import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginResponse} from "../../data/auth/login-response";
import {StorageService} from "../storage/storage.service";
import {tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = false;
  api_url = 'http://localhost:8080';

  constructor(private http: HttpClient,
              private storage: StorageService) {}


  login(username: string, password: string) {
    return this.http.post<LoginResponse>(this.api_url + '/api/auth/login', {email: username, password: password}).pipe(
      tap({
        next: (response) => {
          this.saveToken(response);
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

  private saveToken(response: LoginResponse) {
    this.storage.saveToken(response.token);
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


}
