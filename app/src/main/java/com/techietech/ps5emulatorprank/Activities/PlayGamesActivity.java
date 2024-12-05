package com.techietech.ps5emulatorprank;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techietech.ps5emulatorprank.Adapters.DetailsAdapter;

import java.util.ArrayList;
import java.util.List;

public class PlayGamesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<GameDataClass> gameDataClassList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play_games);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.rv_play_games);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(PlayGamesActivity.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);

        AlertDialog.Builder builder = new AlertDialog.Builder(PlayGamesActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_bar);
        AlertDialog dialog = builder.create();
        dialog.show();

        gameDataClassList = new ArrayList<>();
        DetailsAdapter detailsAdapter = new DetailsAdapter(PlayGamesActivity.this,gameDataClassList);
        recyclerView.setAdapter(detailsAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("games");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gameDataClassList.clear();

                for(DataSnapshot itemSnapshot : snapshot.getChildren()){
                    GameDataClass gameDataClass = itemSnapshot.getValue(GameDataClass.class);
                    //gameDataClass.setKey(itemSnapshot.getKey());
                    gameDataClassList.add(gameDataClass);
                }
                detailsAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });

    }
}