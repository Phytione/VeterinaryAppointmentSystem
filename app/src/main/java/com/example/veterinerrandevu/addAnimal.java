package com.example.veterinerrandevu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addAnimal extends AppCompatActivity {
    EditText newAnimal_isim,newAnimal_tur,newAnimal_yas,newAnimal_sikayet;
    Button newAnimal_save,newAnimal_cancel;
    dataBase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);
        DB=new dataBase(this);
        newAnimal_isim=findViewById(R.id.newAnimal_isim);
        newAnimal_tur=findViewById(R.id.newAnimal_tur);
        newAnimal_sikayet=findViewById(R.id.newAnimal_sikayet);
        newAnimal_yas=findViewById(R.id.newAnimal_yas);

        newAnimal_save=findViewById(R.id.newAnimal_save);

    }
    public void saveAnimal(View view){


        String eposta=getIntent().getStringExtra("eposta");
        String animalName=newAnimal_isim.getText().toString();
        String animalTur=newAnimal_tur.getText().toString();
        String animalYas=newAnimal_yas.getText().toString();
        String sikayet=newAnimal_sikayet.getText().toString();


        if (TextUtils.isEmpty(animalName) || TextUtils.isEmpty(animalTur) || TextUtils.isEmpty(animalYas) || TextUtils.isEmpty(sikayet)) {
            Toast.makeText(getApplicationContext(), "Tüm alanların doldurulması gerekir", Toast.LENGTH_SHORT).show();
        }else{
            try {
                Boolean insertDataAnimals=DB.insertDataAnimals(eposta,animalName,animalTur,animalYas,sikayet);
                if(insertDataAnimals == true){
                    Toast.makeText(getApplicationContext(),"Kayıt Başarılı",Toast.LENGTH_SHORT).show();
                    String getAnimalTur=DB.getAnimalTur(eposta);
                    String nameData=getAnimalTur.toString();
                    Intent intent=new Intent(getApplicationContext(),MyAnimals.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"Kayıt Başarısız",Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                System.out.println(e);
                e.printStackTrace();
            }

        }



    }

}