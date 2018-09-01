package com.metrologygate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.metrologygate.R;
import com.metrologygate.models.Login;
import com.metrologygate.retrofit.ApiClient;
import com.metrologygate.retrofit.ApiInterface;
import com.metrologygate.utility.Constants;
import com.metrologygate.utility.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText etIpAddress, etPassword, etUsername;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etIpAddress = findViewById(R.id.et_ip);
        etPassword = findViewById(R.id.et_password);
        etUsername = findViewById(R.id.et_username);
        progressBar = findViewById(R.id.login_progress);
        new PreferenceManager(this);
        // Set up the login form.
    }

    public void onClickLoginButton(View view) {
        progressBar.setVisibility(View.VISIBLE);

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> call = apiInterface.login(etUsername.getText().toString(), etPassword.getText().toString());
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(@NonNull Call<Login> call, @NonNull Response<Login> response) {
                progressBar.setVisibility(View.GONE);
                Log.e("API", " " + response.code());
                Log.e("API", " " + response.raw());
                Log.e("API", " " + response.errorBody());
                Log.e("API", " " + response.message());
                progressBar.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    if (response.body().getLoginResult() == 0) {
                        PreferenceManager.setPrefString(Constants.USER_NAME, etUsername.getText().toString());
                        PreferenceManager.setPrefString(Constants.PASSWORD, etPassword.getText().toString());
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        PreferenceManager.setPrefBool(Constants.LOGGED_IN, true);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
            }
        })
        ;
    }
}

