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
    return this.http.post("http://localhost:8080/login", JSON.stringify(loginDto), this.options).map((response : Response) => response.json());
  }

  public addUser(user : RegisterDto) {
    return this.http.post("http://localhost:8080/adduser", JSON.stringify(user), this.options);
  }

  public getUser(userId : number):  Observable<EvaUserDto>{
    return this.http.get("http://localhost:8080/getuser?userId=" + userId).map((response: Response) => response.json());
  }
}
