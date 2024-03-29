package com.example.veterinerrandevu;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class RandevuActivity extends AppCompatActivity {
    Button tarihSec,randevuOnay;
    EditText edtTarih;
    TextView denek7;
    Spinner spnBolum,spnHekim,spnTarih,spnEvcilHayvan;
    dataBase DB;
    ArrayList<String> asi,icHastalik,kard,cerrah,noro;
    ArrayAdapter<String> arrayAdapter_bolum;
    HashMap<String, Integer> resimMap;


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
        List<String> animalTurList=db.getAnimalTurList(eposta);
        List<String> veri=db.getSpinnerData(eposta);

        CustomSpinnerAdapter adapter=new CustomSpinnerAdapter(this,veri,animalTurList);
        adapter.setDropDownViewResource(R.layout.customspinnerlayout);
        spnEvcilHayvan.setAdapter(adapter);
        spnEvcilHayvan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String currentAnimalTuru = animalTurList.get(i);
                ImageView imageView = view.findViewById(R.id.imageSpinner);
                if (imageView != null) {
                    int resimId = getResimIdForAnimalTur(currentAnimalTuru);
                    imageView.setImageResource(resimId);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }
    public int getResimIdForAnimalTur(String animalTur) {
        int resimId;
        if (animalTur.equals("Kedi")) {
            resimId = R.drawable.catt;
        } else if (animalTur.equals("Köpek")) {
            resimId = R.drawable.dogg;
        } else if (animalTur.equals("Muhabbet Kuşu")) {
            resimId = R.drawable.birdd;
        } else if (animalTur.equals("Tavşan")) {
            resimId = R.drawable.rabbit;
        } else {
            resimId = R.drawable.defaultxx;
        }
        return resimId;
    }

    public class CustomSpinnerAdapter extends ArrayAdapter<String> {


        private final Context context;
        private List<String> animalTurList;
        private List<String> veriListesi;

        public CustomSpinnerAdapter(Context context, List<String> veriListesi,List<String> animalTurList/*int resimId*/) {
            super(context,R.layout.customspinnerlayout,veriListesi);
            this.context=context;
            this.animalTurList=animalTurList;
            this.veriListesi=veriListesi;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.customspinnerlayout, parent, false);

            TextView label = row.findViewById(R.id.textView10);
            label.setText(getItem(position));

            ImageView imageView = row.findViewById(R.id.imageSpinner);
            if (imageView != null) {
                int resimId = getResimIdForAnimalTur(animalTurList.get(position));
                imageView.setImageResource(resimId);
            }

            return row;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        private View getCustomView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.customspinnerlayout, parent, false);
            }


            TextView label = convertView.findViewById(R.id.textView10);
            label.setText(getItem(position));

            ImageView imageView = convertView.findViewById(R.id.imageSpinner);
            if (imageView != null) {
                int resimId = getResimIdForAnimalTur(animalTurList.get(position));
                imageView.setImageResource(resimId);
            }

            return convertView;

        }

    }

    public void loadSpinnerData2(){
        asi=new ArrayList<>();
        asi.add("Sevilay Ercan");

        icHastalik=new ArrayList<>();
        icHastalik.add("Kadir Sevim");
        icHastalik.add("Osman Safa Terzi");

        kard=new ArrayList<>();
        kard.add("Ekrem Çolakoğlu");

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

    public void randevuOnay(View view) throws ParseException {

        String eposta=getIntent().getStringExtra("eposta");
        DB=new dataBase(this);
        TakvimSpinnerAdaptor takvimSpinnerAdaptor=new TakvimSpinnerAdaptor(getApplicationContext(),30);
        String rEvcil=spnEvcilHayvan.getSelectedItem().toString();
        String rBolum=spnBolum.getSelectedItem().toString();
        String rHekim=spnHekim.getSelectedItem().toString();
        String rTarih=takvimSpinnerAdaptor.getSelectedDateAsString(spnTarih);
        SimpleDateFormat format=new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        Date tarih;
        tarih=format.parse(rTarih);
        Log.d("asdas", String.valueOf(tarih));
        Boolean checkRandevu=DB.checkRandevu(rBolum,eposta,rTarih);
        if(checkRandevu==false){
            Boolean insertDataRandevu=DB.insertDataRandevu(eposta,rEvcil,rBolum,rHekim,rTarih);
            if(insertDataRandevu==true){
                KayitOl.ToastUtils.showCustomToast("Randevunuz başarıyla alınmıştır.",0,getApplicationContext());

            }else {
                KayitOl.ToastUtils.showCustomToast("Kayıt başarısız",0,getApplicationContext());

            }
        }else {
            KayitOl.ToastUtils.showCustomToast("Aynı gün içerisinde aynı bölüme randevu alamazsınız. Başka bir gün için deneyin",0,getApplicationContext());
            //Toast.makeText(getApplicationContext(),"Aynı gün içerisinde aynı bölüme randevu alamazsınız. Başka bir gün deneyin",Toast.LENGTH_SHORT).show();

        }


    }



}