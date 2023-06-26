import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CreateShipmentComponent } from '../create-shipment/create-shipment.component';
import { TableColumn } from 'src/app/shared/interface/table-column';
import { ShipmentService } from '../service/shipment.service';
import { Shipment } from '../interface/shipment';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-shipment-list',
  templateUrl: './shipment-list.component.html',
  styleUrls: ['./shipment-list.component.scss']
})
export class ShipmentListComponent {

  dialogRef!: MatDialogRef<CreateShipmentComponent, any>;

  columns: TableColumn[] = [
    { name: 'productName', header: 'Producto' },
    { name: 'quantity', header: 'cantidad' },
    { name: 'storageName', header: 'Almacen' },
    { name: 'shippingTypeName', header: 'Tipo de almacenamiento' },
    { name: 'clientName', header: 'Cliente' },
    { name: 'guideNumber', header: 'Numero de guia' },
  ];
  shipment: Shipment[] = [];

  constructor(private shipmentService: ShipmentService,
    private dialog: MatDialog,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.getShipment();
  }


  getShipment(): void {
    this.shipmentService.getAllShipments().subscribe({
      next: (response: Shipment[]) => {
        this.shipment = response;
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }

  public deletWrapper = (id: any) => {
    this.deleteRegister(id);
  }

  deleteRegister(id: number): void {
    this.shipmentService.delete(id).subscribe({
      next: (response: any) => {
        this.getShipment();
        this.toast.info('Envio eliminado', 'Info');
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


  openDialog(shipment?: Shipment): void {
    let shipmentData = shipment ?? new Shipment();
    this.dialogRef = this.dialog.open(CreateShipmentComponent, {
      maxWidth: '94vw',
      width: '850px',
      height: '85%',
      data: shipmentData
    });

    this.dialogRef.afterClosed().subscribe(estado => {
      if (estado) this.getShipment();
    });
  }
}
