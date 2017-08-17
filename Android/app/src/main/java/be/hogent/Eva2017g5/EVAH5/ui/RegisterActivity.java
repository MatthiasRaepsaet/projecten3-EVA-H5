package be.hogent.Eva2017g5.EVAH5.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.hogent.Eva2017g5.EVAH5.domain.SessionManager;
import be.hogent.Eva2017g5.EVAH5.rest.ApiInterface;
import be.hogent.Eva2017g5.EVAH5.rest.Register;
import be.hogent.Eva2017g5.EVAH5.rest.RetrofitAPI;
import be.hogent.Eva2017g5.R;
import retrofit2.Call;
import retrofit2.Callback;
//import org.apache.commons.validator.routines.EmailValidator;

public class RegisterActivity extends Activity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private Button btnRegister;
    private Button btnLinkToLogin;
    private EditText inputFirstName;
    private EditText inputLastName;
    private EditText inputUsername;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputFirstName = (EditText) findViewById(R.id.firstname);
        inputLastName = (EditText) findViewById(R.id.lastname);
        inputUsername = (EditText) findViewById(R.id.username);
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);

        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(RegisterActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }

        // Register Button Click event
        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String firstname = inputFirstName.getText().toString().trim();
                String lastname = inputFirstName.getText().toString().trim();
                String username = inputUsername.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if(firstname.isEmpty() || email.isEmpty() || password.isEmpty() || lastname.isEmpty() || username.isEmpty()){
                    Toast.makeText(getApplicationContext(),
                            "Vul aub uw gegevens in!", Toast.LENGTH_LONG)
                            .show();
                }else if(password.length()<6){
                    Toast.makeText(getApplicationContext(),
                            "Wachtwoord moet uit minstens 6 tekens bestaan", Toast.LENGTH_LONG)
                            .show();
                }
                /*else if(!EmailValidator.getInstance().isValid(email)) {
                    Toast.makeText(getApplicationContext(),
                            "Gelieve een correct email op te geven", Toast.LENGTH_LONG)
                            .show();

                }*/
                else if(!firstname.matches("[a-zA-Z]+") || !lastname.matches("[a-zA-Z]+")){
                    Toast.makeText(getApplicationContext(),
                            "Gelieve een correcte voornaam en/of achternaam in te geven", Toast.LENGTH_LONG)
                            .show();
                }
                    else
                {
                    registerUser(firstname,lastname,username, email, password);
                }

            }
        });

        // Link to Login Screen
        btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }



    /**
     * Function to store user in MySQL database will post params(tag, name,
     * email, password) to register url
     * */
    private void registerUser(final String firstname,final String lastname,final String username, final String email,
                              final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_register";

        pDialog.setMessage("Registreren ...");
        showDialog();

        ApiInterface mApiService = RetrofitAPI.getDefaultInterfaceService();
        Call<Register> mService = mApiService.registration(new Register( email, firstname, lastname,password , username));
        mService.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, retrofit2.Response<Register> response) {
                if (200 <= response.code() && response.code() <= 300) {
                    // redirect to Login Activity page
                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                call.cancel();
                Toast.makeText(RegisterActivity.this, "Please check your network connection and internet permission", Toast.LENGTH_LONG).show();

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
