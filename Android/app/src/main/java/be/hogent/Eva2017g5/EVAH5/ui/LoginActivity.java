package be.hogent.Eva2017g5.EVAH5.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import be.hogent.Eva2017g5.EVAH5.domainAndModel.SessionManager;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.ApiInterface;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.Login;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.RetrofitAPI;
import be.hogent.Eva2017g5.EVAH5.domainAndModel.UserId;
import be.hogent.Eva2017g5.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private ImageView evalogo;
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

        evalogo = (ImageView)  findViewById(R.id.evalogo);
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
        Call<Login> mService = mApiService.authenticate(new Login(password, username));

        mService.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, retrofit2.Response<Login> response) {
                if (200 <= response.code() && response.code() <= 300) {
                    final Intent loginIntent = new Intent(LoginActivity.this, MainActivity.class);

                    ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
                    Call<UserId> mService = mApiService.getUserId(username);
                    mService.enqueue(new Callback<UserId>() {
                        @Override
                        public void onResponse(Call<UserId> call, Response<UserId> response) {
                            UserId userId = response.body();
                            loginIntent.putExtra("USERID", userId.getId());
                        }

                        @Override
                        public void onFailure(Call<UserId> call, Throwable t) {

                        }
                    });

                    loginIntent.putExtra("USERNAME", username);
                    // user successfully logged in
                    // Create login session
                    session.setLogin(true);
                    startActivity(loginIntent);


                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                System.out.println();
                Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                Log.d("Error", "Verbinding met database mislukt");
                call.cancel();


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

