package com.example.veterinerrandevu;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyAnimals extends AppCompatActivity {


   // TextView denek5;
    dataBase DB;
    RecyclerView recyclerView;
    ArrayList<String> name,sikayet;
    MyAdapterAnimal myAdapterAnimal;






    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animals);
        DB=new dataBase(this);
        String eposta=getIntent().getStringExtra("eposta");
        name=new ArrayList<>();
        sikayet=new ArrayList<>();
        recyclerView=findViewById(R.id.animalsRV);
        myAdapterAnimal=new MyAdapterAnimal(this,name,sikayet);
        recyclerView.setAdapter(myAdapterAnimal);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        veriyiGoster();
        //String animalDataTemp=DB.getRawCountAnimal(eposta);
        //String animalDatas=animalDataTemp.toString(); // sahip olduğum hayvan sayısı
        //denek5.setText(animalDatas);
        //animalList=findViewById(R.id.animalsList);
        //animalList.setAdapter((ListAdapter) DB.getAnimalName(eposta));

        //list olarak yaptığın veriyi setadapter yap





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        String eposta=getIntent().getStringExtra("eposta");
        int id=item.getItemId();
        if(id==R.id.hayvanEkle){
            Intent intent=new Intent(getApplicationContext(),addAnimal.class);
            intent.putExtra("eposta",eposta);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    private void veriyiGoster(){
        String eposta=getIntent().getStringExtra("eposta");
        Cursor cursor=DB.getAnimalData(eposta);
        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"Henüz hiç evcil hayvan eklemediniz. Sağ üstteki butonu kullanabilirsiniz",Toast.LENGTH_LONG).show();
            return;
        }else {
            while (cursor.moveToNext()){
                name.add(cursor.getString(1));
                sikayet.add(cursor.getString(4));
            }
        }

    }


}