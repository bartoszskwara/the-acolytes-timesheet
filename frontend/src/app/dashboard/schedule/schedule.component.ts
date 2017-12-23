import { Component, OnInit } from '@angular/core';
import { ScheduleService } from './schedule.service'
import { UserService } from '../../service/user/user.service'
import { Schedule } from '../../model/schedule/Schedule'
import { CustomDateFormatter } from './custom-date-formatter.provider';
import { CustomEventTitleFormatter } from './custom-event-title-formatter.provider';
import { Subject } from 'rxjs/Subject';
import {
  startOfDay,
  endOfDay,
  subDays,
  addDays,
  endOfMonth,
  isSameDay,
  isSameMonth,
  addHours
} from 'date-fns';
import {
  CalendarEvent,
  CalendarEventAction,
  CalendarEventTimesChangedEvent,
  CalendarDateFormatter,
  CalendarEventTitleFormatter
} from 'angular-calendar';
import * as moment from 'moment';

@Component({
  selector: 'schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.scss'],
  providers: [
    {
      provide: CalendarDateFormatter,
      useClass: CustomDateFormatter
    },
    {
      provide: CalendarEventTitleFormatter,
      useClass: CustomEventTitleFormatter
    }
  ]
})
export class ScheduleComponent implements OnInit {

  view: string = 'month';
  viewDate: Date = new Date();

  actions: CalendarEventAction[] = [
      {
        label: '<i class="fa fa-fw fa-pencil"></i>',
        onClick: ({ event }: { event: CalendarEvent }): void => {
          this.handleEvent(event);
        }
      },
      {
        label: '<i class="fa fa-fw fa-times"></i>',
        onClick: ({ event }: { event: CalendarEvent }): void => {
          this.events = this.events.filter(iEvent => iEvent !== event);
          this.handleEvent(event);
        }
      }
    ];

  events: CalendarEvent[] = [];
  refresh: Subject<any> = new Subject();

  private activeDayIsOpen: boolean = false;
  private schedules: CalendarEvent[] = [];

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
                  end: moment.utc(response.schedule[i].endDate, 'YYYY-MM-DD HH:mm:ss').local().toDate(),
                  color: {
                    "primary": "rgb(117, 169, 255)",
                    "secondary": "rgba(117, 169, 255, 0.05)"
                  },
                  draggable: false,
                  resizable: {
                    beforeStart: false,
                    afterEnd: false
                  },
                  actions: this.actions
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
          this.refresh.next();
          console.log(this.schedules);
        }
      );
  }

  dayClicked({ date, events }: { date: Date; events: CalendarEvent[] }): void {
    if (isSameMonth(date, this.viewDate)) {
      if ((isSameDay(this.viewDate, date) && this.activeDayIsOpen === true) || events.length === 0) {
        this.activeDayIsOpen = false;
      } else {
        this.activeDayIsOpen = true;
        this.viewDate = date;
      }
    }
  }

  handleEvent(event: CalendarEvent): void {
    console.log('handle event');
    console.log(event);
  }

}
