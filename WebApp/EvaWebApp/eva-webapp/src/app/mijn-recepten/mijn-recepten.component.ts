import { Component, OnInit } from '@angular/core';
import {RecipesService} from "../services/recipes.service";
import {AddRecipeDto} from "../dtos/AddRecipeDto";

@Component({
  selector: 'app-mijn-recepten',
  templateUrl: './mijn-recepten.component.html',
  styleUrls: ['./mijn-recepten.component.css']
})
export class MijnReceptenComponent implements OnInit {

  myRecipe: AddRecipeDto;

  mijnReceptenbool = true;
  favoReceptenbool = false;
  receptenZoekenbool = false;

  recipesService;

  constructor(recipesService: RecipesService) {
    this.recipesService = recipesService;
    this.myRecipe = {title : "new recipe", description : "dit is mijn nieuw recept", userId : 1, ingredients : [{id : 1, name : "rijst", amount :  200}]};
  }

  ngOnInit() {
    console.log(this.mijnReceptenbool);
    console.log(this.favoReceptenbool);
    console.log(this.receptenZoekenbool);
  }

  receptZoeken(){
    console.log("ik zoek een recept");
    this.mijnReceptenbool = false;
    this.favoReceptenbool = false;
    this.receptenZoekenbool = true;
    /*this.recipesService.getRecipes().subscribe(data => {
      console.log(data);
    });*/
  }

  favorieteRecepten(){
    console.log("dit zijn de recepten die ik leuk vind");
    this.mijnReceptenbool = false;
    this.favoReceptenbool = true;
    this.receptenZoekenbool = false;
  }

  mijnRecepten(){
    console.log("dit zijn mijn recepten");
    this.mijnReceptenbool = true;
    this.favoReceptenbool = false;
    this.receptenZoekenbool = false;
    //this.recipesService.addRecipe(this.myRecipe).subscribe( result => console.log(result));
  }
}
