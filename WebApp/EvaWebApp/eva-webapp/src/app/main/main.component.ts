import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  constructor(private router : Router) {
    if(localStorage.getItem("loginValidated") == null || localStorage.getItem("loginValidated") === "false") {
      this.router.navigate(['/login']);
    }
  }

  ngOnInit() {
  }

  getUser(){
    console.log(JSON.parse(localStorage.getItem("myUser")));
  }

  logout(){
    localStorage.setItem("loginValidated", "false");
    localStorage.setItem("myUser", null);
    this.router.navigate(['/login']);
  }
}
