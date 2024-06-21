import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = 'http://localhost:8082/api/posts';

  constructor(private http: HttpClient) { }

  addComment(postId: string, content: string): Observable<any[]> {
    const jsonContent = {
      content: content
    }
    return this.http.post<any[]>(`${this.apiUrl}/${postId}/comments`, jsonContent);
  }
}
