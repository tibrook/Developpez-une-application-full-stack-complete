import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private apiUrl = `${environment.baseUrl}/posts`;

  constructor(private http: HttpClient) { }

  addComment(postId: string, content: string): Observable<any[]> {
    const jsonContent = {
      content: content
    }
    return this.http.post<any[]>(`${this.apiUrl}/${postId}/comment`, jsonContent);
  }
}
