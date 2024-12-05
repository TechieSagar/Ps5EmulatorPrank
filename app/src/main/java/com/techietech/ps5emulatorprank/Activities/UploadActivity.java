package com.techietech.ps5emulatorprank.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.techietech.ps5emulatorprank.Models.GameDataClass;
import com.techietech.ps5emulatorprank.R;

public class UploadActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button uploadButton;
    EditText uploadGameName, uploadGameImageUrl,uploadGameUrl, uploadGameRelease;
    CheckBox ps5,ps4,ps3,pc,xbox_one,xbox_x,xbox_360,nitendo,psp,ps_vita;
    String imageUrl, gamePlatform;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upload);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        uploadImage = findViewById(R.id.upload_image);
        uploadGameImageUrl = findViewById(R.id.upload_gameImage_url);
        uploadGameName = findViewById(R.id.upload_game_name);
        uploadGameUrl = findViewById(R.id.upload_game_url);
        uploadButton = findViewById(R.id.upload_button);
        uploadGameRelease = findViewById(R.id.upload_game_release);

        // checkbox variables
        ps5 = findViewById(R.id.checkbox_ps5);
        ps4 = findViewById(R.id.checkbox_ps4);
        ps3 = findViewById(R.id.checkbox_ps3);
        pc = findViewById(R.id.checkbox_pc);
        xbox_one = findViewById(R.id.checkbox_xbox_one);
        xbox_x = findViewById(R.id.checkbox_xbox_x);
        xbox_360 = findViewById(R.id.checkbox_xbox360);
        nitendo =  findViewById(R.id.checkbox_nitendo);
        psp = findViewById(R.id.checkbox_psp);
        ps_vita = findViewById(R.id.checkbox_ps_vita);



        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });



    }

    public void saveData(){

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_bar);
        AlertDialog dialog = builder.create();
        dialog.show();
        uploadData();
        dialog.dismiss();

    }

    public void uploadData(){
        ImageView gameImage = findViewById(R.id.upload_image);
        String gameName = uploadGameName.getText().toString();
        String gameImageUrl = uploadGameImageUrl.getText().toString();
        String gameUrl = uploadGameUrl.getText().toString();
        String gameRelease = uploadGameRelease.getText().toString();
        String gamePlatform = "";

        if(ps5.isChecked()){
            gamePlatform = gamePlatform + "Ps5 ";
        }

        if(ps4.isChecked()){
            gamePlatform = gamePlatform + "Ps4 ";
        }

        if(ps3.isChecked()){
            gamePlatform = gamePlatform + "Ps3 ";
        }

        if(pc.isChecked()){
            gamePlatform = gamePlatform + "Pc ";
        }

        if(xbox_one.isChecked()){
            gamePlatform = gamePlatform + "Xbox One ";
        }

        if(xbox_x.isChecked()){
            gamePlatform = gamePlatform + "Xbox X ";
        }

        if(xbox_360.isChecked()){
            gamePlatform = gamePlatform + "Xbox 360 ";
        }

        if(nitendo.isChecked()){
            gamePlatform = gamePlatform + "Nitendo ";
        }

        if(psp.isChecked()){
            gamePlatform = gamePlatform + "PSP ";
        }

        if(ps_vita.isChecked()){
            gamePlatform = gamePlatform + "Ps Vita ";
        }


        GameDataClass gameDataClass = new GameDataClass(gameName,gameImageUrl,gameUrl,gameRelease,gamePlatform);

        FirebaseDatabase.getInstance().getReference("games").child(gameName)
                .setValue(gameDataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(UploadActivity.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadActivity.this, "Something Went Wrong "+e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}