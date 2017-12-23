import { Component, OnInit } from '@angular/core';
import { ScheduleService } from './schedule.service'
import { UserService } from '../../service/user/user.service'
import { Schedule } from '../../model/schedule/Schedule'


import { Subject } from 'rxjs/Subject';

import * as moment from 'moment';

@Component({
  selector: 'schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {

  private schedules: Object[] = [];

  calendarOptions:Object = {
        height: 'auto',
        fixedWeekCount : false,
        defaultDate: new Date(),
        editable: true,
        eventLimit: true,
        events: this.schedules,
        timeFormat: 'H:mm'
    };

  constructor(private scheduleService : ScheduleService,
              private userService : UserService) {}

  ngOnInit() {
    this.getScheduleByUserId(this.userService.getCurrentUser().getId());
  }

  private getScheduleByUserId(userId: number): void {
    this.scheduleService.getScheduleByUserId(userId)
      .subscribe(
        response => {
          console.log(response);
          for (var i in response.schedule) {
            this.schedules.push(
              {
                title: response.schedule[i].activity.name,
                start: moment.utc(response.schedule[i].startDate, 'YYYY-MM-DD HH:mm:ss').local().toDate(),
                end: moment.utc(response.schedule[i].endDate, 'YYYY-MM-DD HH:mm:ss').local().toDate()
              }
            )
          }


        },
        error => {
          console.log(error);
          console.log('error');
        },
        () => {
          console.log('end');

        }
      );
  }



}
