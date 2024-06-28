import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Post } from '../interfaces/posts/post.interface';
import { environment } from 'src/environments/environment';

/**
 * Service responsible for handling API calls related to posts.
 * This service allows fetching, creating, and retrieving specific posts.
 *
 * @Injectable Decorator that marks this class as available for dependency injection,
 * with a singleton instance provided in the root injector.
 */
@Injectable({
  providedIn: 'root'
})
export class PostService {
  private apiUrl = `${environment.baseUrl}/auth/posts`;

  constructor(private http: HttpClient) { }
 /**
   * Fetches a list of posts based on the topics the user is subscribed to.
   * @returns An Observable emitting an array of Post objects.
   */
  getPostsBySubscribedTopics(): Observable<Post[]> {
    return this.http.get<Post[]>(`${this.apiUrl}/feed`);
  }

  /**
   * Retrieves a specific post by its ID.
   * @param postId The unique identifier of the post.
   * @returns An Observable emitting the Post object retrieved.
   */
  getPostById(postId: string): Observable<Post> {
    return this.http.get<Post>(`${this.apiUrl}/${postId}`);
  }
  
   /**
   * Submits a new post to the server.
   * @param post The Post object to be created.
   * @returns An Observable emitting the newly created Post object.
   */
  createPost(post: Post): Observable<Post> {
    return this.http.post<Post>(`${this.apiUrl}/add`, post);
  }
}
