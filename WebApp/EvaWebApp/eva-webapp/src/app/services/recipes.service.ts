import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response, Headers} from '@angular/http';
import {Observable} from "rxjs/Observable";
import {RecipeDto} from "../dtos/RecipeDto";
import 'rxjs/add/operator/map';
import {AddRecipeDto} from "../dtos/AddRecipeDto";

@Injectable()
export class RecipesService {

  headers : Headers;
  options : RequestOptions;

  constructor(private http: Http) {
    this.headers = new Headers({ 'Content-Type': 'application/json',
      'Accept': 'application/json' });
    this.options = new RequestOptions({ headers: this.headers });
  }

  public getRecipes(): Observable<RecipeDto> {
    return this.http.get("http://localhost:8080/getallrecipes").map((response : Response) => response.json());
  }

  public addRecipe(recipe : AddRecipeDto) {
    return this.http.post("http://localhost:8080/addrecipe", JSON.stringify(recipe), this.options);
  }
}
