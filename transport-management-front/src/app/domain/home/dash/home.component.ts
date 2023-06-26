import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { StorageService } from '../../storage/service/storage.service';
import { Storage } from '../../storage/interface/storage';
import { Shipment } from '../../shipments/interface/shipment';
import { ShipmentService } from '../../shipments/service/shipment.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  displayedColumns: string[] = ['tipoEnvio', 'cliente', 'numeroGuia', 'precioFinal','fechaEnvio','bodega', 'Completar'];

  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [

          { title: 'Card 1', cols: 2, rows: 1, generalCols: 2 },
          { title: 'Card 2', cols: 2, rows: 1, generalCols: 2 },
          { title: 'Card 3', cols: 2, rows: 2, generalCols: 2 },
        ];
      }

      return [
        { title: 'Card 1', cols: 1, rows: 1, generalCols: 2 },
        { title: 'Card 2', cols: 1, rows: 1, generalCols: 2 },
        { title: 'Card 3', cols: 2, rows: 3, generalCols: 2 },
      ];
    })
  );

  warehousesAvailable: number = 0;
  totalWarehouses: number = 0;
  pendingShipments: number = 0;

  shipmentsList:Shipment[] = [];

  constructor(
    private breakpointObserver: BreakpointObserver,
    private storageService: StorageService,
    private toast: ToastrService,
    private shipmentService: ShipmentService) { }

  ngOnInit(): void {
    this.loadStorages();
    this.loadShipments();
  }

  loadStorages() {
    this.storageService.getAllStorages().subscribe((data: any) => {
      this.totalWarehouses = data.length;
      this.warehousesAvailable = data.filter((storage: Storage) => storage.isActive).length;
    }
    );
  }

  loadShipments() {
    this.shipmentService.getAllShipments().subscribe((data: any) => {
      this.shipmentsList = data;
      this.pendingShipments = data.filter((shipment: Shipment) => shipment.isActive).length;
      this.shipmentsList = this.shipmentsList.filter((shipment: Shipment) => shipment.isActive);
    });
  }

  complete(shipment: Shipment) {
    shipment.isActive = false;
    this.shipmentService.updateShipment(shipment).subscribe({
      next: (response: Shipment) => {
        this.toast.success('El envio se completo', 'Success');
        this.loadShipments();
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

}
