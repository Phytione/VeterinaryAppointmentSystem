package com.example.veterinerrandevu;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class MyAdapterDoctor extends RecyclerView.Adapter<MyAdapterDoctor.MyViewHolder> {
    private Context context;
    private ArrayList dAnimalName,dTarih;
    private ArrayList dSahip;

    public MyAdapterDoctor(Context context, ArrayList dAnimalName,ArrayList dTarih,ArrayList dSahip) {
        this.context = context;
        this.dAnimalName = dAnimalName;
        this.dSahip = dSahip;
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
        int yesilRenk= ContextCompat.getColor(context,R.color.yesil);
        int kirmiziRenk=ContextCompat.getColor(context,R.color.kirmizi);
        int siyahRenk=ContextCompat.getColor(context,R.color.black);
        Collections.sort(dTarih, new Comparator<String>() {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

            @Override
            public int compare(String dateString1, String dateString2){
                try{
                    Date date1=format.parse(dateString1);
                    Date date2=format.parse(dateString2);
                    return date1.compareTo(date2);
                }catch (ParseException e){
                    e.printStackTrace();
                }
                return 0;
            }
        });

        int sortedPosition=dTarih.indexOf(String.valueOf(dTarih.get(position)));


        holder.dAnimalName.setText(String.valueOf(dAnimalName.get(position)));
        holder.dSahip.setText(String.valueOf(dSahip.get(position)));
        holder.dTarih.setText(String.valueOf(dTarih.get(sortedPosition)));

        boolean isPastAppoinment=checkIfPastAppointment((String) dTarih.get(position));

        if (isPastAppoinment){
            holder.doctorLinearLayout.setBackgroundColor(kirmiziRenk);
            holder.dTarih.setTextColor(Color.WHITE);
            holder.dAnimalName.setTextColor(Color.WHITE);
            holder.dSahip.setTextColor(Color.WHITE);

        }else{
            holder.doctorLinearLayout.setBackgroundColor(yesilRenk);
            holder.dTarih.setTextColor(siyahRenk);
            holder.dAnimalName.setTextColor(siyahRenk);
            holder.dSahip.setTextColor(siyahRenk);
        }



    }


    @Override
    public int getItemCount() { return dAnimalName.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView dAnimalName,dSahip,dTarih;
        LinearLayout doctorLinearLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            doctorLinearLayout=itemView.findViewById(R.id.doctorLinearLayout);
            dAnimalName=itemView.findViewById(R.id.textRName);
            dSahip=itemView.findViewById(R.id.textSahip);
            dTarih=itemView.findViewById(R.id.textTarih);
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
