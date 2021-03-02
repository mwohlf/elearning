import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StartComponent} from "./start/start.component";

const routes: Routes = [

    {
        // this is the entry point, also configured in keycloak as post auth redirect
        path: 'start', component: StartComponent,
        // canActivate: [AuthGuard],
    },


    // redirects:
    {path: '', redirectTo: '/start', pathMatch: 'full'},
    {path: '**', redirectTo: '/error'},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
