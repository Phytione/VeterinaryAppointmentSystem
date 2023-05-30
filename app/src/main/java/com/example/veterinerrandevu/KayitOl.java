package com.example.veterinerrandevu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class KayitOl extends AppCompatActivity {
    EditText edtAdSoyad,edtEposta,edtSifre;
    TextView hesapGiris;
    Button btnKayit,hekimKayit;
    dataBase DB;
    databaseHekim dbHekim;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        edtAdSoyad=findViewById(R.id.edtAdsoyad);
        edtEposta=findViewById(R.id.edtEpostaP);
        edtSifre=findViewById(R.id.edtSifre);
        btnKayit=findViewById(R.id.btnKayit);
        //hekimKayit=findViewById(R.id.hekimKayit);
        hesapGiris=findViewById(R.id.hesapGiris);
        DB=new dataBase(this);



    }
    public void hesapGiris(View view){
        //giriş yap a at verileri çekerek
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void kayit(View view) {
        String user = edtEposta.getText().toString();
        String name = edtAdSoyad.getText().toString();
        String pass = edtSifre.getText().toString();
        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(name)) {
            Toast.makeText(KayitOl.this, "Tüm alanların doldurulması gerekir", Toast.LENGTH_SHORT).show();
        }
        else {
            Boolean kontrol=emailValidator(edtEposta);
            if(kontrol){
                Boolean checkuser = DB.checkusername(user);
                if (checkuser == false) {
                    Boolean insert = DB.insertData(user, pass, name);
                    if (insert == true) {
                        Toast.makeText(KayitOl.this, "Kayıt Başarılı", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        Intent intent2=new Intent(getApplicationContext(),MyAdapterAnimal.class);
                        intent.putExtra("adSoyad", name);
                        intent.putExtra("eposta",user);
                        intent.putExtra("sifre",pass);
                        intent2.putExtra("eposta",user);
                        startActivity(intent);
                        startActivity(intent2);
                        this.finish();
                    } else {
                        Toast.makeText(KayitOl.this, "Kayıt başarısız", Toast.LENGTH_SHORT).show();
                    }
                } else {

                }

            }else{
                Toast.makeText(this, "Geçerli bir e-mail adresi girin", Toast.LENGTH_SHORT).show();

            }

        }


    }
    public boolean emailValidator(EditText editText){

        String emailToText=edtEposta.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            //Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Geçerli bir e-mail adresi girin", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    /*public void hekimKaydi(View view){
        String user = edtEposta.getText().toString();
        String name = edtAdSoyad.getText().toString();
        String pass = edtSifre.getText().toString();


        if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(name)) {
            Toast.makeText(KayitOl.this, "Tüm alanların doldurulması gerekir", Toast.LENGTH_SHORT).show();
        }

        //şifre iste ve şifre doğruysa kayıt et

    }*/
}