import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { CountryServiceService } from 'src/app/core/services/country-service.service';
import { ClientService } from '../service/client.service';
import { Client } from '../interface/client';

@Component({
  selector: 'app-create-client',
  templateUrl: './create-client.component.html',
  styleUrls: ['./create-client.component.scss']
})
export class CreateClientComponent {

  clientForm: FormGroup = this.fb.group({
    id: [null],
    name: ['', Validators.required],
    country: ['', Validators.required],
    address: ['', Validators.required],
    email: ['', Validators.required],
    isActive: [true],
    phone: ['', [Validators.required, /*Validators.pattern(this.validatorService.phonePattern),*/]],
  });

  client: Client = new Client();
  countries: { id: number, name: string }[] = [];

  constructor(
    private clientService: ClientService,
    private dialogRef: MatDialogRef<CreateClientComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Client,
    private toast: ToastrService,
    private fb: FormBuilder,
    private countryService: CountryServiceService
  ) {
    this.countries = this.countryService.getCountries();
  }

  ngOnInit(): void {
    this.setClient();
  }

  setClient() {
    this.client = this.data;
    if (this.client.id) {
      this.clientForm.reset({
        ...this.client,
      });
    }
  }

  accept(): void {
    const formValue = { ...this.clientForm.value };
    if (this.clientForm.invalid) {//validamos que no haya error en los campos
      this.clientForm.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }
    this.client = formValue;
    (this.client.id) ? this.updateClient() : this.saveCLient();
  }

  saveCLient(): void {
    this.clientService.addClient(this.client).subscribe({
      next: (response: Client) => {
        this.toast.success('Client created successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  updateClient(): void {
    this.clientService.updateClient(this.client).subscribe({
      next: (response: Client) => {
        this.toast.success('Client updated successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  closeModal(status?: boolean) {
    this.clientForm.reset();
    this.dialogRef.close(status);
  }

  ErrorRequiredMsg(field: string): string {
    const errors = this.clientForm.get(field)?.errors;
    const touch = this.clientForm.get(field)?.touched;

    if ((errors?.['required']) && touch) {
      return `Campo requerido`
    }
    return '';
  }
}
