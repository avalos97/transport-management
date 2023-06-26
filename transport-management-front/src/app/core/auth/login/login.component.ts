import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import * as moment from 'moment-timezone';
import { AuthService } from '../service/auth.service';
import { ToastrService } from 'ngx-toastr';
import { TokenService } from '../service/token.service';
import { AuthUser } from '../interface/auth-user';
import { Token } from '../interface/token';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  horaActual!: number;
  greeting!: string;
  hide = true;
  authUser: AuthUser = new AuthUser();

  loginForm: FormGroup = this.fb.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private tokenService: TokenService,
    private toastr: ToastrService,
    private router: Router
  ) { }

  ngOnInit() {
    this.horaActual = moment().tz('America/El_Salvador').hour();
    this.getGreeting();

  }

  sendCredentials() {
    const formValue = { ...this.loginForm.value };
    if (this.loginForm.invalid) {//validamos que no haya error en los campos
      this.loginForm.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }

    this.authUser = formValue;

    this.authService.login(this.authUser).subscribe({
      next: (response: Token) => {
        this.tokenService.setToken(response.token!);
        this.router.navigateByUrl('/logistics-app/home');
        this.toastr.success('Bienvenido!', 'Success');
      },
      error: (error) => {
        this.toastr.warning('Credenciales invalidas. Intente de nuevo', 'Error');
      }
    });

  }


  getGreeting(): string {
    if (this.horaActual >= 6 && this.horaActual < 12) {
      this.greeting = "Buenos días";
    } else if (this.horaActual >= 12 && this.horaActual < 19) {
      this.greeting = "Buenas tardes";
    } else {
      this.greeting = "Buenas noches";
    }
    return this.greeting;
  }

  ErrorMsg(field: string): string {
    const errors = this.loginForm.get(field)?.errors;
    const touch = this.loginForm.get(field)?.touched;

    if (errors?.['required'] && touch) {
      return `El campo es requerido`
    }
    return '';
  }

}
