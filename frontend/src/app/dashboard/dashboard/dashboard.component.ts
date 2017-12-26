import { Component, OnInit } from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {IAmPresentComponent} from '../i-am-present/i-am-present.component';

@Component({
  selector: 'dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  ngOnInit() {
    if(window.navigator && window.navigator.geolocation){
      this.openIAmPresentComponent();
    }
  }

  openIAmPresentComponent(): void {
    let dialogRef = this.dialog.open(IAmPresentComponent, {
    
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
    });
  }

}
