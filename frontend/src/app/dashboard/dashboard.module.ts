import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { DashboardRoutingModule } from './dashboard.routing';
import { LayoutComponent } from './layout/layout.component';
import { SidebarLeftComponent } from './sidebar-left/sidebar-left.component';

@NgModule({
  imports: [
    CommonModule,
    DashboardRoutingModule
  ],
  declarations: [
    DashboardComponent,
    LayoutComponent,
    SidebarLeftComponent
  ],
  exports: [
    DashboardComponent
  ]
})
export class DashboardModule { }
