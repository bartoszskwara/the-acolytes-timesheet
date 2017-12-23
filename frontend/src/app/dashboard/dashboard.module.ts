import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpModule } from '@angular/http';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardRoutingModule } from './dashboard.routing';
import { LayoutComponent } from './layout/layout.component';
import { SidebarLeftComponent } from './sidebar-left/sidebar-left.component';

import { UserLoggedGuard } from '../security/guard/user-logged.guard';
import { SidebarRightComponent } from './sidebar-right/sidebar-right.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { NewsComponent } from './news/news.component';
import { NavbarComponent } from './navbar/navbar.component';

import { ScheduleService } from './schedule/schedule.service';
import { NewsService } from './news/news.service';
import { NavbarService } from './navbar/navbar.service';

import { MomentModule } from 'angular2-moment';
import {CalendarComponent} from "ap-angular2-fullcalendar/src/calendar/calendar";

// MATERIAL DESIGN
import {MatCardModule} from '@angular/material/card';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { UpcomingEventComponent } from './sidebar-right/upcoming-event/upcoming-event.component';

@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule,
    HttpModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatMenuModule,
    MatButtonModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatExpansionModule,
    MatButtonToggleModule,
    MomentModule
  ],
  declarations: [
    CalendarComponent,
    DashboardComponent,
    LayoutComponent,
    SidebarLeftComponent,
    SidebarRightComponent,
    ScheduleComponent,
    NewsComponent,
    NavbarComponent,
    UpcomingEventComponent
  ],
  exports: [
    DashboardComponent
  ],
  providers: [
    UserLoggedGuard,
    ScheduleService,
    NewsService,
    NavbarService
  ]
})
export class DashboardModule { }
