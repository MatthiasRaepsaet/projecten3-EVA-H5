package be.hogent.Eva2017g5.EVAH5.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.hogent.Eva2017g5.EVAH5.domain.SessionManager;
import be.hogent.Eva2017g5.EVAH5.domain.AppConfig;
import be.hogent.Eva2017g5.EVAH5.domain.AppController;
import be.hogent.Eva2017g5.EVAH5.rest.ApiInterface;
import be.hogent.Eva2017g5.EVAH5.rest.Login;
import be.hogent.Eva2017g5.EVAH5.rest.Recipe;
import be.hogent.Eva2017g5.EVAH5.rest.RetrofitAPI;
import be.hogent.Eva2017g5.R;
import retrofit2.Call;
import retrofit2.Callback;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnLogin;
    private Button btnLinkToRegister;
    private EditText inputUsername;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.username);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);


        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String username = inputUsername.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if(username.isEmpty() || password.isEmpty()){
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Geef aub uw gegevens in!", Toast.LENGTH_LONG)
                            .show();
                }else {
                    checkLogin(username,password);
                }
            }

        });

        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
    /**
     * function to verify login details in db
     * */
    private void checkLogin(final String username, final String password) {
        // Tag used to cancel the request

        pDialog.setMessage("Aanmelden ...");
        showDialog();

        ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
        Call<Login> mService = mApiService.authenticate(new Login(username, password));

        mService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {
                if (200 <= response.code() && response.code() <= 300) {

                    ApiInterface api = RetrofitAPI.getWithoutExposeInterfaceService();
                    Call<List<Recipe>> callRecipes = api.getRecipes();
                    {
                        callRecipes.enqueue(new Callback<List<Recipe>>() {
                            @Override
                            public void onResponse(Call<List<Recipe>> call, retrofit2.Response<List<Recipe>> response) {
                                ArrayList<Recipe> recipes = (ArrayList) response.body();
                                Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);
                                loginIntent.putParcelableArrayListExtra("RECIPES", recipes);
                                loginIntent.putExtra("USERNAME", username);
                                // user successfully logged in
                                // Create login session
                                session.setLogin(true);
                                startActivity(loginIntent);
                            }

                            @Override
                            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                                System.out.println("Failure to get recipes" + t.getMessage());
                            }
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                call.cancel();
                System.out.println();
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}

