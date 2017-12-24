import { Component, OnInit, ViewChild } from '@angular/core';
import { ScheduleService } from './schedule.service'
import { UserService } from '../../service/user/user.service'
import * as moment from 'moment';
import { CalendarComponent } from "ap-angular2-fullcalendar/src/calendar/calendar";

@Component({
  selector: 'schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss']
})
export class ScheduleComponent implements OnInit {

  private schedules: Object[] = [];
  @ViewChild(CalendarComponent) myCalendar: CalendarComponent;

  calendarOptions: any = {
        fixedWeekCount : false,
        defaultDate: new Date(),
        editable: true,
        eventLimit: true,
        events: this.schedules,
        timeFormat: 'H:mm',
        header: {
          left: '',
          center: 'title',
          right: 'month,agendaWeek,agendaDay,today listYear prev,next '
        }
    };

  constructor(private scheduleService : ScheduleService,
              private userService : UserService) {}

  ngOnInit() {

    console.log('init');
    console.log(this.myCalendar)

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
                start: moment.utc(response.schedule[i].startDate, 'YYYY-MM-DD HH:mm:ss Z').local().format('YYYY-MM-DD HH:mm:ss'),
                end: moment.utc(response.schedule[i].endDate, 'YYYY-MM-DD HH:mm:ss Z').local().format('YYYY-MM-DD HH:mm:ss')
              }
            )
          }
        },
        error => {
          console.log(error);
          console.log('error');
        },
        () => {
          console.log('end, see schedules: ');
          console.log(this.schedules);
          this.calendarOptions.events = this.schedules;
          this.myCalendar.fullCalendar('renderEvents', this.schedules);
        }
      );
  }



}
