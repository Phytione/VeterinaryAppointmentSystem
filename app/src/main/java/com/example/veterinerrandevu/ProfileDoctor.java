package com.example.veterinerrandevu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ProfileDoctor extends AppCompatActivity {
    EditText edtAdSoyadPD,edtEpostaPD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);
        edtAdSoyadPD=findViewById(R.id.edtAdSoyadPD);
        edtEpostaPD=findViewById(R.id.edtEpostaPD);
        String nameDataDoctor=getIntent().getStringExtra("nameDataDoctor");
        String usernameDoctor=getIntent().getStringExtra("usernamedoctor");

        edtAdSoyadPD.setText(nameDataDoctor);
        edtEpostaPD.setText(usernameDoctor);
        edtAdSoyadPD.setEnabled(false);
        edtEpostaPD.setEnabled(false);



    }

    public void cikisYap2(View view){
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finishAffinity();

    }
}