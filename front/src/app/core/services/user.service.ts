import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TopicService } from './topic.service';
import { Topic } from '../interfaces/topics/topic.interface';
import { SubscriptionResponse } from '../interfaces/topics/subscription.interface';
import { User } from '../interfaces/profile/user.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<User|null>(null);
  private apiUrl = `${environment.baseUrl}/auth/profile`;
  private topicsSubject = new BehaviorSubject<Topic[]>([]);
  private subscriptionsSubject = new BehaviorSubject<SubscriptionResponse[]>([]);

  user$ = this.userSubject.asObservable();
  subscriptions$ = this.subscriptionsSubject.asObservable();
  topics$ = this.topicsSubject.asObservable();

  constructor(private http: HttpClient,  private topicService: TopicService) {}

  setUser(user: User | null): void {
    this.userSubject.next(user);
  }

  getUser(): User | null {
    return this.userSubject.value;
  }

  loadUserData(): void {
    if (!this.getUser()) {
      this.getUserDetails().subscribe({
        next: (user) => {
          this.setUser(user);
          this.loadTopics();
        },
        error: (err) => {
          console.error('Error loading user details', err);
        }
      });
    }
  }
  getUserDetails(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/me`);
  }

  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/update`, user);
  }
  loadTopics(): void {
    this.topicService.getAllTopics().subscribe({
      next: (topics) => {
        this.topicsSubject.next(topics);
      },
      error: (err) => {
        console.error('Error loading topics', err);
      }
    });
  }
  logout(): void {
    this.setUser(null);
  }

}
