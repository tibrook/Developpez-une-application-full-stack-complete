import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../../interfaces/LoginRequest.interface';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm!: FormGroup;
  submitted = false;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usernameOrEmail: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  get formControls() {
    return this.loginForm.controls;
  }

  onSubmit(): void {
    console.log("login with credentials", this.loginForm.value)
    const loginRequest = this.loginForm.value as LoginRequest;
    this.authService.login(loginRequest).subscribe(
      response => {
        localStorage.setItem('token', response.token);
        this.router.navigate(['/']);
      },
      error => {
        this.errorMessage = error.error.message || 'Login failed';
      }
    );
  }
}
