package com.techietech.ps5emulatorprank.Activities;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import com.techietech.ps5emulatorprank.OnBoarding;
import com.techietech.ps5emulatorprank.R;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 3000;

    ImageView backgroundImage;
    TextView splashText;

    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        splashScreen.setKeepOnScreenCondition(() -> false );
        //startSomeNextActivity();
        //finish();


        backgroundImage = findViewById(R.id.splash_logo);
        splashText = findViewById(R.id.splash_text2);

        sideAnim = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.fadein);

        backgroundImage.setAnimation(sideAnim);
        splashText.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, OnBoarding.class);
                        startActivity(intent);
                        finish();
            }
        }, SPLASH_TIMER);

    }
}