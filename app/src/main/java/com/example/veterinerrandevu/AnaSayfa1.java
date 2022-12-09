package com.example.veterinerrandevu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AnaSayfa1 extends AppCompatActivity {

    TextView txtproAdSoyad;
    dataBase DB;
    Button refresh;
    RecyclerView recyclerView;
    ArrayList<String> rAnimalName,rBolum,rDoctor,rTarih;
    MyAdapterRandevu myAdapterRandevu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa1);
        String adSoyad=getIntent().getStringExtra("adSoyad");
        String sifre=getIntent().getStringExtra("sifre");
        String eposta=getIntent().getStringExtra("eposta");
        txtproAdSoyad=findViewById(R.id.txtproAdSoyad);
        refresh=findViewById(R.id.refresh);
        DB=new dataBase(this);
        String getName=DB.getName(eposta,sifre);
        String tasinanAd=getName.toString();
        txtproAdSoyad.setText(tasinanAd);





    }

    public void randevu(View view){
        String eposta=getIntent().getStringExtra("eposta");
        Intent intent=new Intent(getApplicationContext(),RandevuActivity.class);
        intent.putExtra("eposta",eposta);
        startActivity(intent);

    }
    public void hayvanlar(View view){
        String adSoyad=getIntent().getStringExtra("adSoyad");
        String sifre=getIntent().getStringExtra("sifre");
        String eposta=getIntent().getStringExtra("eposta");
        Intent intent=new Intent(this,MyAnimals.class);
        intent.putExtra("eposta",eposta);
        startActivity(intent);

    }
    public void profile(View view){
        String adSoyad=getIntent().getStringExtra("adSoyad");
        String sifre=getIntent().getStringExtra("sifre");
        String eposta=getIntent().getStringExtra("eposta");
        Intent intent=new Intent(AnaSayfa1.this,Profile.class);
        intent.putExtra("adSoyad",adSoyad);
        intent.putExtra("sifre",sifre);
        intent.putExtra("eposta",eposta);
        startActivity(intent);

    }
    public void refresh(View view){
        rAnimalName=new ArrayList<>();
        rBolum=new ArrayList<>();
        rDoctor=new ArrayList<>();
        rTarih=new ArrayList<>();
        recyclerView=findViewById(R.id.randevuRV);
        myAdapterRandevu=new MyAdapterRandevu(this,rAnimalName,rBolum,rDoctor,rTarih);
        recyclerView.setAdapter(myAdapterRandevu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        veriyiGoster();

    }

    private void veriyiGoster(){
        String eposta=getIntent().getStringExtra("eposta");
        Cursor cursor=DB.getRandevuData(eposta);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Henüz hiç randevu almadınız",Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){
                rAnimalName.add(cursor.getString(1));
                rBolum.add(cursor.getString(2));
                rDoctor.add(cursor.getString(3));
                rTarih.add(cursor.getString(4));
            }
        }

    }
}