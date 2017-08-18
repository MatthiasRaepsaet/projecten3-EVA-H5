package be.hogent.Eva2017g5.EVAH5.rest;

/**
 * Created by sofie.
 */

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RetrofitAPI {

    public final static String BASE_URL = "https://backendeva.herokuapp.com";

    public static ApiInterface getDefaultInterfaceService() {

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new SingletonListTypeAdapterFactory())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }

    //Doesn't serialize or deserialize fields that have the @Expose annotation
    public static ApiInterface getWithoutExposeInterfaceService() {

        Gson gson = new GsonBuilder().registerTypeAdapterFactory(new SingletonListTypeAdapterFactory())
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        final ApiInterface mInterfaceService = retrofit.create(ApiInterface.class);
        return mInterfaceService;
    }

}

