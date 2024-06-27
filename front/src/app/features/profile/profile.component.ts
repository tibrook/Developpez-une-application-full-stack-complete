import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { UserService } from 'src/app/core/services/user.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Topic } from 'src/app/core/interfaces/topics/topic.interface';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profileForm!: FormGroup;
  user: any;
  subscriptions: Topic[] = [];
  errorMessage: { [key: string]: string } = {};

  constructor(
    private userService: UserService,
    private subscriptionService: SubscriptionService,
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.profileForm = this.formBuilder.group({
      username: ['', [Validators.required, Validators.pattern(/^[a-zA-Z0-9]*$/)]],
      email: ['', [Validators.required, Validators.email]],
    });

    this.loadUserProfile();
    this.loadSubscriptions();
  }
  loadSubscriptions(): void {
    this.userService.topics$.subscribe(topic => {
      this.subscriptions = topic.filter(topic => topic.subscribed);
    });
  }
  loadUserProfile(): void {
    const user = this.userService.getUser();
    if (user) {
      this.user = user;
      this.profileForm.patchValue(user);
    } else {
      this.userService.user$.subscribe(user => {
        this.user = user;
        this.profileForm.patchValue(user);
      });
    }

  }

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
      },
      error: (err) => {
        console.error('Error updating user', err);
        if (err.error && err.error.field) {
          this.errorMessage[err.error.field] = err.error.message;
        }    
      } 
    });
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  unsubscribe(topicId: number): void {
    this.subscriptionService.unsubscribe(topicId).subscribe(() => {
      this.subscriptions = this.subscriptions.filter(sub => sub.id !== topicId);
    });
  }
}
