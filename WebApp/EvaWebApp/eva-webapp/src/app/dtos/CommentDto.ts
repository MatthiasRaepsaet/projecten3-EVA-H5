import {EvaUserDto} from "./EvaUserDto";
/**
 * Created by Matthias on 2/08/2017.
 */
export class CommentDto {
  id: number;
  author : EvaUserDto;
  message : string;
  upvotes : number;
  downvotes : number;
}
