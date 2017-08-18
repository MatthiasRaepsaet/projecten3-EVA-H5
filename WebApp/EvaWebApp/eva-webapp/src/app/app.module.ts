import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { LoginComponent} from './login/login-component';
import {RouterModule} from "@angular/router";
import { RegisterComponent } from './register/register.component';
import { MainComponent } from './main/main.component';
import { MijnReceptenComponent } from './mijn-recepten/mijn-recepten.component';
import { InfoComponent } from './info/info.component';
import { AlertComponent } from './alert/alert.component';
import {RecipesService} from "./services/recipes.service";
import { SelectedRecipeComponent } from './mijn-recepten/selected-recipe/selected-recipe.component';
import {UsersService} from "./services/user/users.service";
import { AddRecipeComponent } from './mijn-recepten/add-recipe/add-recipe/add-recipe.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    MainComponent,
    MijnReceptenComponent,
    InfoComponent,
    AlertComponent,
    SelectedRecipeComponent,
    AddRecipeComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot([
      {
        path: 'info',
        component: InfoComponent
      },
      {
        path: 'login',
        component: LoginComponent
      },
      {
        path: 'home',
        component: MainComponent
      },
      {
        path: 'register',
        component: RegisterComponent
      },
      {
        path: 'mijn-recepten',
        component: MijnReceptenComponent
      }
    ]),
    HttpModule
  ],
  providers: [RecipesService, UsersService],
  bootstrap: [AppComponent]
})

export class AppModule { }
