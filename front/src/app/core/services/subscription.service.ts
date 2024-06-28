import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubscriptionResponse } from '../interfaces/topics/subscription.interface';
import { environment } from 'src/environments/environment';

/**
 * Service responsible for managing subscriptions to topics.
 * Allows users to subscribe to and unsubscribe from topics via API calls.
 *
 * @Injectable Decorator that marks this class as available for dependency injection,
 * with a singleton instance provided in the root injector.
 */
@Injectable({
  providedIn: 'root'
})
export class SubscriptionService {
  private apiUrl = `${environment.baseUrl}/auth`;

  constructor(private http: HttpClient) { }

   /**
   * Unsubscribes a user from a topic identified by the topicId.
   * 
   * @param topicId The ID of the topic from which to unsubscribe.
   * @returns An Observable that emits the response from the server regarding the unsubscription action.
   */
  unsubscribe(topicId: number): Observable<SubscriptionResponse> {
    return this.http.delete<SubscriptionResponse>(`${this.apiUrl}/topics/${topicId}/unsubscribe`);
  }
  
   /**
   * Subscribes a user to a topic identified by the topicId.
   * 
   * @param topicId The ID of the topic to which to subscribe.
   * @returns An Observable that emits the response from the server regarding the subscription action.
   */
  subscribe(topicId: number): Observable<SubscriptionResponse>  {
    return this.http.post<SubscriptionResponse>(`${this.apiUrl}/topics/${topicId}/subscribe`, null);
  }
}
