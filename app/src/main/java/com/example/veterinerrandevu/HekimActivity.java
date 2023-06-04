package com.example.veterinerrandevu;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HekimActivity extends AppCompatActivity {
    dataBase DB;
    RecyclerView recyclerView;
    TextView hekimAd;
    ArrayList<String> animalName;
    ArrayList<String> sahipName;
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
        sahipName=new ArrayList<>();
        recyclerView=findViewById(R.id.hekimRV);
        myAdapterDoctor=new MyAdapterDoctor(this,animalName,tarih,sahipName);
        recyclerView.setAdapter(myAdapterDoctor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        veriyiGoster();

    }

    private void veriyiGoster(){


        String nameDataDoctor=getIntent().getStringExtra("nameDoctor");
        Cursor cursor=DB.getRandevuforHekim(nameDataDoctor);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Veri kaydı yok",Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                String username=cursor.getString(0);
                String sahipNameDB=DB.getHastaName(String.valueOf(username));
                sahipName.add(sahipNameDB);
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