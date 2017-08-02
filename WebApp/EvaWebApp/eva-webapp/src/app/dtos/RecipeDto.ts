/**
 * Created by Matthias on 1/08/2017.
 */
import {EvaUserDto} from "app/dtos/EvaUserDto";
import {IngredientDto} from "./IngredientDto";
import {CommentDto} from "./CommentDto";

export class RecipeDto {
  id : number;
  title : string;
  author : EvaUserDto;
  description : string;
  ingredients : IngredientDto[];
  upvotes : number;
  downvotes : number;
  comments : CommentDto[];
  category : string;
}
