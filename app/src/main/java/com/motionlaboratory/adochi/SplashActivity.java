package com.motionlaboratory.adochi;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    SessionManager session;
    int delay = 3000;
    DBHelperConfig MyDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        session = new SessionManager(getApplicationContext());
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
                if(!session.isUserLoggedIn()){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }else {
                    startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                    finish();
                }
            }
        },delay);

    }
}
