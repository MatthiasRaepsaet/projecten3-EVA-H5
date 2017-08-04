import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Router} from "@angular/router";
import {RecipeDto} from "../../dtos/RecipeDto";
import {IngredientDto} from "../../dtos/IngredientDto";

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

  comments : any[];

  ingredienten : IngredientDto[];
  relativeIngredientenList = [];

  personenCount = 2;
  singleMulti = "personen";

  like = 0;

  dislike = 0;

  constructor(private router : Router) {
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
}
