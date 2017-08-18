import { Injectable } from '@angular/core';
import {Http, RequestOptions, Response, Headers} from '@angular/http';
import {Observable} from "rxjs/Observable";
import {RecipeDto} from "../dtos/RecipeDto";
import 'rxjs/add/operator/map';
import {AddRecipeDto} from "../dtos/AddRecipeDto";
import {RecipeUserDto} from "../dtos/RecipeUserDto";
import {AddCommentDto} from "../dtos/AddCommentDto";

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
    return this.http.get("https://backendeva.herokuapp.com/getallrecipes").map((response : Response) => response.json());
  }

  public addRecipe(recipe : AddRecipeDto) {
    return this.http.post("https://backendeva.herokuapp.com/addrecipe", JSON.stringify(recipe), this.options);
  }
  public addFavoriteRecipe(recipe : RecipeUserDto) {
    return this.http.post("https://backendeva.herokuapp.com/favoriterecipe", JSON.stringify(recipe), this.options);
  }

  public getFavoriteRecipes(userId): Observable<RecipeDto> {
    return this.http.get("https://backendeva.herokuapp.com/getfavoriterecipes?userId=" + userId).map((response : Response) => response.json());
  }

  public addComment(comment : AddCommentDto) {
    return this.http.post("https://backendeva.herokuapp.com/addcomment", JSON.stringify(comment), this.options);
  }

  public upvoteRecipe(recipeId) {
    return this.http.post("https://backendeva.herokuapp.com/upvoterecipe?id=" + recipeId, this.options);
  }

  public downvoteRecipe(recipeId) {
    return this.http.post("https://backendeva.herokuapp.com/downvoterecipe?id=" + recipeId, this.options);
  }

  public getRecipeById(recipeId){
    return this.http.get("https://backendeva.herokuapp.com/getrecipebyid?id=" + recipeId).map((response: Response) => response.json());
  }

  public getRecipeByCategory(category){
    return this.http.get("https://backendeva.herokuapp.com/recipesbycategory?category=" + category).map((response: Response) => response.json());
  }

  public searchRecipe(search){
    return this.http.get("https://backendeva.herokuapp.com/searchrecipe?search=" + search).map((response: Response) => response.json());
  }
}
