package com.techietech.ps5emulatorprank;

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

public class UploadActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button uploadButton;
    EditText uploadGameName, uploadGameImageUrl,uploadGameUrl;
    CheckBox ps5,ps4,ps3,pc,xbox,xbox_one,xbox_oe,ps_vita;
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
        ps5 = findViewById(R.id.checkbox_ps5);

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
        String gamePlatform = "happy";

        GameDataClass gameDataClass = new GameDataClass(gameName,gameImageUrl,gameUrl,gamePlatform);

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