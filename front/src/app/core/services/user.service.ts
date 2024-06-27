import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TopicService } from './topic.service';
import { Topic } from '../interfaces/topics/topic.interface';
import { SubscriptionResponse } from '../interfaces/topics/subscription.interface';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<any>(null);
  private apiUrl = `${environment.baseUrl}/auth/profile`;
  private topicsSubject = new BehaviorSubject<Topic[]>([]);
  private subscriptionsSubject = new BehaviorSubject<SubscriptionResponse[]>([]);

  user$ = this.userSubject.asObservable();
  subscriptions$ = this.subscriptionsSubject.asObservable();
  topics$ = this.topicsSubject.asObservable();

  constructor(private http: HttpClient,  private topicService: TopicService) {}

  setUser(user: any): void {
    this.userSubject.next(user);
  }

  getUser(): any {
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
  getUserDetails(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/me`);
  }

  updateUser(user: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/update`, user);
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

}
