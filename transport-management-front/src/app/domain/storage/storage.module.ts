import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StorageRoutingModule } from './storage-routing.module';
import { CreateStorageComponent } from './create-storage/create-storage.component';
import { StorageListComponent } from './storage-list/storage-list.component';
import { AngularMaterialModule } from 'src/app/angular-material/angular-material.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    CreateStorageComponent,
    StorageListComponent
  ],
  imports: [
    CommonModule,
    StorageRoutingModule,
    AngularMaterialModule,
    SharedModule,
    ReactiveFormsModule,
    FormsModule,
  ]
})
export class StorageModule { }
