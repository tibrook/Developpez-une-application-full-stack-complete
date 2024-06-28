import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Comment } from '../interfaces/comments/comment.interface';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = `${environment.baseUrl}/auth/posts`;

  constructor(private http: HttpClient) { }

  addComment(postId: string, content: string): Observable<Comment[]> {
    const jsonContent = {
      content: content
    }
    return this.http.post<Comment[]>(`${this.apiUrl}/${postId}/comment`, jsonContent);
  }
}
