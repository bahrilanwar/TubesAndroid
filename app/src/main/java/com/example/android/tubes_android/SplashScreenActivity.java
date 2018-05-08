package com.example.android.tubes_android;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                finish();

            }
        },4000);
    }
}