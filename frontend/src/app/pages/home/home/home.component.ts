import { Component } from '@angular/core';
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatSidenavModule} from "@angular/material/sidenav";
import {Router, RouterLink, RouterOutlet} from "@angular/router";
import {AuthService} from "../../../core/services/auth/auth.service";
import {HOME} from "@angular/cdk/keycodes";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    RouterOutlet,
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent {

  constructor(private authService: AuthService,
              private router: Router) { }

  logout() {
    this.authService.logout();
    this.router.navigate(['/auth/login']);
  }

  goToBooks() {
    this.router.navigate(['/home/books']);
  }

  goToLoans() {
    this.router.navigate(['/home/loans']);
  }

  protected readonly HOME = HOME;
}
