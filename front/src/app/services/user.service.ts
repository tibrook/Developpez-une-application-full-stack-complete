import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { TopicService } from './topic.service';
import { Topic } from '../interfaces/topic.interface';
import { SubscriptionService } from './subscription.service';
import { SubscriptionResponse } from '../interfaces/subscription.interface';
import { PostService } from './post.service';
import { Post } from '../interfaces/post.interface';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userSubject = new BehaviorSubject<any>(null);
  private apiUrl = `${environment.baseUrl}/users/me`;
  private topicsSubject = new BehaviorSubject<Topic[]>([]);
  private subscriptionsSubject = new BehaviorSubject<SubscriptionResponse[]>([]);
  private postsSubject = new BehaviorSubject<Post[]>([]);

  user$ = this.userSubject.asObservable();
  subscriptions$ = this.subscriptionsSubject.asObservable();
  topics$ = this.topicsSubject.asObservable();
  posts$ = this.postsSubject.asObservable();
  constructor(private http: HttpClient,  private topicService: TopicService, private subscriptionService: SubscriptionService, private postService: PostService) {}

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
        this.loadSubscriptions();
        this.loadSubscribedTopicsPosts();
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
  loadSubscriptions(): void {
    this.subscriptionService.getUserSubscriptions().subscribe({
      next: (subscriptions: SubscriptionResponse[]) => {
        this.subscriptionsSubject.next(subscriptions);
      },
      error: (err) => {
        console.error('Error loading subscriptions', err);
      }
    });
  }
  loadSubscribedTopicsPosts(): void {
    this.postService.getPostsBySubscribedTopics().subscribe({
      next: (posts: Post[]) => {
        this.postsSubject.next(posts);
      },
      error: (err) => {
        console.error('Error loading posts', err);
      }
    });
  }

}
