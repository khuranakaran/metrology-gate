package com.metrologygate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.metrologygate.R;
import com.metrologygate.utility.Constants;
import com.metrologygate.utility.PreferenceManager;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new PreferenceManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (PreferenceManager.getPrefBool(Constants.LOGGED_IN)) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        }, 500);
    }
}
