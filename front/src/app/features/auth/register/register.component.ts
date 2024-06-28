import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/core/services/auth.service';
import { RegisterRequest } from '../../../core/interfaces/auth/RegisterRequest.interface';
import { UserService } from 'src/app/core/services/user.service';
import { passwordStrengthValidator } from 'src/app/utils/validators/password.validator';
import { usernameValidator } from 'src/app/utils/validators/username.validator';
import { RegisterResponse } from 'src/app/core/interfaces/auth/RegisterResponse.interface';

/**
 * Component for handling user registration.
 *
 * @Component Decorator that provides metadata for the component including its selector,
 * templateUrl, and styleUrls.
 */
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;
  errorMessage: string = '';
  fieldAlreadyTaken: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private location: Location,
    private userService: UserService
  ) { }

  /**
   * Initializes the component, setting up the registration form with validators.
   */
  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required,usernameValidator()]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, passwordStrengthValidator()]]
    });
  }

  /**
   * Getter for easy access to form controls from the template.
   */
  get formControls() {
    return this.registerForm.controls;
  }

  /**
   * Handles form submission for registration.
   */
  onSubmit(): void {
    this.submitted = true;

    const observer = {
      next: (response: RegisterResponse) => {
        localStorage.setItem('token', response.token);
        this.userService.loadUserData();
        this.router.navigate(['/posts']);
      },
      error: (error: any) => {
        console.error('Registration error', error);
        if(error.status === 409){
          this.fieldAlreadyTaken = error.error.field;
        }else{
          this.errorMessage = error.error.message || "Erreur d'enregistrement";
        }
      }
    };
    const registerRequest = this.registerForm.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(observer);
  }

   /**
   * Navigates back to the previous page.
   */
  goBack(): void {
    this.location.back();
  }
}
