import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login-component.html',
  styleUrls: ['./login-component.css']
})
export class LoginComponent implements OnInit {

  username = '';
  password = '';
  showAlert = true;

  constructor(private router : Router) { }

  ngOnInit() {
  }

  login(username: string, password: string){
    if(username === "matthias" && password === "1234"){
      this.router.navigate(['/home']);
    } else {
      console.log(this.showAlert)
      this.showAlert = false;
      console.log(this.showAlert)
    }
  }
}
