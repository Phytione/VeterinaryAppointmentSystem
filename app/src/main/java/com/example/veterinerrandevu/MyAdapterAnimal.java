package com.example.veterinerrandevu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterAnimal extends RecyclerView.Adapter<MyAdapterAnimal.MyViewHolder>
{
    private Context context;
    private ArrayList animalName,animalSikayet;

    public MyAdapterAnimal(Context context, ArrayList animalName, ArrayList animalSikayet) {
        this.context = context;
        this.animalName = animalName;
        this.animalSikayet = animalSikayet;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.animalName.setText(String.valueOf(animalName.get(position)));
        holder.animalSikayet.setText(String.valueOf(animalSikayet.get(position)));
    }

    @Override
    public int getItemCount() {
        return animalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView animalName,animalSikayet;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            animalName=itemView.findViewById(R.id.textname);
            animalSikayet=itemView.findViewById(R.id.textsikayet);
        }
    }
}
