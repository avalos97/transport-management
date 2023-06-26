import { Component } from '@angular/core';
import { TableColumn } from 'src/app/shared/interface/table-column';
import { ProductService } from '../service/product.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Product } from '../interface/product';
import { CreateProductComponent } from '../create-product/create-product.component';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss']
})
export class ProductListComponent {

  dialogRef!: MatDialogRef<CreateProductComponent, any>;

  columns: TableColumn[] = [
    { name: 'productType', header: 'Tipo de producto' },
    { name: 'description', header: 'Descripcion' },
    { name: 'isActive', header: 'Estado' },
  ];
  products: Product[] = [];

  constructor(private productService: ProductService,
    private dialog: MatDialog,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.getProducts();
  }


  getProducts(): void {
    this.productService.getAllProducts().subscribe({
      next: (response: Product[]) => {
        this.products = response;
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }

  public openDialogWrapper = (row: any) => {
    this.openDialog(row);
  }

  public deletWrapper = (id: any) => {
    this.deleteRegister(id);
  }

  deleteRegister(id: number): void {
    this.productService.delete(id).subscribe({
      next: (response: any) => {
        this.getProducts();
        this.toast.info('Producto eliminado', 'Info');
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }
  

  openDialog(product?: Product): void {
    let productData = product ?? new Product();
    this.dialogRef = this.dialog.open(CreateProductComponent, {
      maxWidth: '94vw',
      width: '850px',
      height: '85%',
      data: productData
    });

    this.dialogRef.afterClosed().subscribe(estado => {
      if (estado) this.getProducts();
    });
  }
}
