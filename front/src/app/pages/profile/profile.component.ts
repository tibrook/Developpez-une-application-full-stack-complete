import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Topic } from 'src/app/interfaces/topic.interface';
import { SubscriptionService } from 'src/app/services/subscription.service';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  profileForm!: FormGroup;
  user: any;
  subscriptions: Topic[] = [];

  constructor(
    private userService: UserService,
    private subscriptionService: SubscriptionService,
    private authService: AuthService,
    private router: Router,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {
    this.profileForm = this.formBuilder.group({
      username: ['', [Validators.required]],
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
    this.userService.getUserDetails().subscribe(user => {
      this.user = user;
      this.profileForm.patchValue(user);
    });

  }

  saveProfile(): void {
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
