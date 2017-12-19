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

import { CalendarModule } from 'angular-calendar';

// MATERIAL DESIGN
import {MatCardModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule,
    MatCardModule,
    HttpModule
  ],
  declarations: [
    DashboardComponent,
    LayoutComponent,
    SidebarLeftComponent,
    SidebarRightComponent,
    ScheduleComponent,
    NewsComponent,
    NavbarComponent
  ],
  exports: [
    DashboardComponent
  ],
  providers: [
    UserLoggedGuard,
    ScheduleService
  ]
})
export class DashboardModule { }
