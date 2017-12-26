import { Component, OnInit } from '@angular/core';
import { Event } from '../../model/event/Event';
import { IAmPresentService } from './i-am-present.service';
import {MatDialogRef} from '@angular/material/dialog'

@Component({
  selector: 'i-am-present',
  templateUrl: './i-am-present.component.html',
  styleUrls: ['./i-am-present.component.scss']
})
export class IAmPresentComponent implements OnInit {

  private position: Position = null;
  private events: Event[] = null;
  private loading: boolean = false;

  constructor(private iAmPresentService: IAmPresentService,
  public dialogRef: MatDialogRef<IAmPresentComponent>) { }

  ngOnInit() {
    this.loading = true;
    window.navigator.geolocation.getCurrentPosition(
      position => {
        console.log(position)
        this.position = position;
        this.checkIfThereIsAnyEventNearby(this.position);
      },
      error => {
        switch (error.code) {
          case 1:
              console.log('Permission Denied');
              break;
          case 2:
              console.log('Position Unavailable');
              break;
          case 3:
              console.log('Timeout');
              break;
        }
      });
    }

  onNoClick(): void {
    this.dialogRef.close(true);
  }

  checkIfThereIsAnyEventNearby(position: Position): void {
    this.loading = true;
    this.iAmPresentService.checkIfThereIsAnyEventNearby(position)
      .subscribe(
        response => {
          this.events = response.events;
        },
        error => {
          console.log(error);
          console.log('error');
        },
        () => {
          this.loading = false;
          console.log('end');
          console.log(this.events);
        }
      );
  }

}
