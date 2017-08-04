import {Component, Input, OnInit} from '@angular/core';
import {RecipesService} from "../services/recipes.service";
import {AddRecipeDto} from "../dtos/AddRecipeDto";
import {UsersService} from "../services/user/users.service";
import {LoginDto} from "../dtos/LoginDto";
import {EvaUserDto} from "../dtos/EvaUserDto";
import {RecipeDto} from "../dtos/RecipeDto";
import {log} from "util";

@Component({
  selector: 'app-mijn-recepten',
  templateUrl: './mijn-recepten.component.html',
  styleUrls: ['./mijn-recepten.component.css']
})
export class MijnReceptenComponent implements OnInit {

  recipe : RecipeDto;

  myRecipe: AddRecipeDto;

  displayedRecipes : RecipeDto[];

  mijnReceptenbool = true;
  favoReceptenbool = false;
  receptenZoekenbool = false;

  loggedUser : EvaUserDto = new EvaUserDto;

  recipesService;
  usersService;

  @Input()
  recipeSelected = false;

  constructor(recipesService: RecipesService, usersService : UsersService) {
    this.recipesService = recipesService;
    this.usersService = usersService;
  }

  ngOnInit() {
    this.usersService.getUser(JSON.parse(localStorage.getItem("myUser")).id).subscribe(result => {
      this.loggedUser = result;
      this.displayedRecipes = this.loggedUser.myRecipes;
    });
  }

  receptZoeken(){
    console.log("ik zoek een recept");
    this.mijnReceptenbool = false;
    this.favoReceptenbool = false;
    this.receptenZoekenbool = true;
    this.refreshUser();
  }

  favorieteRecepten(){
    console.log("dit zijn de recepten die ik leuk vind");
    this.mijnReceptenbool = false;
    this.favoReceptenbool = true;
    this.receptenZoekenbool = false;
    this.usersService.getUser(JSON.parse(localStorage.getItem("myUser")).id).subscribe(result => {
      this.loggedUser = result;
      this.displayedRecipes = this.loggedUser.favoriteRecipes;
    });
  }

  mijnRecepten(){
    console.log("dit zijn mijn recepten");
    this.mijnReceptenbool = true;
    this.favoReceptenbool = false;
    this.receptenZoekenbool = false;
    this.usersService.getUser(JSON.parse(localStorage.getItem("myUser")).id).subscribe(result => {
      this.loggedUser = result;
      this.displayedRecipes = this.loggedUser.myRecipes;
    });
  }

  selectRecipe(recipe){
    this.recipe = recipe;
    this.recipeSelected = true;
  }

  selectedRecipeReceiver(event){
    this.recipeSelected = event.valueOf();
  }

  refreshUser(){
    console.log("refresh");
    this.usersService.getUser(JSON.parse(localStorage.getItem("myUser")).id).subscribe(result => this.loggedUser = result);
  }

  test(){
    console.log(JSON.parse(localStorage.getItem("myUser")));
  }
}
