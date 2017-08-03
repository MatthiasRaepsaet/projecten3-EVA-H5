import {Component, EventEmitter, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-selected-recipe',
  templateUrl: './selected-recipe.component.html',
  styleUrls: ['./selected-recipe.component.css']
})
export class SelectedRecipeComponent implements OnInit {

  @Output()
  recipeSelectedEmitter : EventEmitter<boolean> = new EventEmitter<boolean>();

  comments : any[];

  ingredienten : any[];
  relativeIngredientenList = [];

  personenCount = 2;
  singleMulti = "personen";

  like = 0;

  dislike = 0;

  constructor() {
    this.ingredienten = [{amount : 200, metric : "gram", name: "rijst"}, {amount : 1, metric : "eetlepel", name: "curry"}, {amount : 5, metric : "deciliter", name: "melk"}];
    this.comments = [{name : "Matthias", message : "Dit is een goed recept"}, {name : "Marieke", message : "Kan beter"}, {name : "Lukas", message : "Beregoed!"}]
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
    this.relativeIngredientenList = JSON.parse(JSON.stringify(this.ingredienten));
  }

  likeRecipe(){
    this.like++;
  }

  dislikeRecipe(){
    this.dislike++;
  }

  terug(){
    console.log("terug");
    this.recipeSelectedEmitter.emit(false);
  }
}
