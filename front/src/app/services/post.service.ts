import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/post.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = `${environment.baseUrl}/posts`;

  constructor(private http: HttpClient) { }

  getPostsBySubscribedTopics(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.apiUrl}/postsSubscribedTopics`);
  }
  getPostById(postId: string): Observable<Post> {
    return this.http.get<any>(`${this.apiUrl}/${postId}`);
  }
}
