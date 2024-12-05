package com.techietech.ps5emulatorprank.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.techietech.ps5emulatorprank.Activities.DetailsActivity;
import com.techietech.ps5emulatorprank.Models.PlayGamesData;
import com.techietech.ps5emulatorprank.R;

import java.util.List;

public class PlayGamesAdapter extends RecyclerView.Adapter<MyViewHolderDetails>{

    private Context context;
    private List<PlayGamesData> playGamesDataList;

    public PlayGamesAdapter(Context context, List<PlayGamesData> playGamesDataList) {
        this.context = context;
        this.playGamesDataList = playGamesDataList;
    }

    @NonNull
    @Override
    public MyViewHolderDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_play_games_items,parent,false);
        return new MyViewHolderDetails(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDetails holder, int position) {
        holder.recGameName.setText(playGamesDataList.get(position).getGameName());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("gameName", playGamesDataList.get(holder.getAdapterPosition()).getGameName());
//                intent.putExtra("gameImageUrl", gameDataClassList.get(holder.getAdapterPosition()).getGameImage());
//                intent.putExtra("gameUrl", gameDataClassList.get(holder.getAdapterPosition()).getGameUrl());
//                intent.putExtra("gamePlatform", gameDataClassList.get(holder.getAdapterPosition()).getGamePlatform());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return playGamesDataList.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    // Add your views here

    //CircleImageView recImage;
    TextView recGameName, recGameImageUrl, recGameUrl, recGamePlatform;
    CardView recCard;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

//        recImage = itemView.findViewById(R.id.detailImage);
        recGameName = itemView.findViewById(R.id.recGameName);
//        recGameImageUrl = itemView.findViewById(R.id.detailGameImageUrl);
//        recGameUrl = itemView.findViewById(R.id.detailGameUrl);
//        recGamePlatform = itemView.findViewById(R.id.detailGamePlatform);
        recCard = itemView.findViewById(R.id.recCard);
    }
}
