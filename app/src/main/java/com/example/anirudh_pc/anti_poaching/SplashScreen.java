package com.example.anirudh_pc.anti_poaching;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity{
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        System.out.println("Reached HEre");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent splash_intent = new Intent(SplashScreen.this, LoginActivity.class);
                startActivity(splash_intent);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
