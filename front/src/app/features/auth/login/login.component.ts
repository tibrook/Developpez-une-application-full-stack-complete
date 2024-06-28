import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginRequest } from '../../../core/interfaces/auth/LoginRequest.interface';
import { AuthService } from 'src/app/core/services/auth.service';
import { Location } from '@angular/common';
import { UserService } from 'src/app/core/services/user.service';
import { RegisterResponse } from 'src/app/core/interfaces/auth/RegisterResponse.interface';

/**
 * LoginComponent manages user login, form validation, and navigation post-login.
 *
 * @Component Decorator that associates metadata with the LoginComponent class.
 */
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
    private router: Router,
    private location: Location,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      usernameOrEmail: ['', [Validators.required]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  /**
   * Getter for easy access to form controls from the template.
   */
  get formControls() {
    return this.loginForm.controls;
  }

  /**
   * Handles form submission for login.
   */
  onSubmit(): void {
    const loginRequest = this.loginForm.value as LoginRequest;

    const observer = {
      next: (response: RegisterResponse) => {
        localStorage.setItem('token', response.token);
        this.userService.loadUserData();
        this.router.navigate(['/posts']);

      },
      error: (error: any) => {
        console.error('Login error', error);
        this.errorMessage = error.error.message || 'Login failed';
      }
    };
    this.authService.login(loginRequest).subscribe(observer);
  }
  
  /**
   * Navigates back to the previous page.
   */
  goBack(): void {
    this.location.back();
  }
}
