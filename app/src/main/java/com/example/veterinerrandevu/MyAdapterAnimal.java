package com.example.veterinerrandevu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapterAnimal extends RecyclerView.Adapter<MyAdapterAnimal.MyViewHolder>
{


    private dataBase DB;
    private Context context;
    private String eposta;
    private ArrayList animalName,animalSikayet;
    private String hayvanTuru;
    private List<String> animalTurList;

    public MyAdapterAnimal(Context context, ArrayList animalName, ArrayList animalSikayet,String hayvanTuru,String eposta,dataBase DB,List<String> animalTurList) {
        this.context = context;
        this.animalName = animalName;
        this.animalSikayet = animalSikayet;
        this.hayvanTuru=hayvanTuru;
        this.eposta=eposta;
        this.DB=DB;
        this.animalTurList=animalTurList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userentry,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String currentAnimalTuru= animalTurList.get(position);



        holder.animalEkleImageView.setImageResource(getImageResource(currentAnimalTuru));
        holder.animalName.setText(String.valueOf(animalName.get(position)));
        holder.animalSikayet.setText(String.valueOf(animalSikayet.get(position)));

        Log.d("dekdek",eposta);
        //Log.d("asdas",hayvanTuru);




    }
    private int getImageResource(String hayvanTuru) {
        if (hayvanTuru.equalsIgnoreCase("Kedi")) {
            return R.drawable.catt;
        } else if (hayvanTuru.equalsIgnoreCase("Köpek")) {
            return R.drawable.dogg;
        } else if (hayvanTuru.equalsIgnoreCase("Muhabbet Kuşu")) {
            return R.drawable.birdd;
        } else if (hayvanTuru.equalsIgnoreCase("Tavşan")) {
            return R.drawable.rabbit;
        } else {
            return R.drawable.defaultxx;

        }
    }

    @Override
    public int getItemCount() {
        return animalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView animalName,animalSikayet;
        ImageView animalEkleImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            animalEkleImageView=itemView.findViewById(R.id.animalEkleImageView);
            animalName=itemView.findViewById(R.id.textname);
            animalSikayet=itemView.findViewById(R.id.textsikayet);
        }
    }



}
