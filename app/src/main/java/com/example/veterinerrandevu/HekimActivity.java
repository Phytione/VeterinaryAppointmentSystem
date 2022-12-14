package com.example.veterinerrandevu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HekimActivity extends AppCompatActivity {
    dataBase DB;
    RecyclerView recyclerView;
    TextView hekimAd;
    ArrayList<String> animalName;
   // ArrayList<String> sahipName;
    ArrayList<String> tarih;
    MyAdapterDoctor myAdapterDoctor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hekim);
        String nameDataDoctor=getIntent().getStringExtra("nameDoctor");
        String usernameDoctor=getIntent().getStringExtra("epostaDoctor");
        DB=new dataBase(this);
        hekimAd=findViewById(R.id.textDoctorName);
        hekimAd.setText(nameDataDoctor);


    }
    public void hastaGoster(View view){

        animalName=new ArrayList<>();
        tarih=new ArrayList<>();
        //sahipName=new ArrayList<>();
        recyclerView=findViewById(R.id.hekimRV);
        myAdapterDoctor=new MyAdapterDoctor(this,animalName,tarih);
        recyclerView.setAdapter(myAdapterDoctor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        veriyiGoster();

    }

    private void veriyiGoster(){
       /* String getUsernameforHekim=DB.getUsernameforHekim(String.valueOf(animalName));
        String usernameForHekim=getUsernameforHekim.toString();

        String getNameforHekim=DB.getNameforHekim(usernameForHekim);
        String nameForHekim=getNameforHekim.toString();*/

        String nameDataDoctor=getIntent().getStringExtra("nameDoctor");
        Cursor cursor=DB.getRandevuforHekim(nameDataDoctor);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Veri kayd?? yok",Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                animalName.add(cursor.getString(1));
                tarih.add(cursor.getString(4));


            }
        }

    }
    public void profileD(View view){
        String nameDataDoctor=getIntent().getStringExtra("nameDoctor");
        String usernameDoctor=getIntent().getStringExtra("epostaDoctor");
        Intent intent=new Intent(getApplicationContext(),ProfileDoctor.class);
        intent.putExtra("nameDataDoctor",nameDataDoctor);
        intent.putExtra("usernamedoctor",usernameDoctor);
        startActivity(intent);

    }

}