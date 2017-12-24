import { Component, OnInit } from '@angular/core';
import { UpcomingScheduleService } from './upcoming-schedule.service'
import { Schedule } from '../../../model/schedule/Schedule';
import * as moment from 'moment';
import { UserService } from '../../../service/user/user.service'

@Component({
  selector: 'upcoming-schedule',
  templateUrl: './upcoming-schedule.component.html',
  styleUrls: ['./upcoming-schedule.component.scss']
})
export class UpcomingScheduleComponent implements OnInit {

  private schedule: Schedule = null;
  private loading: boolean = false;

  constructor(private upcomingScheduleService: UpcomingScheduleService,
              private userService: UserService) { }

  ngOnInit() {
    this.getUpcomingSchedule();
  }

  getUpcomingSchedule(): void {
    this.loading = true;
    console.log('start upcoming schedule');
    this.upcomingScheduleService.getUpcomingSchedule(this.userService.getCurrentUser().getId())
      .subscribe(
        response => {
          this.schedule = {
            id: response.upcomingSchedule.id,
            user: response.upcomingSchedule.user,
            event: response.upcomingSchedule.event
          }
        },
        error => {
          console.log('error upcoming schedule');
          console.log(error);
          console.log('error');
        },
        () => {
          this.loading = false;
          console.log('end upcoming schedule startdate:');
          console.log(this.schedule.event.startDate);
        }
      );
  }

}
