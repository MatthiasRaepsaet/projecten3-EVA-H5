package hello.controllers;

import hello.domain.Comment;
import hello.domain.EvaUser;
import hello.domain.Ingredient;
import hello.domain.Recipe;
import hello.dtos.AddCommentDto;
import hello.dtos.AddRecipeDto;
import hello.dtos.CommentDto;
import hello.dtos.RecipeDto;
import hello.repositories.CommentRepository;
import hello.repositories.EvaUserRepository;
import hello.repositories.IngredientRepository;
import hello.repositories.RecipeRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthias on 4/07/2017.
 */
@RestController
@javax.transaction.Transactional
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EvaUserRepository evaUserRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @RequestMapping(path = "/getallrecipes", method = RequestMethod.GET)
    public Iterable<RecipeDto> getAllRecipes(){
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        RecipeDto recipeDto;
        CommentDto commentDto;
        for(Recipe recipe : recipeRepository.findAll()){
            recipeDto = new RecipeDto();
            recipeDto.setId(recipe.getId());
            recipeDto.setTitle(recipe.getTitle());
            recipeDto.setAuthor(recipe.getAuthorName());
            recipeDto.setDescription(recipe.getDescription());
            recipeDto.setDownvotes(recipe.getDownvotes());
            recipeDto.setUpvotes(recipe.getUpvotes());
            recipeDto.setIngredients(recipe.getIngredients());
            for(Comment comment: recipe.getComments()){
                commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setAuthor(comment.getAuthor().getUsername());
                commentDto.setMessage(comment.getMessage());
                commentDto.setDownvotes(comment.getDownvotes());
                commentDto.setUpvotes(comment.getUpvotes());
                recipeDto.getComments().add(commentDto);
            }
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    @RequestMapping(path = "/getcommentsforrecipe", method = RequestMethod.GET)
    public Iterable<Comment> getCommentsForRecipeId(@RequestParam long id){
        Iterable<Comment> resultList = recipeRepository.findOne(id).getComments();
        return resultList;
    }

    @RequestMapping(path = "/addrecipe", method = RequestMethod.POST)
    public void addNewRecipe(@RequestBody AddRecipeDto addRecipeDto){
        Recipe newRecipe = new Recipe();
        EvaUser user = evaUserRepository.findOne(addRecipeDto.getUserId());
        for(Ingredient ingredient: addRecipeDto.getIngredients()){
            ingredientRepository.save(ingredient);
        }
        newRecipe.setTitle(addRecipeDto.getTitle());
        newRecipe.setAuthorName(user.getUsername());
        newRecipe.setDescription(addRecipeDto.getDescription());
        newRecipe.setIngredients(addRecipeDto.getIngredients());
        user.getMyRecipes().add(newRecipe);
        recipeRepository.save(newRecipe);
        evaUserRepository.save(user);
    }

    @RequestMapping(path = "/getrecipebyid", method = RequestMethod.GET)
    public Recipe getRecipeById(@RequestParam long id){
        return recipeRepository.findOne(id);
    }

    @RequestMapping(path = "/addcomment", method = RequestMethod.PUT)
    public void addComment(@RequestParam long recipeId, @RequestBody AddCommentDto addCommentDto) {
        Recipe updatedRecipe = recipeRepository.findOne(recipeId);
        EvaUser user = evaUserRepository.findOne(addCommentDto.getUserId());
        Comment newComment = new Comment();
        newComment.setAuthor(user);
        newComment.setMessage(addCommentDto.getMessage());
        commentRepository.save(newComment);
        updatedRecipe.getComments().add(newComment);
        recipeRepository.save(updatedRecipe);
    }

    @RequestMapping(path = "/upvoterecipe", method = RequestMethod.PUT)
    public void upvoteRecipe(@RequestParam long id){
        Recipe recipe = recipeRepository.findOne(id);
        recipe.setUpvotes(recipe.getUpvotes()+1);
        recipeRepository.save(recipe);
    }

    @RequestMapping(path = "/downvoterecipe", method = RequestMethod.PUT)
    public void downvoteRecipe(@RequestParam long id){
        Recipe recipe = recipeRepository.findOne(id);
        recipe.setDownvotes(recipe.getDownvotes()+1);
        recipeRepository.save(recipe);
    }
}
