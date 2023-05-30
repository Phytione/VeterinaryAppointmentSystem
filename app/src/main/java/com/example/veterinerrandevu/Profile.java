package com.example.veterinerrandevu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Profile extends AppCompatActivity {

    EditText edtNewPassP,edtMevcutPassP,edtAdSoyadP,edtEpostaP;
    Button buttonProfile;
    dataBase DB;
    public int pressCounter=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        DB=new dataBase(this);
        String adSoyad=getIntent().getStringExtra("adSoyad");
        String sifre=getIntent().getStringExtra("sifre");
        String eposta=getIntent().getStringExtra("eposta");
        //buttonProfile = findViewById(R.id.buttonProfile);
        edtAdSoyadP = findViewById(R.id.edtAdSoyadP);
        edtEpostaP = findViewById(R.id.edtEpostaP);
        //edtMevcutPassP = findViewById(R.id.edtMevcutPassP);
        //edtNewPassP = findViewById(R.id.edtNewPassP);
        edtAdSoyadP.setText(adSoyad);
        edtEpostaP.setText(eposta);
        edtEpostaP.setEnabled(false);

    }
    /*public void duzenle(View view){

        String adSoyad=getIntent().getStringExtra("adSoyad");
        String sifre=getIntent().getStringExtra("sifre");
        String eposta=getIntent().getStringExtra("eposta");
        pressCounter++;
        if (pressCounter%2==1){//tek sayı ise
            edtAdSoyadP.setEnabled(true);
            edtMevcutPassP.setEnabled(true);
            edtNewPassP.setEnabled(true);
            buttonProfile.setText("Onayla");
        }else if(pressCounter%2==0){
            //veritabanını burada değiştir
            String newAdSoyad=edtAdSoyadP.getText().toString();
            String mevcutSifre=edtMevcutPassP.getText().toString();
            String yeniSifre=edtNewPassP.getText().toString();


            if(sifre.equals(mevcutSifre)){
                Boolean update=DB.update(newAdSoyad,yeniSifre,adSoyad);
                if(update==true){
                    Toast.makeText(Profile.this, "Bilgileriniz başarıyla güncellenci", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Bilgileriniz güncellenemedi. Tekrar deneyiniz",Toast.LENGTH_SHORT).show();
                }


            }
            else if(sifre!=mevcutSifre){
                System.out.println("ysnlış");
                Toast.makeText(Profile.this, "Mevcut şifreniz aynı değil, tekrar deneyiniz", Toast.LENGTH_SHORT).show();
            }
            edtAdSoyadP.setEnabled(false);
            edtMevcutPassP.setEnabled(false);
            edtNewPassP.setEnabled(false);
            buttonProfile.setText("Duzenle");

        }






    }*/
    public void cikisYap(View view){
        Intent intent=new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finishAffinity();


    }
}