package com.example.veterinerrandevu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyAdapterRandevu extends RecyclerView.Adapter<MyAdapterRandevu.MyViewHolder> {
    private Context context;
    private ArrayList rAnimalName,rBolum,rDoctor,rTarih;
    private LinearLayout randevuLinearLayout;
    //private ArrayList<Date> tarih;


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

        boolean isPastAppoinment = checkIfPastAppointment((String) rTarih.get(position));


       if(isPastAppoinment){
           holder.randevuLinearLayout.setBackgroundColor(Color.RED);
           holder.rAnimalName.setTextColor(Color.WHITE);
           holder.rBolum.setTextColor(Color.WHITE);
           holder.rDoctor.setTextColor(Color.WHITE);
           holder.rTarih.setTextColor(Color.WHITE);
       }else{
           holder.randevuLinearLayout.setBackgroundColor(Color.GREEN);

       }
       /*

       SimpleDateFormat format=new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        Date tarih;
        tarih=format.parse(rTarih);
        */




    }

    @Override
    public int getItemCount() {
        return rAnimalName.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rAnimalName,rBolum,rDoctor,rTarih;
        CardView cardViewRandevuUser;
        LinearLayout randevuLinearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            randevuLinearLayout=itemView.findViewById(R.id.randevuLinearLayout);
            rAnimalName=itemView.findViewById(R.id.textRName);
            rBolum=itemView.findViewById(R.id.textRBolum);
            rDoctor=itemView.findViewById(R.id.textRDoctor);
            rTarih=itemView.findViewById(R.id.textRTarih);
            cardViewRandevuUser=itemView.findViewById(R.id.cardViewRandevuUser);
        }
    }

    public void setBackgroundColor(boolean isPastAppointment){
        if (isPastAppointment) {
            randevuLinearLayout.setBackgroundColor(Color.RED);
        } else {
            randevuLinearLayout.setBackgroundColor(Color.GREEN);
        }
    }
    private boolean checkIfPastAppointment(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        try {
            Date date = format.parse(dateString);
            Date today = new Date();

            // Bugün için alınan randevuların da yeşil olarak gösterilmesi
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Date startOfToday = calendar.getTime();

            return date.before(startOfToday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
