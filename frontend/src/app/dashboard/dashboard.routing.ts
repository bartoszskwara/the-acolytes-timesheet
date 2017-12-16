import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { LayoutComponent } from './layout/layout.component';
import { SidebarLeftComponent } from './sidebar-left/sidebar-left.component';

import { UserLoggedGuard } from '../security/guard/user-logged.guard'

const dashboardRoutes: Routes = [
  { path: 'app',  component: DashboardComponent,
    canActivate: [UserLoggedGuard],
    children: [
      {path: '', redirectTo: 'home', pathMatch: 'full'},
      {path: 'home', component: LayoutComponent}
    ]
  }
];
@NgModule({
  imports: [ RouterModule.forChild(dashboardRoutes) ],
  exports: [ RouterModule ]
})
export class DashboardRoutingModule {}
