import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SidenavComponent } from './template/sidenav.component';
import { HomeComponent } from '../domain/home/dash/home.component';
import { VerificacionGuard } from '../core/auth/guards/verificacion.guard';

const routes: Routes = [
  {
    path: '',
    component: SidenavComponent,
    children: [
      {
        path: 'client-list',
        loadChildren: () => import('../domain/clients/client.module').then(m => m.ClientModule)
      },
      {
        path: 'user-list',
        loadChildren: () => import('../domain/users/users.module').then(m => m.UsersModule) , canActivate: [VerificacionGuard]
      },
      {
        path: 'product-list',
        loadChildren: () => import('../domain/products/product.module').then(m => m.ProductModule)
      },
      {
        path: 'storage-list',
        loadChildren: () => import('../domain/storage/storage.module').then(m => m.StorageModule)
      },
      {
        path: 'shipment-list',
        loadChildren: () => import('../domain/shipments/shipment.module').then(m => m.ShipmentModule)
      },
      {
        path: 'home',
        loadChildren: () => import('../domain/home/home.module').then(m => m.HomeModule)
      },
    ]
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SidenavRoutingModule { }
