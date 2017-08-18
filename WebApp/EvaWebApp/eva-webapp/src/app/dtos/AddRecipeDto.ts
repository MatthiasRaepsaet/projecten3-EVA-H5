import {IngredientDto} from "./IngredientDto";
import {AddIngredientDto} from "./AddIngredientDto";
/**
 * Created by Matthias on 2/08/2017.
 */
export class AddRecipeDto {
  title : string;
  description : string;
  userId : number;
  ingredients : AddIngredientDto[];
  category : string;
}
