import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Storage } from '../interface/storage';
import { StorageService } from '../service/storage.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { StorageType } from '../interface/storage-type';

@Component({
  selector: 'app-create-storage',
  templateUrl: './create-storage.component.html',
  styleUrls: ['./create-storage.component.scss']
})
export class CreateStorageComponent {

  storageForm: FormGroup = this.fb.group({
    id: [null],
    name: ['', Validators.required],
    location: ['', Validators.required],
    storageTypeId: ['', Validators.required],
    isActive: [true],
  });

  storageTypeList: StorageType[] = [];

  storage: Storage = new Storage();
  countries: { id: number, name: string }[] = [];

  constructor(
    private storageService: StorageService,
    private dialogRef: MatDialogRef<CreateStorageComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Storage,
    private toast: ToastrService,
    private fb: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.setStorage();
    this.loadStorageType();
  }

  setStorage() {
    this.storage = this.data;
    if (this.storage.id) {
      this.storageForm.reset({
        ...this.storage,
      });
    }
  }

  loadStorageType() {
    this.storageService.getAllStoragesType().subscribe({
      next: (response: StorageType[]) => {
        this.storageTypeList = response;
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  accept(): void {
    const formValue = { ...this.storageForm.value };
    if (this.storageForm.invalid) {//validamos que no haya error en los campos
      this.storageForm.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }
    this.storage = formValue;
    (this.storage.id) ? this.updateStorage() : this.saveStorage();
  }

  saveStorage(): void {
    this.storageService.addStorage(this.storage).subscribe({
      next: (response: Storage) => {
        this.toast.success('storage created successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  updateStorage(): void {
    this.storageService.updateStorage(this.storage).subscribe({
      next: (response: Storage) => {
        this.toast.success('Storage updated successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  closeModal(status?: boolean) {
    this.storageForm.reset();
    this.dialogRef.close(status);
  }

  ErrorRequiredMsg(field: string): string {
    const errors = this.storageForm.get(field)?.errors;
    const touch = this.storageForm.get(field)?.touched;

    if ((errors?.['required']) && touch) {
      return `Campo requerido`
    }
    return '';
  }
}
