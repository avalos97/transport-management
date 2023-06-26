import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { CreateClientComponent } from '../create-client/create-client.component';
import { Client } from '../interface/client';
import { ClientService } from '../service/client.service';
import { TableColumn } from 'src/app/shared/interface/table-column';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {

  dialogRef!: MatDialogRef<CreateClientComponent, any>;

  columns: TableColumn[] = [
    { name: 'name', header: 'Nombre' },
    { name: 'country', header: 'Pais' },
    { name: 'address', header: 'Direccion' },
    { name: 'email', header: 'Email' },
    { name: 'isActive', header: 'Estado' },
  ];
  clients: Client[] = [];

  constructor(private clientService: ClientService,
    private dialog: MatDialog,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.getClients();
  }

  getClients(): void {
    this.clientService.getAllClients().subscribe({
      next: (response: Client[]) => {
        this.clients = response;
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


  openDialog(room?: Client): void {
    let clientData = room ?? new Client();
    this.dialogRef = this.dialog.open(CreateClientComponent, {
      maxWidth: '94vw',
      width: '850px',
      height: '85%',
      disableClose: true,
      data: clientData
    });

    this.dialogRef.afterClosed().subscribe(estado => {
      if (estado) this.getClients();
    });
  }

  public deletWrapper = (id: any) => {
    this.deleteRegister(id);
  }

  deleteRegister(id: number): void {
    this.clientService.delete(id).subscribe({
      next: (response: any) => {
        this.getClients();
        this.toast.info('Cliente eliminado', 'Info');
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }
}
