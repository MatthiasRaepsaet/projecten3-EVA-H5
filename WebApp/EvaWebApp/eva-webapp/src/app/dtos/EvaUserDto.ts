import {RecipeDto} from "./RecipeDto";
/**
 * Created by Matthias on 2/08/2017.
 */
export class EvaUserDto {
  id : number;
  firstName : string;
  lastName : string;
  email : string;
  username : string;
  password : string;
  score : number;
  completedChallenges : any[];
  todaysChallenges : any[];
  myRecipes : RecipeDto[];
  favoriteRecipes : RecipeDto[];
}
