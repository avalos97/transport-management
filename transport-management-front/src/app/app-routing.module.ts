import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './core/auth/guards/auth.guard';
import { LoginGuard } from './core/auth/guards/login.guard';

const routes: Routes = [
  { path: 'logistics-app', loadChildren: () => import('./sidenav/sidenav.module').then(m => m.SidenavModule), canActivate: [AuthGuard] },
  { path: 'auth', loadChildren: () => import('./core/auth/auth.module').then(m => m.AuthModule), canActivate: [LoginGuard] },
  { path: '**', redirectTo: 'auth/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
