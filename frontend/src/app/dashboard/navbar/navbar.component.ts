import { Component, OnInit } from '@angular/core';
import { SafeUrl } from '@angular/platform-browser';
import { UserService } from '../../service/user/user.service';
import { NavbarService } from './navbar.service';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  loadingAvatar: boolean = false;
  loadingPoints: boolean = false;
  avatar: SafeUrl = null;
  points: number = 0;

  constructor(private userService: UserService,
              private navbarService: NavbarService) { }

  ngOnInit() {
    this.getUserAvatar();
    this.getUserPoints();
  }

  getUserAvatar() : void {
    this.loadingAvatar = true;
    if(this.userService.getCurrentUser() == null) {
      return;
    }
    this.navbarService.getUserAvatar(this.userService.getCurrentUser().getId())
      .subscribe(
        response => {
          this.avatar = response;
          console.log(response);
        },
        error => {
          console.log(error);
          console.log('error avatar');
        },
        () => {
          this.loadingAvatar = false;
        }
      );
  }

  getUserPoints() {
    this.loadingPoints = true;
    if(this.userService.getCurrentUser() == null) {
      return;
    }
    this.navbarService.getUserPoints(this.userService.getCurrentUser().getId())
      .subscribe(
        response => {
          this.points = response.total;
          console.log(response);
        },
        error => {
          console.log(error);
          console.log('error avatar');
        },
        () => {
          this.loadingPoints = false;
        }
      );
  }

}
