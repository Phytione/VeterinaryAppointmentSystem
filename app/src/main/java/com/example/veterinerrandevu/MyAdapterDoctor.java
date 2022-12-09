package com.example.veterinerrandevu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapterDoctor extends RecyclerView.Adapter<MyAdapterDoctor.MyViewHolder> {
    private Context context;
    private ArrayList dAnimalName,dTarih;
   // private ArrayList dSahip;

    public MyAdapterDoctor(Context context, ArrayList dAnimalName,ArrayList dTarih) {
        this.context = context;
        this.dAnimalName = dAnimalName;
       // this.dSahip = dSahip;
        this.dTarih=dTarih;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.userdoctor,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.dAnimalName.setText(String.valueOf(dAnimalName.get(position)));
       // holder.dSahip.setText(String.valueOf(dSahip.get(position)));
        holder.dTarih.setText(String.valueOf(dTarih.get(position)));


    }


    @Override
    public int getItemCount() { return dAnimalName.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dAnimalName,dSahip,dTarih;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            dAnimalName=itemView.findViewById(R.id.textRName);
           // dSahip=itemView.findViewById(R.id.textSahip);
            dTarih=itemView.findViewById(R.id.textTarih);
        }
    }
}
