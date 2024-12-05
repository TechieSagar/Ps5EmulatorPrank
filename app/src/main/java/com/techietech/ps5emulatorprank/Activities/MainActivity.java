package com.techietech.ps5emulatorprank.Activities;

import static com.denzcoskun.imageslider.constants.ScaleTypes.FIT;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techietech.ps5emulatorprank.OnBoarding;
import com.techietech.ps5emulatorprank.R;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button nextBtn,nextScreen, play_button, upload_button;
    FloatingActionButton fab;
    LinearLayout dotsLayout,gameList, upcomingGames;
    ViewPager viewPager;
    private InterstitialAd mInterstitialAd;
    private static final String TAG = "MainActivity";

    private ImageSlider imageSlider;
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    View ScreenView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nextScreen = findViewById(R.id.buttonScreen);
        //nextBtn = findViewById(R.id.buttonScreen);
        //dotsLayout = findViewById(R.id.dotsLayout);
        //viewPager = findViewById(R.id.slider);

        gameList = findViewById(R.id.cardView2);
        upcomingGames = findViewById(R.id.upcomingGames);

        play_button = findViewById(R.id.play_button);
        //upload_button = findViewById(R.id.upload_button);
        fab = findViewById(R.id.fab);

        play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlayGamesActivity.class);
                startActivity(intent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

        gameList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                startActivity(intent);
            }
        });

        upcomingGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpcomingGamesActivity.class);
                startActivity(intent);
            }
        });

        //ScreenView = findViewById(R.id.relativeLayout);

        FirebaseApp.initializeApp(/*context=*/ this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());

        imageSlider = findViewById(R.id.image_slider);
        ArrayList<SlideModel> slideModels = new ArrayList<>();

        database.collection("slider_images").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if(task.isSuccessful()){

                    for(QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()){
                        slideModels.add(new SlideModel(queryDocumentSnapshot.getString("url"), FIT));
                        imageSlider.setImageList(slideModels, FIT);
//                        ScreenView.setBackground(slideModels,FIT);
                    }
                }
                else {
                    Toast.makeText(MainActivity.this,"Unable to load images", Toast.LENGTH_SHORT).show();
                }

            }
        })          .addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this,"Unable to load images", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void openActivity2(){
        Intent intent = new Intent(this, OnBoarding.class);
        startActivity(intent);
    }



}