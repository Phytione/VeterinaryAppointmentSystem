package com.example.veterinerrandevu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        String hayvanTuru=DB.getAnimalTur(eposta);
        name=new ArrayList<>();
        sikayet=new ArrayList<>();
        recyclerView=findViewById(R.id.animalsRV);
        List<String> animalTurList=DB.getAnimalTurList(eposta);

        myAdapterAnimal=new MyAdapterAnimal(this,name,sikayet,hayvanTuru,eposta,DB,animalTurList);
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
            KayitOl.ToastUtils.showCustomToast("Henüz hiç evcil hayvan eklemediniz. Sağ üstteki menüyü kullanabilirsiniz",1,getApplicationContext());
            return;
        }else {
            while (cursor.moveToNext()){
                name.add(cursor.getString(1));
                sikayet.add(cursor.getString(4));
            }
        }

    }


}