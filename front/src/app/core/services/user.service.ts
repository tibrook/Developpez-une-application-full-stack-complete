import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TopicService } from './topic.service';
import { Topic } from '../interfaces/topics/topic.interface';
import { SubscriptionResponse } from '../interfaces/topics/subscription.interface';
import { User } from '../interfaces/profile/user.interface';

/**
 * Service responsible for managing user data, including their profile details, topics, and subscriptions.
 *
 * @Injectable Marks the class as a service that can be injected with dependencies and used across the application.
 * @providedIn 'root' Ensures a single instance of the service is used throughout the application.
 */
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

  /**
   * Sets the current user data in the user BehaviorSubject.
   * @param user The user data to set, or null to clear the user data.
   */
  setUser(user: User | null): void {
    this.userSubject.next(user);
  }

  /**
   * Retrieves the current user data from the user BehaviorSubject.
   * @returns The current user if set, or null if no user is set.
   */
  getUser(): User | null {
    return this.userSubject.value;
  }

  /**
   * Loads user data from the backend and updates the local user BehaviorSubject.
   * Also initiates loading of related topics upon successful retrieval of user data.
   */
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

  /**
   * Fetches user details from the server.
   * @returns An Observable of User containing the fetched user details.
   */
  getUserDetails(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/me`);
  }

  /**
   * Updates user details on the server.
   * @param user The updated user data to send to the server.
   * @returns An Observable of User containing the updated user data.
   */
  updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/update`, user);
  }

  /**
   * Loads all topics from the TopicService and updates the topics BehaviorSubject.
   */
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

  /**
   * Logs out the current user by clearing the user data and resetting related BehaviorSubjects.
   */
  logout(): void {
    this.setUser(null);
  }

}
