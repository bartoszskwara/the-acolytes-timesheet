import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardRoutingModule } from './dashboard.routing';
import { LayoutComponent } from './layout/layout.component';
import { SidebarLeftComponent } from './sidebar-left/sidebar-left.component';

import { UserLoggedGuard } from '../security/guard/user-logged.guard';
import { SidebarRightComponent } from './sidebar-right/sidebar-right.component';
import { ScheduleComponent } from './schedule/schedule.component';
import { NewsComponent } from './news/news.component';
import { NavbarComponent } from './navbar/navbar.component';


@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule
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
    UserLoggedGuard
  ]
})
export class DashboardModule { }
