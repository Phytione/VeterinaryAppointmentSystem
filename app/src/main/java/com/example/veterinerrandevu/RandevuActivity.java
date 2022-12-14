package com.example.veterinerrandevu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RandevuActivity extends AppCompatActivity {
    Button tarihSec,randevuOnay;
    EditText edtTarih;
    TextView denek7;
    Spinner spnBolum,spnHekim,spnTarih,spnEvcilHayvan;
    dataBase DB;
    ArrayList<String> asi,icHastalik,kard,cerrah,noro;
    ArrayAdapter<String> arrayAdapter_bolum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randevu);
        String eposta=getIntent().getStringExtra("eposta");
        DB=new dataBase(this);
        spnEvcilHayvan=findViewById(R.id.spnEvcilHayvan);
        randevuOnay=findViewById(R.id.randevuOnay);
        spnBolum=findViewById(R.id.spnBolum);
        spnHekim=findViewById(R.id.spnHekim);
        spnTarih=findViewById(R.id.spnTarih);


        //TakvimSpinnerAdaptor takvimSpinnerAdaptor=new TakvimSpinnerAdaptor(getApplicationContext(),30);
        spnTarih.setAdapter(new TakvimSpinnerAdaptor(getApplicationContext(),30));



        loadSpinnerData1();
        loadSpinnerData2();





    }

    public void loadSpinnerData1(){
        String eposta=getIntent().getStringExtra("eposta");
        dataBase db=new dataBase(getApplicationContext());
        List<String> veri=db.getSpinnerData(eposta);
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,veri);
        spnEvcilHayvan.setAdapter(dataAdapter);
    }

    public void loadSpinnerData2(){
        asi=new ArrayList<>();
        asi.add("Sevilay Ercan");

        icHastalik=new ArrayList<>();
        icHastalik.add("Kadir Sevim");
        icHastalik.add("Osman Safa Terzi");

        kard=new ArrayList<>();
        kard.add("Ekrem ??olako??lu");

        cerrah=new ArrayList<>();
        cerrah.add("Murat Demir");
        cerrah.add("Tugce Demir");

        noro=new ArrayList<>();
        noro.add("SevilayKeser");


        dataBase db=new dataBase(getApplicationContext());
        List<String> veri=db.getSpinnerDataBolum();
        ArrayAdapter<String> dataAdapter=new ArrayAdapter<String>(this,androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,veri);
        spnBolum.setAdapter(dataAdapter);

        spnBolum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    arrayAdapter_bolum=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,asi);
                }
                if (i==1){
                    arrayAdapter_bolum=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,icHastalik);
                }
                if (i==2){
                    arrayAdapter_bolum=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,kard);
                }
                if (i==3){
                    arrayAdapter_bolum=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,cerrah);
                }
                if (i==4){
                    arrayAdapter_bolum=new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,noro);
                }
                spnHekim.setAdapter(arrayAdapter_bolum);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    public void randevuOnay(View view){
        String eposta=getIntent().getStringExtra("eposta");
        DB=new dataBase(this);
        TakvimSpinnerAdaptor takvimSpinnerAdaptor=new TakvimSpinnerAdaptor(getApplicationContext(),30);
        //String stringTarih=takvimSpinnerAdaptor.getSelectedDateAsString(spnTarih);
        String rEvcil=spnEvcilHayvan.getSelectedItem().toString();
        String rBolum=spnBolum.getSelectedItem().toString();
        String rHekim=spnHekim.getSelectedItem().toString();
        String rTarih=takvimSpinnerAdaptor.getSelectedDateAsString(spnTarih);
        Boolean checkRandevu=DB.checkRandevu(rBolum,eposta,rTarih);
        if(checkRandevu==false){
            Boolean insertDataRandevu=DB.insertDataRandevu(eposta,rEvcil,rBolum,rHekim,rTarih);
            if(insertDataRandevu==true){
                Toast.makeText(getApplicationContext(),"Randevunuz ba??ar??yla al??nm????t??r. K??sa mesaj ile bilgilendirileceksiniz",Toast.LENGTH_SHORT).show();

            }else {
                Toast.makeText(getApplicationContext(), "Kay??t ba??ar??s??z", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(getApplicationContext(),"Ayn?? g??n i??erisinde ayn?? b??l??me randevu alamazs??n??z. Ba??ka bir g??n deneyin",Toast.LENGTH_SHORT).show();

        }


    }



}