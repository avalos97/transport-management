import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';

@Injectable({
  providedIn: 'root'
})
export class VerificacionGuard implements CanActivate {
  constructor(
    private tokenService: TokenService,
    private toastr: ToastrService,
    private router:Router
  ) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean{
    
    if (!this.tokenService.getToken() || !this.tokenService.isRol('ROLE_ADMIN')) { //verificamos si tiene el acceso a la vista que pasaremos como parametro
      this.router.navigate(['']);
      this.toastr.warning('Acceso denegado', 'ATENCIÓN', {
        timeOut: 3000
      });
      return false;
    }
    return true ;
    
  }
  
}


