import { Component, OnInit } from '@angular/core';
import { UpcomingEventService } from './upcoming-event.service'
import { Event } from '../../../model/event/Event';
import * as moment from 'moment';

@Component({
  selector: 'upcoming-event',
  templateUrl: './upcoming-event.component.html',
  styleUrls: ['./upcoming-event.component.scss']
})
export class UpcomingEventComponent implements OnInit {

  private event: Event = null;
  private loading: boolean = false;

  constructor(private upcomingEventService: UpcomingEventService) { }

  ngOnInit() {
    this.getUpcomingEvent();
  }

  getUpcomingEvent(): void {
    this.loading = true;
    this.upcomingEventService.getUpcomingEvent()
      .subscribe(
        response => {
          this.event = response.upcomingEvent;
        },
        error => {
          console.log(error);
          console.log('error');
        },
        () => {
          this.loading = false;
          console.log('end');
          console.log(this.event);
        }
      );
  }

}
