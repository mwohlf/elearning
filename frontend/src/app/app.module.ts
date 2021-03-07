import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {LayoutModule} from '@angular/cdk/layout';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {FooterComponent} from './footer/footer.component';
import {HeaderComponent} from './header/header.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HomeComponent} from './home/home.component';
import {TopicsComponent} from './topics/topics.component';
import {StartComponent} from './start/start.component';
import {MatButtonModule} from "@angular/material/button";
import { TutorialComponent } from './tutorial/tutorial.component';
import { LoginComponent } from './login/login.component';
import { CookiesComponent } from './cookies/cookies.component';
import { AboutComponent } from './about/about.component';
import { GdprComponent } from './gdpr/gdpr.component';
import { ErrorComponent } from './error/error.component';

@NgModule({
    declarations: [
        AppComponent,
        FooterComponent,
        HeaderComponent,
        HomeComponent,
        TopicsComponent,
        StartComponent,
        TutorialComponent,
        LoginComponent,
        CookiesComponent,
        AboutComponent,
        GdprComponent,
        ErrorComponent
    ],
    imports: [
        BrowserModule,
        LayoutModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatButtonModule,
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
