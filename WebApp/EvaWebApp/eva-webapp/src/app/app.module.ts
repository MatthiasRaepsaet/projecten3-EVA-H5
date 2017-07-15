import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent} from './login/login-component';
import {RouterModule} from "@angular/router";
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { MijnReceptenComponent } from './mijn-recepten/mijn-recepten.component';
import { InfoComponent } from './info/info.component';
import { AlertComponent } from './alert/alert.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent,
    MijnReceptenComponent,
    InfoComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'home',
        component: MainComponent
      }
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
