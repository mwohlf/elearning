import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StartComponent} from "./start/start.component";
import {TopicsComponent} from "./topics/topics.component";
import {TutorialComponent} from "./tutorial/tutorial.component";
import {LoginComponent} from "./login/login.component";
import {CookiesComponent} from "./cookies/cookies.component";
import {AboutComponent} from "./about/about.component";
import {GdprComponent} from "./gdpr/gdpr.component";
import {ErrorComponent} from "./error/error.component";

const routes: Routes = [

    {
        path: 'start', component: StartComponent,
    },

    {
        path: 'tutorial', component: TutorialComponent,
    },

    {
        path: 'topics', component: TopicsComponent,
    },

    {
        path: 'login', component: LoginComponent,
    },

    {
        path: 'cookies', component: CookiesComponent,
    },

    {
        path: 'about', component: AboutComponent,
    },

    {
        path: 'gdpr', component: GdprComponent,
    },

    {
        path: 'error', component: ErrorComponent,
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
