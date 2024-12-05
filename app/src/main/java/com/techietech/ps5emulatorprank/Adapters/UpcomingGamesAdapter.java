package com.techietech.ps5emulatorprank.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.techietech.ps5emulatorprank.Activities.DetailsActivity;
import com.techietech.ps5emulatorprank.Models.PlayGamesData;
import com.techietech.ps5emulatorprank.Models.UpcomingGameData;
import com.techietech.ps5emulatorprank.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpcomingGamesAdapter extends RecyclerView.Adapter<MyViewHolderUpcomingGames>{

    private Context context;
    private List<UpcomingGameData> upcomingGameDataList;

    public UpcomingGamesAdapter(Context context, List<UpcomingGameData> upcomingGameDataList) {
        this.context = context;
        this.upcomingGameDataList = upcomingGameDataList;
    }

    @NonNull
    @Override
    public MyViewHolderUpcomingGames onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_upcoming_games_items,parent,false);
        return new MyViewHolderUpcomingGames(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderUpcomingGames holder, int position) {
        Glide.with(holder.recGameImageUrl.getContext()).load(upcomingGameDataList.get(position).getGameImage()).into(holder.recGameImageUrl);
        holder.recGameName.setText(upcomingGameDataList.get(position).getGameName());
        holder.recGamePlatform.setText(upcomingGameDataList.get(position).getGamePlatform());
        holder.recGameReleaseDate.setText(upcomingGameDataList.get(position).getGameReleaseDate());

        holder.recGameInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("gameInfoUrl", upcomingGameDataList.get(holder.getAdapterPosition()).getGameUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return upcomingGameDataList.size();
    }
}

class MyViewHolderUpcomingGames extends RecyclerView.ViewHolder {
    // Add your views here

    //CircleImageView recImage;
    TextView recGameName, recGamePlatform,recGameReleaseDate;
    CircleImageView recGameImageUrl, recGameInfo;
//    CardView recCard;


    public MyViewHolderUpcomingGames(@NonNull View itemView) {
        super(itemView);

        //recImage = itemView.findViewById(R.id.detailImage);
        recGameName = itemView.findViewById(R.id.rv_upg_game_name);
        recGameImageUrl = itemView.findViewById(R.id.rv_upg_game_image);
        recGameInfo = itemView.findViewById(R.id.rv_upg_game_info);
        recGamePlatform = itemView.findViewById(R.id.rv_upg_game_platform);
        recGameReleaseDate = itemView.findViewById(R.id.rv_upg_release_date);

    }
}
