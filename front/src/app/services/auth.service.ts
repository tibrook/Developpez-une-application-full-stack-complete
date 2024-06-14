import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../auth/interfaces/LoginRequest.interface';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = `${environment.baseUrl}/auth`;

  constructor(private http: HttpClient) { }

  login(loginRequest: LoginRequest): Observable<any> {
    console.log("login with credentials", loginRequest)
    return this.http.post(`${this.apiUrl}/login`, loginRequest);
  }

  register(user: { email: string, username: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }
}
