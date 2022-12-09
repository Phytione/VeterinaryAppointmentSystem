package com.example.veterinerrandevu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterRandevu extends RecyclerView.Adapter<MyAdapterRandevu.MyViewHolder> {
    private Context context;
    private ArrayList rAnimalName,rBolum,rDoctor,rTarih;

    public MyAdapterRandevu(Context context, ArrayList rAnimalName, ArrayList rBolum,ArrayList rDoctor,ArrayList rTarih) {
        this.context = context;
        this.rAnimalName = rAnimalName;
        this.rBolum = rBolum;
        this.rDoctor = rDoctor;
        this.rTarih=rTarih;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userrandevu,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.rAnimalName.setText(String.valueOf(rAnimalName.get(position)));
        holder.rBolum.setText(String.valueOf(rBolum.get(position)));
        holder.rDoctor.setText(String.valueOf(rDoctor.get(position)));
        holder.rTarih.setText(String.valueOf(rTarih.get(position)));


    }

    @Override
    public int getItemCount() {
        return rAnimalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rAnimalName,rBolum,rDoctor,rTarih;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rAnimalName=itemView.findViewById(R.id.textRName);
            rBolum=itemView.findViewById(R.id.textRBolum);
            rDoctor=itemView.findViewById(R.id.textRDoctor);
            rTarih=itemView.findViewById(R.id.textRTarih);
        }
    }
}
