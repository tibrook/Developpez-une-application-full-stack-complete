import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubscriptionResponse } from '../interfaces/subscription.interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private apiUrl = `${environment.baseUrl}`;

  constructor(private http: HttpClient) { }

  unsubscribe(topicId: number): Observable<SubscriptionResponse> {
    return this.http.delete<SubscriptionResponse>(`${this.apiUrl}/topics/${topicId}/unsubscribe`);
  }
  subscribe(topicId: number): Observable<SubscriptionResponse>  {
    return this.http.post<SubscriptionResponse>(`${this.apiUrl}/topics/${topicId}/subscribe`, null);
  }
}
