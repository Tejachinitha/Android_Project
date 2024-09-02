package com.example.api_practice;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {
    Context context;
    List<RetrofitEntity> retrofitEntities;
    public AdapterClass(MainActivity mainActivity, List<RetrofitEntity> retrofitEntities) {
        this.context=mainActivity;
        this.retrofitEntities=retrofitEntities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_adapter_class,parent,false));
    }
    public  void filerList(List<RetrofitEntity> filterLists){
        retrofitEntities = (filterLists != null) ? filterLists : new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.description.setText(retrofitEntities.get(position).getDescription());
        holder.title.setText(retrofitEntities.get(position).getTitle());
        holder.price.setText(String.valueOf(retrofitEntities.get(position).getPrice()));
        holder.id.setText(String.valueOf(retrofitEntities.get(position).getId()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,GetImageActivity.class);
                intent.putExtra("IMAGE_URL",retrofitEntities.get(position).getImageUrl());
                intent.putExtra("COUNT_URL",retrofitEntities.get(position).getRatingEntity().getCount());
                intent.putExtra("RATING_URL",retrofitEntities.get(position).getRatingEntity().getRate());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return retrofitEntities.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder {
        TextView id,title,price,description;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id_number);
            title=itemView.findViewById(R.id.id_tittle);
            price=itemView.findViewById(R.id.id_price);
            description=itemView.findViewById(R.id.id_description);
            button=itemView.findViewById(R.id.Button_ToImage);
        }
    }
}