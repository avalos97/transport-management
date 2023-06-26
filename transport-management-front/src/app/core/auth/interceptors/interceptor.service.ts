import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HTTP_INTERCEPTORS } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Observable, throwError } from 'rxjs';
import { catchError, finalize } from 'rxjs/operators';
import { TokenService } from '../service/token.service';


const AUTHORIZATION = 'Authorization';

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  private countRequest = 0;

  constructor(
    private toastr: ToastrService,
    private spinner: NgxSpinnerService,
    private tokenService: TokenService,

  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
    // emit onStarted event before request execution
    if (!this.countRequest) {
      this.spinner.show();
    }
    this.countRequest++;

    if (!this.tokenService.isLogged()) {
      this.tokenService.logOut();
      return next.handle(req);
    }

    let intReq = req;
    const token = this.tokenService.getToken();
    intReq = this.addToken(req, token);

    return next.handle(intReq).pipe(finalize(() => {
      this.countRequest--;
      (!this.countRequest) && this.spinner.hide();
    }), catchError(error => {
      if (error instanceof HttpErrorResponse && error.status === 401) {
        this.toastr.warning('ACCESO DENEGADO', 'ATENCION', {
          timeOut: 3000
        });
        this.tokenService.logOut();
        return throwError(() => error);

      } else if (error.status === 403) {
        this.toastr.warning('PERMISOS REQUERIDOS', 'ATENCION', {
          timeOut: 3000
        });

        return throwError(() => error);
      } else {
        return throwError(() => error);
      }
    }
    ));
  }

  private addToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({ headers: req.headers.set(AUTHORIZATION, 'Bearer ' + token)},
    );
  }

}

export const interceptorProvider = [{ provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi: true }];