package com.example.veterinerrandevu;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText edtEposta;
    EditText edtSifre;
    TextView txtKayit;
    Button btnGiris;
    dataBase DB;
    databaseHekim dataB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtEposta=findViewById(R.id.edtEpostaP);
        edtSifre=findViewById(R.id.edtSifre);
        btnGiris=findViewById(R.id.btnGiris);
        txtKayit=findViewById(R.id.txtKayit);
        DB= new dataBase(this);
        dataB=new databaseHekim(this);
        DB.getReadableDatabase();


    }

    public void kayitOl(View view){

        Intent intent = new Intent(MainActivity.this,KayitOl.class);
        startActivity(intent);
    }
    public void hekimGiris(View view){

        String user=edtEposta.getText().toString();
        String pass=edtSifre.getText().toString();
        if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)){
            Toast.makeText(MainActivity.this,"Tüm alanların doldurulması gerekir",Toast.LENGTH_SHORT).show();
        }
        else{
            Boolean kontrol=emailValidator(edtEposta);
            Boolean checkloginDoctor=DB.checkusernamepasswordDoctor(user,pass);
            if(kontrol){
                if (checkloginDoctor==true){
                    String getNameDoctor=DB.getNameDoctor(user,pass);
                    String nameDataDoctor=getNameDoctor.toString();
                    //String getHastaName=DB.getHastaName(user);
                    //String hastaName=getHastaName.toString();
                    Toast.makeText(this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(),HekimActivity.class);
                    intent.putExtra("nameDoctor",nameDataDoctor);
                    intent.putExtra("passwordDoctor",pass);
                    intent.putExtra("epostaDoctor",user);
                    finish();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "Kullanıcı adınız veya şifreniz yanlış,kontrol ediniz", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "Geçerli bir e-mail adresi girin", Toast.LENGTH_SHORT).show();

            }


        }

    }
    public void girisYap(View view){

        String adSoyad=getIntent().getStringExtra("adSoyad");
        String sifre=getIntent().getStringExtra("sifre");
        String eposta=getIntent().getStringExtra("eposta");
        String user=edtEposta.getText().toString();
        String pass=edtSifre.getText().toString();
        if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)){
            KayitOl.ToastUtils.showCustomToast("Tüm alanların doldurulması gerekir",0,MainActivity.this);
        }
        else{
            try {

                Boolean kontrol=emailValidator(edtEposta);
                Boolean checklogin = DB.checkusernamepassword(user,pass);
                if(kontrol){
                    if (checklogin == true) {
                        String getName=DB.getName(user,pass);
                        String nameData=getName.toString();
                        Toast.makeText(this, "Giriş başarılı", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AnaSayfa1.class);
                        Intent intent2= new Intent(getApplicationContext(),MyAdapterAnimal.class);
                        intent.putExtra("adSoyad", nameData);
                        intent.putExtra("sifre", pass);
                        intent.putExtra("eposta", user);
                        intent2.putExtra("eposta",user);
                        startActivity(intent);
                        startActivity(intent2);



                    } else {
                        KayitOl.ToastUtils.showCustomToast("Kullanıcı adınız veya şifreniz yanlış, kontrol ediniz",0,MainActivity.this);
                    }

                }else{
                    KayitOl.ToastUtils.showCustomToast("Geçerli bir e-mail adresi girin",0,this);

                }

            } catch (Exception e) {
                KayitOl.ToastUtils.showCustomToast("Beklenmeyen bir sorun oluştu.Tekrar deneyiniz",0,MainActivity.this);
                System.out.println(e);
            }
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
}