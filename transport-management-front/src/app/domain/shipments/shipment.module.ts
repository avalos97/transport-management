import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ShipmentRoutingModule } from './shipment-routing.module';
import { CreateShipmentComponent } from './create-shipment/create-shipment.component';
import { ShipmentListComponent } from './shipments-list/shipment-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from 'src/app/shared/shared.module';
import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';


@NgModule({
  declarations: [
    CreateShipmentComponent,
    ShipmentListComponent
  ],
  imports: [
    CommonModule,
    ShipmentRoutingModule,
    AngularMaterialModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class ShipmentModule { }
