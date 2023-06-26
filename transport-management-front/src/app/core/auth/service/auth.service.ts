import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { AuthUser } from '../interface/auth-user';
import { Observable } from 'rxjs';
import { Token } from '../interface/token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiUrl = environment.REST_API_URL + 'authenticate';

  constructor(private http: HttpClient) { }

  login(auth: AuthUser): Observable<Token> {
    return this.http.post<Token>(this.apiUrl, auth);
  }
}
