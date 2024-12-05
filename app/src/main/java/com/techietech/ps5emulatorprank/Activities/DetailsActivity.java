package com.techietech.ps5emulatorprank.Activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.techietech.ps5emulatorprank.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsActivity extends AppCompatActivity {

    TextView detailGameName, detailGameImageUrl, detailGameUrl, detailGamePlatform;
    CircleImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        detailGameName = findViewById(R.id.detailGameName);
        detailGameImageUrl = findViewById(R.id.detailGameImageUrl);
        detailGameUrl = findViewById(R.id.detailGameUrl);
        detailGamePlatform = findViewById(R.id.detailGamePlatform);
        detailImage = findViewById(R.id.detailImage);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detailGameName.setText(bundle.getString("gameName"));
            detailGameImageUrl.setText(bundle.getString("gameImageUrl"));
            detailGameUrl.setText(bundle.getString("gameUrl"));
            detailGamePlatform.setText(bundle.getString("gamePlatform"));
            Glide.with(this).load(bundle.getString("gameImageUrl")).into(detailImage);
        }

    }
}