import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserService } from 'src/app/core/services/user.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Topic } from 'src/app/core/interfaces/topics/topic.interface';
import { User } from 'src/app/core/interfaces/profile/user.interface';

/**
 * Component for managing user profile information.
 *
 * @Component Angular decorator that defines metadata for the component.
 */
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profileForm!: FormGroup;
  user: User | null = null;
  subscriptions: Topic[] = [];
  errorMessage: { [key: string]: string } = {};
  successMessage: string = '';

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  /**
   * Initializes component, form and loads user data.
   */
  ngOnInit(): void {
    this.profileForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9]*$/)]],
      email: ['', [Validators.required, Validators.email]],
    });

    this.loadUserProfile();
    this.loadSubscriptions();
    this.setupFormValueChanges();
  }

  /**
   * Sets up form value changes subscriptions to handle form validation dynamically.
   */
  setupFormValueChanges(): void {
    this.profileForm.get('username')?.valueChanges.subscribe(() => {
      this.errorMessage['username'] = '';
    });

    this.profileForm.get('email')?.valueChanges.subscribe(() => {
      this.errorMessage['email'] = '';
    });

    this.profileForm.valueChanges.subscribe(() => {
      this.errorMessage = {};
      this.successMessage = '';
    });
  }

  /**
   * Loads topics that the user is subscribed to.
   */
  loadSubscriptions(): void {
    this.userService.topics$.subscribe(topic => {
      this.subscriptions = topic.filter(topic => topic.subscribed);
    });
  }

  /**
   * Handles subscription change events.
   */
  onSubscriptionChanged(): void {
    this.loadSubscriptions();
  }

  /**
   * Loads user profile data and sets it in the form.
   */
  loadUserProfile(): void {
    const user = this.userService.getUser();
    if (user) {
      this.user = user;
      this.profileForm.patchValue(user);
    } else {
      this.userService.user$.subscribe(user => {
        if (user) { 
          this.user = user;
          this.profileForm.patchValue(user);
        }
      });
    }
  }

  /**
   * Submits the profile form and updates the user profile.
   */
  saveProfile(): void {
    if (this.profileForm.invalid) {
      return;
    }

    const updatedUser = this.profileForm.value;
    this.userService.updateUser(updatedUser).subscribe({
      next: (user) => {
        this.user = user;
        this.profileForm.patchValue(user);
        this.errorMessage = {};
        this.successMessage = 'Le profil a été mis à jour avec succès.';
        this.profileForm.markAsPristine(); // Reset form state
      },
      error: (err) => {
        console.error('Error updating user', err);
        this.successMessage = '';
        if (err.error && err.error.field) {
          this.errorMessage[err.error.field] = err.error.message;
        }    
      } 
    });
  }

  /**
   * Logs out the user and navigates to the login page.
   */
  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  /**
   * Checks if there is any error message present.
   * @returns Boolean indicating if there is an error message.
   */
  hasErrorMessage(): boolean {
    return Object.keys(this.errorMessage['message']).length > 0;
  }

}
