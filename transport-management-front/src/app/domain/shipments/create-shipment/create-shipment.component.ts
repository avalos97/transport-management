import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Shipment } from '../interface/shipment';
import { ShipmentService } from '../service/shipment.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Product } from '../../products/interface/product';
import { Client } from '../../clients/interface/client';
import { ShippingType } from '../interface/shipping-type';
import { ProductService } from '../../products/service/product.service';
import { ClientService } from '../../clients/service/client.service';
import { StorageService } from '../../storage/service/storage.service';
import { Storage } from '../../storage/interface/storage';

@Component({
  selector: 'app-create-shipment',
  templateUrl: './create-shipment.component.html',
  styleUrls: ['./create-shipment.component.scss']
})
export class CreateShipmentComponent {

  shipmentForm: FormGroup = this.fb.group({
    id: [null],
    quantity: ['', Validators.required],//
    registrationDate: [''],
    deliveryDate: ['', Validators.required],//
    shippingPrice: ['', Validators.required],//
    discount: [''],
    fleetVehicleNumber: ['', Validators.required],//
    guideNumber: ['', [Validators.required, Validators.pattern('^[A-Za-z0-9]{10}$')]],//se  valida que tenga 10 caracteres alfanumericos
    isActive: [true],
    productId: ['', Validators.required],//
    clientId: ['', Validators.required],//
    storageId: ['', Validators.required],//
    shippingTypeId: ['', Validators.required],//
  });

  productList: Product[] = [];
  clientList: Client[] = [];
  storageList: Storage[] = [];
  shippingTypeList: ShippingType[] = [];
  labelFleetVehicle: string = 'Placa/Numero de flota';
  placeholderFleetVehicle: string = 'Placa/Numero de flota';

  shipment: Shipment = new Shipment();
  countries: { id: number, name: string }[] = [];

  constructor(
    private shipmentService: ShipmentService,
    private productService: ProductService,
    private clientService: ClientService,
    private storageService: StorageService,
    private dialogRef: MatDialogRef<CreateShipmentComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Shipment,
    private toast: ToastrService,
    private fb: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.checkShippingTypeId();
    this.shipmentForm.get('shippingTypeId')?.valueChanges.subscribe(() => {
      this.checkShippingTypeId();
    });

    this.setupFleetVehicleNumberValidator();
    this.setShipment();
    this.loadProducList();
    this.loadClientList();
    this.loadStorageList();
    this.loadShippingTypeList();
  }

  setShipment() {
    this.shipment = this.data;
    if (this.shipment.id) {
      this.shipmentForm.reset({
        ...this.shipment,
      });
    }
  }

  loadProducList() {
    this.productService.getAllProducts().subscribe({
      next: (response: Product[]) => {
        this.productList = response;
      },
      error: (error) => {
        console.log(error);
      }
    }
    );
  }

  loadClientList() {
    this.clientService.getAllClients().subscribe({
      next: (response: Client[]) => {
        this.clientList = response;
      },
      error: (error) => {
        console.log(error);
      }
    }
    );
  }

  loadStorageList() {
    this.storageService.getAllStorages().subscribe({
      next: (response: Storage[]) => {
        this.storageList = response;
      },
      error: (error) => {
        console.log(error);
      }
    }
    );
  }

  loadShippingTypeList() {
    this.shipmentService.getAllShippingType().subscribe({
      next: (response: ShippingType[]) => {
        this.shippingTypeList = response;
      },
      error: (error) => {
        console.log(error);
      }
    }
    );
  }

  checkShippingTypeId() {
    const shippingTypeIdControl = this.shipmentForm.get('shippingTypeId');
    const fleetVehicleNumberControl = this.shipmentForm.get('fleetVehicleNumber');
    if (!shippingTypeIdControl?.value) {
      fleetVehicleNumberControl?.disable();
    } else {
      fleetVehicleNumberControl?.enable();
      this.labelFleetVehicle = (shippingTypeIdControl?.value == 1) ? 'Placa del vehículo' : 'Numero de flota';
      this.placeholderFleetVehicle = (shippingTypeIdControl?.value == 1) ? 'FORMATO: AAA333' : 'FORMATO: AAA3333A';
    }
  }

  accept(): void {
    const formValue = { ...this.shipmentForm.value };
    if (this.shipmentForm.invalid) {//validamos que no haya error en los campos
      this.shipmentForm.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }
    this.shipment = formValue;
    (this.shipment.id) ? this.updateShipment() : this.saveShipment();
  }

  saveShipment(): void {
    this.shipmentService.addShipment(this.shipment).subscribe({
      next: (response: Shipment) => {
        this.toast.success('shipment created successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  updateShipment(): void {
    this.shipmentService.updateShipment(this.shipment).subscribe({
      next: (response: Shipment) => {
        this.toast.success('Shipment updated successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  closeModal(status?: boolean) {
    this.shipmentForm.reset();
    this.dialogRef.close(status);
  }

  ErrorRequiredMsg(field: string): string {
    const errors = this.shipmentForm.get(field)?.errors;
    const touch = this.shipmentForm.get(field)?.touched;

    if ((errors?.['required']) && touch) {
      return `Campo requerido`
    } else if (errors?.['pattern'] && touch) {
      return `Formato inválido`;
    }
    return '';
  }

  setupFleetVehicleNumberValidator() {
    this.shipmentForm.get('shippingTypeId')?.valueChanges.subscribe(value => {
      const fleetVehicleNumberControl = this.shipmentForm.get('fleetVehicleNumber');

      if (value === 1) {
        fleetVehicleNumberControl?.setValidators([
          Validators.required,
          Validators.pattern('^[A-Za-z]{3}[0-9]{3}$')
        ]);
      } else if (value === 2) {
        fleetVehicleNumberControl?.setValidators([
          Validators.required,
          Validators.pattern('^[A-Za-z]{3}[0-9]{4}[A-Za-z]$')
        ]);
      } else {
        fleetVehicleNumberControl?.clearValidators();
      }

      fleetVehicleNumberControl?.updateValueAndValidity();
    });
  }
}
