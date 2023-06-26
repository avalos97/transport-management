import { Component } from '@angular/core';
import { CreateStorageComponent } from '../create-storage/create-storage.component';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { TableColumn } from 'src/app/shared/interface/table-column';
import { StorageService } from '../service/storage.service';
import { Storage } from '../interface/storage';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-storage-list',
  templateUrl: './storage-list.component.html',
  styleUrls: ['./storage-list.component.scss']
})
export class StorageListComponent {

  dialogRef!: MatDialogRef<CreateStorageComponent, any>;

  columns: TableColumn[] = [
    { name: 'name', header: 'Nombre de almacen' },
    { name: 'location', header: 'Locación' },
    { name: 'storageTypeName', header: 'Tipo de almacenamiento' },
    { name: 'isActive', header: 'Estado' },
  ];
  storages: Storage[] = [];

  constructor(private storageService: StorageService,
    private dialog: MatDialog,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.getStorage();
  }


  getStorage(): void {
    this.storageService.getAllStorages().subscribe({
      next: (response: Storage[]) => {
        this.storages = response;
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


  openDialog(storage?: Storage): void {
    let storageData = storage ?? new Storage();
    this.dialogRef = this.dialog.open(CreateStorageComponent, {
      maxWidth: '94vw',
      width: '850px',
      height: '85%',
      data: storageData
    });

    this.dialogRef.afterClosed().subscribe(estado => {
      if (estado) this.getStorage();
    });
  }

  public deletWrapper = (id: any) => {
    this.deleteRegister(id);
  }

  deleteRegister(id: number): void {
    this.storageService.delete(id).subscribe({
      next: (response: any) => {
        this.getStorage();
        this.toast.info('Producto eliminado', 'Info');
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }
}
