import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductService } from '../service/product.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Product } from '../interface/product';

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.scss']
})
export class CreateProductComponent {

  productForm: FormGroup = this.fb.group({
    id: [null],
    productType: ['', Validators.required],
    description: ['', Validators.required],
    isActive: [true],
  });

  product: Product = new Product();
  countries: { id: number, name: string }[] = [];

  constructor(
    private productService: ProductService,
    private dialogRef: MatDialogRef<CreateProductComponent>,
    @Inject(MAT_DIALOG_DATA) private data: Product,
    private toast: ToastrService,
    private fb: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.setRoom();
  }

  setRoom() {
    this.product = this.data;
    if (this.product.id) {
      this.productForm.reset({
        ...this.product,
      });
    }
  }

  accept(): void {
    const formValue = { ...this.productForm.value };
    if (this.productForm.invalid) {//validamos que no haya error en los campos
      this.productForm.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }
    this.product = formValue;
    (this.product.id) ? this.updateProduct() : this.saveProduct();
  }

  saveProduct(): void {
    this.productService.addProduct(this.product).subscribe({
      next: (response: Product) => {
        this.toast.success('product created successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  updateProduct(): void {
    this.productService.updateProduct(this.product).subscribe({
      next: (response: Product) => {
        this.toast.success('Product updated successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  closeModal(status?: boolean) {
    this.productForm.reset();
    this.dialogRef.close(status);
  }

  ErrorRequiredMsg(field: string): string {
    const errors = this.productForm.get(field)?.errors;
    const touch = this.productForm.get(field)?.touched;

    if ((errors?.['required']) && touch) {
      return `Campo requerido`
    }
    return '';
  }

}
