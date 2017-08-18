import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response, Headers} from '@angular/http';
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';
import {EvaUserDto} from "../../dtos/EvaUserDto";
import {LoginDto} from "../../dtos/LoginDto";
import {RegisterDto} from "../../dtos/RegisterDto";

@Injectable()
export class UsersService {

  headers : Headers;
  options : RequestOptions;

  constructor(private http: Http) {
    this.headers = new Headers({ 'Content-Type': 'application/json',
      'Accept': 'application/json' });
    this.options = new RequestOptions({ headers: this.headers });
  }

  public loginUser(loginDto : LoginDto): Observable<EvaUserDto> {
    return this.http.post("https://backendeva.herokuapp.com/login", JSON.stringify(loginDto), this.options).map((response : Response) => response.json());
  }

  public addUser(user : RegisterDto) {
    return this.http.post("https://backendeva.herokuapp.com/adduser", JSON.stringify(user), this.options);
  }

  public getUser(userId : number):  Observable<EvaUserDto>{
    return this.http.get("https://backendeva.herokuapp.com/getuser?userId=" + userId).map((response: Response) => response.json());
  }
}
