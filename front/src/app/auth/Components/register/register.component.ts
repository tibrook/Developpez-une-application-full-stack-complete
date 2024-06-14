import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { RegisterRequest } from '../../interfaces/RegisterRequest.interface';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
  submitted = false;
  errorMessage: string = '';

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private location: Location 
  ) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]]
    });
  }

  get formControls() {
    return this.registerForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;

    const observer = {
      next: (response: any) => {
        console.log('Registration successful', response);
        this.router.navigate(['/login']); // Rediriger vers la page de login
      },
      error: (error: any) => {
        console.error('Registration error', error);
        this.errorMessage = error.error.message || 'Registration failed';
      }
    };
    const registerRequest = this.registerForm.value as RegisterRequest;
    this.authService.register(registerRequest).subscribe(observer);
  }

  goBack(): void {
    this.location.back();
  }
}
