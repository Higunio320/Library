import { Component } from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatIconModule} from "@angular/material/icon";
import {MatInputModule} from "@angular/material/input";
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router, RouterLink} from "@angular/router";
import {AuthService} from "../../../../core/services/auth/auth.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    MatButtonModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    RouterLink
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  constructor(private authService: AuthService,
              private router: Router) {
  }

  hide = true;
  hideRepeat = true;

  registerForm = new FormGroup({
    username: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    repeatPassword: new FormControl('', Validators.required),
  });

  submitForm() {
    const username = this.registerForm.get('username')!.value;
    const password = this.registerForm.get('password')!.value;
    const firstName = this.registerForm.get('firstName')!.value;
    const lastName = this.registerForm.get('lastName')!.value;
    const repeatPassword = this.registerForm.get('repeatPassword')!.value;

    if(!username || !password || !firstName || !lastName || !repeatPassword) {
      return;
    }
    console.log("Registering...");

    this.authService.register(username, password, firstName, lastName).subscribe(
      response => {
        this.router.navigate(['/home']);
      }
    );
  }


}
