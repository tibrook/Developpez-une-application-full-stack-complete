import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../interfaces/auth/LoginRequest.interface';
import { RegisterRequest } from '../interfaces/auth/RegisterRequest.interface';
import { LoginResponse } from '../interfaces/auth/LoginResponse.interface';
import { RegisterResponse } from '../interfaces/auth/RegisterResponse.interface';
import { UserService } from './user.service';
/**
 * Service providing authentication related functionalities.
 *
 * @Injectable Decorator that marks the class as one that participates in the dependency injection system.
 * @providedIn 'root' - Specifies that the service should be provided in the root injector, making it available throughout the app.
 */
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.baseUrl}`;

  constructor(private http: HttpClient, private userService: UserService) { }
 /**
   * Performs the login operation by sending login credentials to the server.
   *
   * @param loginRequest The login request payload containing user credentials.
   * @returns An Observable emitting the server's response to the login request.
   */
  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginRequest);

  }
    /**
   * Performs the registration operation by sending user registration details to the server.
   *
   * @param registerRequest The registration request payload containing new user details.
   * @returns An Observable emitting the server's response to the registration request.
   */
  register(registerRequest: RegisterRequest): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(`${this.apiUrl}/register`, registerRequest);
  }
   /**
   * Checks if the user is currently logged in.
   *
   * @returns A boolean value indicating whether a user token exists in storage, suggesting the user is logged in.
   */
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
  /**
   * Logs the user out by removing the user token from storage and performing cleanup actions via UserService.
   */
  logout(): void {
    localStorage.removeItem('token');
    this.userService.logout(); 
  }
}
