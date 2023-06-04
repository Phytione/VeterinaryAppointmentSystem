package com.example.veterinerrandevu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KayitOl extends AppCompatActivity {
    EditText edtAdSoyad,edtEposta,edtSifre;
    TextView hesapGiris;
    Button btnKayit;
    dataBase DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);
        edtAdSoyad=findViewById(R.id.edtAdsoyad);
        edtEposta=findViewById(R.id.edtEpostaP);
        edtSifre=findViewById(R.id.edtSifre);
        btnKayit=findViewById(R.id.btnKayit);
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
            ToastUtils.showCustomToast("Tüm alanların doldurulması gerekir",0,KayitOl.this);
        }
        else {
            Boolean kontrol=emailValidator(edtEposta);
            if(kontrol){
                Boolean checkuser = DB.checkusername(user);
                Log.d("check", String.valueOf(checkuser));
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


                    ToastUtils.showCustomToast("Girilen mail adresi sistemimizde kayıtlıdır. Lütfen giriş yapınız veya başka bir mail adresi kullanınız.",1,KayitOl.this);

                }

            }else{


                ToastUtils.showCustomToast("Geçerli bir e-mail adresi girin",0,KayitOl.this);

            }

        }


    }
    public static class ToastUtils{

        public static void showCustomToast(String message, int sayi, Context context){

            LayoutInflater inflater=LayoutInflater.from(context);
            View layout=inflater.inflate(R.layout.custom_toast,null);

            TextView toastTextView=layout.findViewById(R.id.toast_text);
            toastTextView.setText(message);

            Toast toast=new Toast(context);
            toast.setGravity(Gravity.BOTTOM, 0, 200);

            toast.setView(layout);

            toast.setDuration(sayi);

            toast.show();
        }

    }










    public boolean emailValidator(EditText editText){

        String emailToText=edtEposta.getText().toString();
        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            //Toast.makeText(this, "Email Verified !", Toast.LENGTH_SHORT).show();
            return true;
        } else {

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