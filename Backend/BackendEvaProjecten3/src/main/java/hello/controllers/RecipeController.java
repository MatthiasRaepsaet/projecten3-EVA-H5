package hello.controllers;

import hello.domain.Comment;
import hello.domain.EvaUser;
import hello.domain.Ingredient;
import hello.domain.Recipe;
import hello.dtos.*;
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
            recipeDto.setCategory(recipe.getCategory());
            for(Comment comment: recipe.getComments()){
                commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setAuthor(comment.getAuthor());
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
        Ingredient newIngredient;
        List<Ingredient> newIngredientList = new ArrayList<>();
        for(AddIngredientDto ingredient: addRecipeDto.getIngredients()){
            newIngredient = new Ingredient();
            newIngredient.setName(ingredient.getName());
            newIngredient.setAmount(ingredient.getAmount());
            newIngredient.setMetric(ingredient.getMetric());
            newIngredientList.add(newIngredient);
            ingredientRepository.save(newIngredient);
        }
        newRecipe.setTitle(addRecipeDto.getTitle());
        newRecipe.setAuthorName(user.getUsername());
        newRecipe.setDescription(addRecipeDto.getDescription());
        newRecipe.setIngredients(newIngredientList);
        newRecipe.setCategory(addRecipeDto.getCategory());
        user.getMyRecipes().add(newRecipe);
        recipeRepository.save(newRecipe);
        evaUserRepository.save(user);
    }

    @RequestMapping(path = "/favoriterecipe", method = RequestMethod.POST)
    public void addFavorite(@RequestBody RecipeUserDto recipeUser){
        EvaUser user = evaUserRepository.findOne(recipeUser.getUserId());
        Recipe recipe = recipeRepository.findOne(recipeUser.getRecipeId());
        user.getFavoriteRecipes().add(recipe);
        evaUserRepository.save(user);
    }

    @RequestMapping(path = "/getrecipebyid", method = RequestMethod.GET)
    public Recipe getRecipeById(@RequestParam long id){
        return recipeRepository.findOne(id);
    }

    @RequestMapping(path = "/addcomment", method = RequestMethod.POST)
    public void addComment(@RequestBody AddCommentDto addCommentDto) {
        Recipe updatedRecipe = recipeRepository.findOne(addCommentDto.getRecipeId());
        EvaUser user = evaUserRepository.findOne(addCommentDto.getUserId());
        Comment newComment = new Comment();
        newComment.setAuthor(user.getUsername());
        newComment.setMessage(addCommentDto.getMessage());
        commentRepository.save(newComment);
        updatedRecipe.getComments().add(newComment);
        recipeRepository.save(updatedRecipe);
    }

    @RequestMapping(path = "/upvoterecipe", method = RequestMethod.POST)
    public void upvoteRecipe(@RequestParam long id){
        Recipe recipe = recipeRepository.findOne(id);
        recipe.setUpvotes(recipe.getUpvotes()+1);
        recipeRepository.save(recipe);
    }

    @RequestMapping(path = "/downvoterecipe", method = RequestMethod.POST)
    public void downvoteRecipe(@RequestParam long id){
        Recipe recipe = recipeRepository.findOne(id);
        recipe.setDownvotes(recipe.getDownvotes()+1);
        recipeRepository.save(recipe);
    }

    @RequestMapping(path="/getfavoriterecipes", method = RequestMethod.GET)
    public Iterable<RecipeDto> getFavoriteRecipes(@RequestParam long userId){
        EvaUser user = evaUserRepository.findOne(userId);
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        RecipeDto recipeDto;
        CommentDto commentDto;
        for(Recipe recipe : user.getFavoriteRecipes()){
            recipeDto = new RecipeDto();
            recipeDto.setId(recipe.getId());
            recipeDto.setTitle(recipe.getTitle());
            recipeDto.setAuthor(recipe.getAuthorName());
            recipeDto.setDescription(recipe.getDescription());
            recipeDto.setDownvotes(recipe.getDownvotes());
            recipeDto.setUpvotes(recipe.getUpvotes());
            recipeDto.setIngredients(recipe.getIngredients());
            recipeDto.setCategory(recipe.getCategory());
            for(Comment comment: recipe.getComments()){
                commentDto = new CommentDto();
                commentDto.setId(comment.getId());
                commentDto.setAuthor(comment.getAuthor());
                commentDto.setMessage(comment.getMessage());
                commentDto.setDownvotes(comment.getDownvotes());
                commentDto.setUpvotes(comment.getUpvotes());
                recipeDto.getComments().add(commentDto);
            }
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    @RequestMapping(path = "/recipesbycategory", method = RequestMethod.GET)
    public Iterable<Recipe> getRecipesByCategory(@RequestParam String category){
        Iterable<Recipe> recipeList = recipeRepository.findAll();
        List<Recipe> resultList = new ArrayList<>();
        for(Recipe recipe : recipeList){
            if(recipe.getCategory() == category){
                resultList.add(recipe);
            }
        }
        return resultList;
    }
    @RequestMapping(path = "/searchrecipe", method = RequestMethod.GET)
    public Iterable<Recipe> searchRecipe(@RequestParam String search){
        Iterable<Recipe> recipeList = recipeRepository.findAll();
        List<Recipe> resultList = new ArrayList<>();
        for(Recipe recipe : recipeList){
            if(recipe.getTitle().contains(search)){
                resultList.add(recipe);
            }
        }
        return resultList;
    }

}
