import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {UsersService} from "../services/user/users.service";
import {LoginDto} from "../dtos/LoginDto";
import {EvaUserDto} from "../dtos/EvaUserDto";

@Component({
  selector: 'app-login',
  templateUrl: './login-component.html',
  styleUrls: ['./login-component.css']
})
export class LoginComponent implements OnInit {

  evaUser : EvaUserDto = new EvaUserDto;

  showAlert = true;
  usersService;

  constructor(private router : Router, usersService : UsersService) {
    this.usersService = usersService;
    console.log(JSON.parse(localStorage.getItem("myUser")));
    console.log(localStorage.getItem("loginValidated"));
  }

  ngOnInit() {
  }

  login(username: string, password: string){
    localStorage.setItem("myUser", null);
    let userLogin : LoginDto = {username : username, password : password};
    this.usersService.loginUser(userLogin).subscribe(result => {
      this.evaUser = result;
      if(this.evaUser.id !== 0){
        this.router.navigate(['/home']);
        console.log(JSON.parse(localStorage.getItem("myUser")));
        localStorage.setItem("myUser", JSON.stringify(this.evaUser));
        localStorage.setItem("loginValidated", "true");
      } else if(this.evaUser.id === 0){
        this.showAlert = false;
      }
    });
  }
}
