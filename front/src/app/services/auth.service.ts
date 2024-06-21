import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../auth/interfaces/LoginRequest.interface';
import { RegisterRequest } from '../auth/interfaces/RegisterRequest.interface';
import { LoginResponse } from '../auth/interfaces/LoginResponse.interface';
import { RegisterResponse } from '../auth/interfaces/RegisterResponse.interface';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.baseUrl}/auth`;

  constructor(private http: HttpClient) { }

  login(loginRequest: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, loginRequest);

  }
  register(registerRequest: RegisterRequest): Observable<RegisterResponse> {
    return this.http.post<RegisterResponse>(`${this.apiUrl}/register`, registerRequest);
  }
  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }
  logout(): void {
    localStorage.removeItem('token');
  }
}
