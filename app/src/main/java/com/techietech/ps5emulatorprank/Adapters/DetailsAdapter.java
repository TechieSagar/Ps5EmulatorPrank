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

import com.bumptech.glide.Glide;
import com.techietech.ps5emulatorprank.Activities.DetailsActivity;
import com.techietech.ps5emulatorprank.Models.GameDataClass;
import com.techietech.ps5emulatorprank.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsAdapter extends RecyclerView.Adapter<MyViewHolderDetails>{

    private Context context;
    private List<GameDataClass> gameDataClassList;

    public DetailsAdapter(Context context, List<GameDataClass> gameDataClassList) {
        this.context = context;
        this.gameDataClassList = gameDataClassList;
    }

    @NonNull
    @Override
    public MyViewHolderDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_play_games_items,parent,false);
        return new MyViewHolderDetails(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderDetails holder, int position) {
        Glide.with(context).load(gameDataClassList.get(position).getGameImage()).into(holder.recImage);
        holder.recGameName.setText(gameDataClassList.get(position).getGameName());
        holder.recGameImageUrl.setText(gameDataClassList.get(position).getGameImage());
        holder.recGameUrl.setText(gameDataClassList.get(position).getGameUrl());
        holder.recGamePlatform.setText(gameDataClassList.get(position).getGamePlatform());
        holder.recGameRelease.setText(gameDataClassList.get(position).getGameReleaseDate());

        holder.recCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("gameName", gameDataClassList.get(holder.getAdapterPosition()).getGameName());
                intent.putExtra("gameImageUrl", gameDataClassList.get(holder.getAdapterPosition()).getGameImage());
                intent.putExtra("gameUrl", gameDataClassList.get(holder.getAdapterPosition()).getGameUrl());
                intent.putExtra("gamePlatform", gameDataClassList.get(holder.getAdapterPosition()).getGamePlatform());

                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return gameDataClassList.size();
    }
}

class MyViewHolderDetails extends RecyclerView.ViewHolder {
    // Add your views here

    CircleImageView recImage;
    TextView recGameName, recGameImageUrl, recGameUrl, recGamePlatform, recGameRelease;
    CardView recCard;


    public MyViewHolderDetails(@NonNull View itemView) {
        super(itemView);

//        recImage = itemView.findViewById(R.id.detailImage);
        recGameName = itemView.findViewById(R.id.recGameName);
//        recGameImageUrl = itemView.findViewById(R.id.detailGameImageUrl);
//        recGameUrl = itemView.findViewById(R.id.detailGameUrl);
//        recGamePlatform = itemView.findViewById(R.id.detailGamePlatform);
        recCard = itemView.findViewById(R.id.recCard);
    }
}
