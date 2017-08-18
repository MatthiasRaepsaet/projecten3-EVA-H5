package be.hogent.Eva2017g5.EVAH5.rest;

/**
 * Created by sofie.
 */
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("/login")
    Call<Login> authenticate(@Body Login login);

    @POST("/adduser")
    Call<Register> registration(@Body Register register);

    @GET("/getuser?userId={userid}")
    Call<EvaUser> getEvaUser(@Path("id") String id);

    @GET("/getallrecipes")
    Call<List<Recipe>> getRecipes();

    @GET("/getfavoriterecipes?userId={userid}")
    Call<List<Recipe>> getFavoriteRecipes(@Path("id") String id);

    @POST("/favoriterecipe")
    Call<Recipe> addFavorite();

    @GET("/getcommentsforrecipe?recipeId={recipeid}")
    Call<List<Comment>> getComments(@Path("id") String id);

    @POST("/upvote?recipeId={recipeid}")
    Call<Recipe> upvoteRecipe(@Path("id") String id);

    @POST("/downvote?recipeId={recipeid}")
    Call<Recipe> downvoteRecipe(@Path("id") String id);

    @GET("/getallchallenges")
    Call<List<Challenge>> getChallenges();




}
