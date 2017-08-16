import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {Router} from "@angular/router";


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(private router : Router){
  }

  title = 'app';

  getUser(){
    console.log(JSON.parse(localStorage.getItem("myUser")));
  }

  logout(){
    localStorage.setItem("loginValidated", "false");
    localStorage.setItem("myUser", null);
    this.router.navigate(['/login']);
  }
}
