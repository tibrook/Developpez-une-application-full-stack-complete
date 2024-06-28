import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/posts/post.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = `${environment.baseUrl}/auth/posts`;

  constructor(private http: HttpClient) { }

  getPostsBySubscribedTopics(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.apiUrl}/feed`);
  }
  getPostById(postId: string): Observable<Post> {
    return this.http.get<Post>(`${this.apiUrl}/${postId}`);
  }
  createPost(post: Post): Observable<Post> {
    return this.http.post<Post>(`${this.apiUrl}/add`, post);
  }
}
