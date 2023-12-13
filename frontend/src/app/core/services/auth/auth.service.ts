import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LoginResponse} from "../../data/auth/login-response";
import {StorageService} from "../storage/storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  authenticated = false;
  api_url = 'http://localhost:8080';

  constructor(private http: HttpClient,
              private storage: StorageService) {}


  login(username: string, password: string) {
    this.http.post<LoginResponse>(this.api_url + '/api/auth/login', {email: username, password: password}).subscribe({
      next: next => this.saveToken(next),
      error: err => console.log(err)
    });
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
    return this.authenticated;
  }


}
