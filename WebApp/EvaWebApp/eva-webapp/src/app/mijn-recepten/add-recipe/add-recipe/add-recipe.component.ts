import { Component, OnInit } from '@angular/core';
import {AddRecipeDto} from "../../../dtos/AddRecipeDto";
import {AddIngredientDto} from "../../../dtos/AddIngredientDto";
import {RecipesService} from "../../../services/recipes.service";

@Component({
  selector: 'app-add-recipe',
  templateUrl: './add-recipe.component.html',
  styleUrls: ['./add-recipe.component.css']
})
export class AddRecipeComponent implements OnInit {

  constructor(private recipeService : RecipesService) { }

  ngOnInit() {
  }

  addNewRecipe(titel, beschrijving, category, ingredienten){
    let newRecipe : AddRecipeDto = new AddRecipeDto;
    let myIngredient : AddIngredientDto = new AddIngredientDto;
    let ingredientList : AddIngredientDto[] =  [];
    let ingredientAux;
    let fullIngredients;
    newRecipe.title = titel;
    newRecipe.description = beschrijving;
    newRecipe.category = category;
    fullIngredients = ingredienten.split(",");
    for(const ingre of fullIngredients){
      console.log(ingre);
      ingre.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
      let n =0;
      if(ingre.charAt(0) === " "){
        n = 1;
      }
      ingredientAux = ingre.split(" ");
      myIngredient.amount = ingredientAux[n + 0];
      myIngredient.metric = ingredientAux[n + 1];
      myIngredient.name = ingredientAux[n + 2];
      ingredientList.push(myIngredient);
      myIngredient = new AddIngredientDto
      ingredientAux = "";
    }
    newRecipe.ingredients = ingredientList;
    newRecipe.userId = JSON.parse(localStorage.getItem("myUser")).id;
    this.recipeService.addRecipe(newRecipe).subscribe();
  }
}
