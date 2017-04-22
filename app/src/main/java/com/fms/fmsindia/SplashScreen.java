package com.fms.fmsindia;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.fms.fmsindia.registion.Login;


public class SplashScreen extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }
    protected void onStart() {
        super.onStart();
          /*New Handler to start the Menu-Activity      and close this Splash-Screen after some seconds.*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent mIntent = new Intent(SplashScreen.this, Login.class);
                    startActivity(mIntent);
                    finish();


                //Create an Intent that will start the Menu-Activity.
//                if(!LocalStorage.getPatientdata(SplashScreen.this).isEmpty()) {
//                    Intent mIntent = new Intent(SplashScreen.this, Login.class);
//                    startActivity(mIntent);
//                    finish();
//                }else {
//                    Intent mIntent = new Intent(SplashScreen.this, MobileNo.class);
//                    startActivity(mIntent);
//                    finish();
//                }
                //Utility.checkAppNavigationStatus(context);


            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
