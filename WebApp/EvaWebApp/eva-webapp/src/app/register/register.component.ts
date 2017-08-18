import { Component, OnInit } from '@angular/core';
import {UsersService} from "../services/user/users.service";
import {Router} from "@angular/router";
import {RegisterDto} from "../dtos/RegisterDto";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser : RegisterDto = new RegisterDto;

  usersService;

  constructor(usersService : UsersService, private router : Router) {
    this.usersService = usersService;
  }

  ngOnInit() {
  }

  registerAccount(firstName, lastName, username, password, email){
    this.newUser = {firstName, lastName, password, email, username};
    if(this.newUser.firstName !== null && this.newUser.lastName !== null && this.newUser.password !== null && this.newUser.username !== null && this.newUser.email !== null){
      this.usersService.addUser(this.newUser).subscribe();
    }
  }
}
