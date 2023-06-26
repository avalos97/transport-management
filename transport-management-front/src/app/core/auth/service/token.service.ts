import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'AuthToken';


@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(
    private router: Router
  ) { 
  }

  public setToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY) || '';
  }

  public isLogged(): boolean {
    return (this.getToken()) ? true : false;
  }

  public getUsername(): string {
    if (!this.isLogged()) {
      return '';
    }
    //Decodificamos el payload del token para obtener el username
    const token = this.getToken();
    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const username = values.sub;
    return username;
  }

  public isRol(rol: string): boolean{
    if (!this.isLogged()) {
      return false;
    }
    //Decodificamos el payload del token para comprobar si esta el privilegio que pasamos como parametro
    const token = this.getToken();
    const payload = token.split('.')[1];
    const payloadDecoded = atob(payload);
    const values = JSON.parse(payloadDecoded);
    const roles = values.roles;
    return (roles.indexOf(rol) < 0) ? false : true;
  }

  public logOut(): void {
    this.setToken('');    
    this.router.navigate(['/auth/login']);
  }
}
