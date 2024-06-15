import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TopicService } from './topic.service';
import { Topic } from '../interfaces/topic.interface';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<any>(null);
  private apiUrl = `${environment.baseUrl}/users/me`;
  private topicsSubject = new BehaviorSubject<Topic[]>([]);

  user$ = this.userSubject.asObservable();

  topics$ = this.topicsSubject.asObservable();

  constructor(private http: HttpClient,  private topicService: TopicService) {}

  setUser(user: any): void {
    this.userSubject.next(user);
  }

  getUser(): any {
    return this.userSubject.value;
  }

  loadUserData(): void {
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

  getUserDetails(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
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
