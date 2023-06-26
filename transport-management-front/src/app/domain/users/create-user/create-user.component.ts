import { Component, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { User } from '../interface/user';
import { UserService } from '../service/user.service';
import { ToastrService } from 'ngx-toastr';
import { Role } from '../interface/role';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent {

  roleList: Role[] = [
    { id: 1, name: 'ADMIN' },
    { id: 2, name: 'USER' },
  ];

  userForm: FormGroup = this.fb.group({
    id: [null],
    name: ['', Validators.required],
    username: ['', Validators.required],
    password: ['', Validators.required],
    isActive: [true],
    rolesId: ['', Validators.required]
  });

  user: User = new User();

  constructor(
    private userService: UserService,
    private dialogRef: MatDialogRef<CreateUserComponent>,
    @Inject(MAT_DIALOG_DATA) private data: User,
    private toast: ToastrService,
    private fb: FormBuilder,
  ) {
  }

  ngOnInit(): void {
    this.setUser();
  }

  setUser() {
    this.user = this.data;
    if (this.user.id) {
      this.userForm.reset({
        ...this.user,
      });
    }
  }

  accept(): void {
    const formValue = { ...this.userForm.value };
    if (this.userForm.invalid) {//validamos que no haya error en los campos
      this.userForm.markAllAsTouched();//simulamos haber tocado los campos para mostrar los errores
      return;
    }
    
    this.user = formValue;
    (this.user.id) ? this.updateUser() : this.saveUser();
  }

  saveUser(): void {
    this.userService.addUser(this.user).subscribe({
      next: (response: User) => {
        this.toast.success('user created successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  updateUser(): void {
    if (this.user.password!.length > 15) {
      this.user.password = '';
    }

    this.userService.updateUser(this.user).subscribe({
      next: (response: User) => {
        this.toast.success('User updated successfully', 'Success');
        this.closeModal(true);
      },
      error: (error) => {
        this.toast.error(error.error.message, 'Error');
      }
    }
    );
  }

  closeModal(status?: boolean) {
    this.userForm.reset();
    this.dialogRef.close(status);
  }

  ErrorRequiredMsg(field: string): string {
    const errors = this.userForm.get(field)?.errors;
    const touch = this.userForm.get(field)?.touched;

    if ((errors?.['required']) && touch) {
      return `Campo requerido`
    }
    return '';
  }

}
