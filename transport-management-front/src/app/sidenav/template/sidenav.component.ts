import { Component, ElementRef, OnInit } from '@angular/core';
import { TokenService } from 'src/app/core/auth/service/token.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit {

  isOpen: boolean = true;
  username: string = '';
  constructor(private tokenService: TokenService) { }
  ngOnInit(): void {
    this.username = this.tokenService.getUsername();
  }

  opened() {
    this.isOpen = !this.isOpen;
  }

  logout() {
    this.tokenService.logOut();
  }
}
