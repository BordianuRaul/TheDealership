import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private url: string = "http://localhost:8080/api/cars";
  private tokenKey = 'authToken';

  constructor(private http: HttpClient) {}

  loginUser(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.url}/login`, { email, password });
  }

  registerUser(username: string, email: string, password: string) {
    return this.http.post<any>(`${this.url}/register`, null, { params:{ username, email, password }});
  }


  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }
  isUserLoggedIn(){
    return this.getToken();
  }
}
