import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Router} from "@angular/router";
import {RecipeDto} from "../../dtos/RecipeDto";
import {IngredientDto} from "../../dtos/IngredientDto";
import {RecipesService} from "../../services/recipes.service";
import {RecipeUserDto} from "../../dtos/RecipeUserDto";

@Component({
  selector: 'app-selected-recipe',
  templateUrl: './selected-recipe.component.html',
  styleUrls: ['./selected-recipe.component.css']
})
export class SelectedRecipeComponent implements OnInit, OnChanges {

  @Input("recipe")
  recipe: RecipeDto;

  @Output()
  recipeSelectedEmitter : EventEmitter<boolean> = new EventEmitter<boolean>();

  ingredienten : IngredientDto[];
  relativeIngredientenList = [];

  personenCount = 2;
  singleMulti = "personen";

  constructor(private router : Router, private recipeService : RecipesService) {
    for(const ing of this.relativeIngredientenList){
      ing.amount = ing.amount*this.personenCount;
    }
    if(localStorage.getItem("loginValidated") == null || localStorage.getItem("loginValidated") === "false") {
      this.router.navigate(['/login']);
    }

  }

  ngOnChanges(changes : SimpleChanges){
    this.resetIngredientList();
    for(const ing of this.relativeIngredientenList){
      ing.amount = ing.amount*this.personenCount;
    }
  }

  ngOnInit() {

  }

  addPersonen(){
    this.resetIngredientList();
    this.personenCount++;
    if(this.personenCount === 1){
      this.singleMulti = "persoon";
    } else {
      this.singleMulti = "personen";
    }
    for(const ing of this.relativeIngredientenList){
      ing.amount = ing.amount*this.personenCount;
    }
  }

  removePersonen(){
    this.resetIngredientList();
    this.personenCount--;
    if(this.personenCount === 1 || this.personenCount === 0){
      this.singleMulti = "persoon";
    } else {
      this.singleMulti = "personen";
    }
    if(this.personenCount === 0){
      this.personenCount++;
    }
    for(const ing of this.relativeIngredientenList){
      ing.amount = ing.amount*this.personenCount;
    }
  }

  resetIngredientList(){
    this.ingredienten = this.recipe.ingredients;
    this.relativeIngredientenList = JSON.parse(JSON.stringify(this.ingredienten));
  }

  likeRecipe(){
    this.recipe.upvotes++;
  }

  dislikeRecipe(){
    this.recipe.downvotes++;
  }

  terug(){
    console.log("terug");
    this.recipeSelectedEmitter.emit(false);
  }

  addToFavorite(recipe){
    let favRec : RecipeUserDto = new RecipeUserDto;
    favRec.userId = JSON.parse(localStorage.getItem("myUser")).id;
    favRec.recipeId = this.recipe.id;
    this.recipeService.addFavoriteRecipe(favRec).subscribe();
  }
}
