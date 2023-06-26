import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { CreateUserComponent } from '../create-user/create-user.component';
import { TableColumn } from 'src/app/shared/interface/table-column';
import { User } from '../interface/user';
import { UserService } from '../service/user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {

  dialogRef!: MatDialogRef<CreateUserComponent, any>;

  columns: TableColumn[] = [
    { name: 'name', header: 'Nombre completo' },
    { name: 'username', header: 'username' },
    { name: 'isActive', header: 'Estado' },
  ];
  users: User[] = [];

  constructor(private userService: UserService,
    private dialog: MatDialog,
    private toast: ToastrService
  ) { }

  ngOnInit(): void {
    this.getUser();
  }


  getUser(): void {
    this.userService.getAllUsers().subscribe({
      next: (response: User[]) => {
        this.users = response;
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


  openDialog(user?: User): void {
    let userData = user ?? new User();
    this.dialogRef = this.dialog.open(CreateUserComponent, {
      maxWidth: '94vw',
      width: '850px',
      height: '85%',
      data: userData
    });

    this.dialogRef.afterClosed().subscribe(estado => {
      if (estado) this.getUser();
    });
  }

  public deletWrapper = (id: any) => {
    this.deleteRegister(id);
  }

  deleteRegister(id: number): void {
    this.userService.delete(id).subscribe({
      next: (response: any) => {
        this.getUser();
        this.toast.info('Producto eliminado', 'Info');
      },
      error: (erro) => {
        console.log(erro)
      }
    }
    );
  }
}
