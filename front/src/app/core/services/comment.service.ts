import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../interfaces/comments/comment.interface';

/**
 * Service for handling operations related to comments on posts.
 * This includes adding new comments and potentially other comment-related functionalities.
 *
 * @Injectable Decorator that marks this class as one that can participate in the dependency injection system.
 * @providedIn 'root' - Ensures a single instance of this service is provided across the app (singleton).
 */
@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl:string = `${environment.baseUrl}/auth/posts`;

  constructor(private http: HttpClient) { }
  /**
   * Sends a request to add a new comment to a specific post.
   * 
   * @param postId The ID of the post to which the comment is being added.
   * @param content The text content of the comment.
   * @returns An Observable that emits the list of updated comments for the post.
   */
  addComment(postId: string, content: string): Observable<Comment[]> {
    const jsonContent = {
      content: content
    }
    return this.http.post<Comment[]>(`${this.apiUrl}/${postId}/comment`, jsonContent);
  }
}
