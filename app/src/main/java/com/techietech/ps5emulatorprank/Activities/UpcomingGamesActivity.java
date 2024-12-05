package com.techietech.ps5emulatorprank.Activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.techietech.ps5emulatorprank.Adapters.PlayGamesAdapter;
import com.techietech.ps5emulatorprank.Adapters.UpcomingGamesAdapter;
import com.techietech.ps5emulatorprank.Models.PlayGamesData;
import com.techietech.ps5emulatorprank.Models.UpcomingGameData;
import com.techietech.ps5emulatorprank.R;

import java.util.ArrayList;
import java.util.List;

public class UpcomingGamesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<UpcomingGameData> upcomingGameDataList;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upcoming_games);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.rv_upcoming_games);

        //GridLayoutManager gridLayoutManager = new GridLayoutManager(UpcomingGamesActivity.this,1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AlertDialog.Builder builder = new AlertDialog.Builder(UpcomingGamesActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_bar);
        AlertDialog dialog = builder.create();
        dialog.show();

        upcomingGameDataList = new ArrayList<>();
        UpcomingGamesAdapter upcomingGamesAdapter = new UpcomingGamesAdapter(UpcomingGamesActivity.this,upcomingGameDataList);
        recyclerView.setAdapter(upcomingGamesAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("games");
        dialog.show();

        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                upcomingGameDataList.clear();

                for(DataSnapshot itemSnapshot : snapshot.getChildren()){
                    UpcomingGameData upcomingGameData = itemSnapshot.getValue(UpcomingGameData.class);
                    //gameDataClass.setKey(itemSnapshot.getKey());
                    upcomingGameDataList.add(upcomingGameData);
                }
                upcomingGamesAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });



    }
}