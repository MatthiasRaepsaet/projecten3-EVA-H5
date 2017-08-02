import {IngredientDto} from "./IngredientDto";
/**
 * Created by Matthias on 2/08/2017.
 */
export class AddRecipeDto {
  title : string;
  description : string;
  userId : number;
  ingredients : IngredientDto[];
}
