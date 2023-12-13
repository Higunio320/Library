import { Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home/home.component";
import {LoginComponent} from "./pages/home/auth/login/login.component";
import {RegisterComponent} from "./pages/home/auth/register/register.component";
import {authGuard} from "./core/guards/auth.guard";

export const routes: Routes = [
  { path: '', redirectTo: 'auth/login', pathMatch: 'full' },
  { path: 'auth', children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: '**', redirectTo: 'login' }
    ]},
  { path: 'home', component: HomeComponent, canActivate: [authGuard]},
  { path: '**', redirectTo: 'auth/login' }
];
