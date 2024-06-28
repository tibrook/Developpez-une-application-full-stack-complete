import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Topic } from '../interfaces/topics/topic.interface';
import { environment } from 'src/environments/environment';

/**
 * Service responsible for retrieving topic information from the server.
 * This service handles all interactions with the API concerning topic data.
 *
 * @Injectable Decorator that marks this class as available for dependency injection.
 * @providedIn 'root' - Indicates that the service should be provided in the root injector,
 * ensuring that only one instance of this service exists throughout the app.
 */
@Injectable({
  providedIn: 'root'
})
export class TopicService {
  private apiUrl = `${environment.baseUrl}/auth/topics`;

  constructor(private http: HttpClient) { }

  /**
   * Fetches all available topics from the server.
   * This method retrieves a list of topics from the API, which can be displayed or used within the application.
   * 
   * @returns An Observable that emits an array of Topic objects, allowing components to subscribe to this method
   * and get updated topic information as it is loaded from the server.
   */
  getAllTopics(): Observable<Topic[]> {
    return this.http.get<Topic[]>(`${this.apiUrl}`);
  }
}
