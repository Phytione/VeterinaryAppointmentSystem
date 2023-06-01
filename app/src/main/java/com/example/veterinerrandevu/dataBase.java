package com.example.veterinerrandevu;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dataBase extends SQLiteOpenHelper {

    public dataBase(Context context) { super(context, "uyeler.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table users(username TEXT primary key,password TEXT,name TEXT)");
        sqLiteDatabase.execSQL("create table animls(username TEXT ,animalName TEXT,animalTur TEXT,animalYas TEXT,animalSikayet TEXT)");
        sqLiteDatabase.execSQL("create table randevu(username TEXT,animalName TEXT,bolum TEXT,doctor TEXT,tarih TEXT)");
        //sqLiteDatabase.execSQL("create table bolumler(bolumListesi TEXT)");
        //sqLiteDatabase.execSQL("create table doctors(usernameDoctor TEXT primary key,passwordDoctor TEXT,nameDoctor TEXT,bolum TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");
        sqLiteDatabase.execSQL("drop table if exists animals");
        sqLiteDatabase.execSQL("drop table if exists randevu");
       // sqLiteDatabase.execSQL("drop table if exists doctors");


    }
    public Boolean insertData(String username,String password,String name){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("username",username);
        values.put("password",password);
        values.put("name",name);
        long result=db.insert("users",null,values);
        if (result==-1)
            return false;
        else
            return true;
    }
    public Boolean insertDataRandevu(String username,String animalName,String bolum,String doctor,String tarih){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("animalName",animalName);
        values.put("bolum",bolum);
        values.put("doctor",doctor);
        values.put("tarih",tarih);
        long result=db.insert("randevu",null,values);
        if(result==-1)
            return false;
        else
            return true;
    }


     @SuppressLint("Range")
     public String getName(String username, String password){
         String dataName="";
         SQLiteDatabase db=this.getReadableDatabase();
         Cursor cursor=db.rawQuery("select name from users where username=? and password=?",new String[]{username,password});
         if (cursor.getCount()>0){
             cursor.moveToFirst();
             dataName=cursor.getString(cursor.getColumnIndex("name"));

         }
         return dataName;

     }
    @SuppressLint("Range")
    public String getNameforHekim(String username){
        String dataName="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select name from users where username=?",new String[]{username});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            dataName=cursor.getString(cursor.getColumnIndex("name"));

        }
        return dataName;

    }

    @SuppressLint("Range")
    public String getUsernameforHekim(String animalName){
        String dataName="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select username from animals where animalName=?",new String[]{animalName});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            dataName=cursor.getString(cursor.getColumnIndex("username"));

        }
        return dataName;

    }

    @SuppressLint("Range")
    public String getNameDoctor(String username, String password){
        String dataName="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select nameDoctor from doctors where usernameDoctor=? and passwordDoctor=?",new String[]{username,password});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            dataName=cursor.getString(cursor.getColumnIndex("nameDoctor"));

        }
        return dataName;

    }
    @SuppressLint("Range")
    public String getAnimalTur(String username){
        String dataName="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select animalTur from animals where username=?",new String[]{username});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            dataName=cursor.getString(cursor.getColumnIndex("animalTur"));

        }
        return dataName;

    }

    public List<String> getAnimalTurList(String email) {
        List<String> animalTurList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT animalTur FROM animals WHERE username = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String animalTur = cursor.getString(cursor.getColumnIndex("animalTur"));
                animalTurList.add(animalTur);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return animalTurList;
    }

    @SuppressLint("Range")
    public String getAnimalSikayet(String username){
        String dataName="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select animalSikayet from animals where username=?",new String[]{username});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            dataName=cursor.getString(cursor.getColumnIndex("animalSikayet"));

        }
        return dataName;

    }
    public List<Animal> getAnimalsByEmail(String email) {
        List<Animal> animals = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT animalTur FROM animals WHERE username = ?", new String[]{email});
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String animalTur = cursor.getString(cursor.getColumnIndex("animalTur"));
                Animal animal = new Animal(animalTur);
                animals.add(animal);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return animals;
    }


    @SuppressLint("Range")
    public String getHastaName(String username){
        String dataName="";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select name from users where username=?",new String[]{username});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            dataName=cursor.getString(cursor.getColumnIndex("name"));

        }
        return dataName;

    }


    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select*from users where username=?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkRandevu(String bolum,String username,String tarih){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select*from randevu where bolum=? and username=? and tarih=?",new String[]{bolum,username,tarih});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }



    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepasswordDoctor(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from doctors where usernameDoctor=? and passwordDoctor=?",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public boolean insertDataAnimals(String username,String animalName,String animalTur,String animalYas,String sikayet){

            SQLiteDatabase db=this.getWritableDatabase();
            ContentValues values=new ContentValues();

            values.put("username",username);
            values.put("animalName",animalName);
            values.put("animalTur",animalTur);
            values.put("animalYas",animalYas);
            values.put("animalSikayet",sikayet);
            long result=db.insert("animals",null,values);
            if (result==-1)
                return false;
            else
                return true;
    }
    /*public boolean insertDataDoctors(String usernameDoctor,String passwordDoctor,String nameDoctor,String bolum){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("usernameDoctor",usernameDoctor);
        values.put("passwordDoctor",passwordDoctor);
        values.put("nameDoctor",nameDoctor);
        values.put("bolum",bolum);
        long result=db.insert("doctors",null,values);
        if(result==-1)
            return false;
        else
            return true;

    }*/
    @SuppressLint("Range")
    public String getRawCountAnimal(String username){
        SQLiteDatabase db=this.getReadableDatabase();
        String sQty;
        Cursor cursor=db.rawQuery("select count(*)from animals where username=?",new String[]{username});

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            sQty=cursor.getString(cursor.getColumnIndex("count(*)"));
        }else
            sQty="0";


        db.close();
        return sQty;

    }
    public List<String> getSpinnerData(String username) {
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select animalName from animals where username=?",new String[]{username});
        if(cursor.moveToFirst()){
            do {
                veriler.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return veriler;

    }

    public List<String> getAnimalName(String username){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select animalName from animals where username=?",new String[]{username});
        List<String> animalNames=new ArrayList<String>();

        if (cursor.moveToFirst()){
            do {
                animalNames.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return animalNames;

    }
   /* public Boolean update(String name,String password,String username){

       /* SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("update users set name=?,password=? where username=?",new String[]{name,password,username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;


    }*/

    public Cursor getAnimalData(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select*from animals where username=?",new String[]{username});
        return cursor;
    }

    public Cursor getRandevuData(String username){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select*from randevu where username=?",new String[]{username});
        return cursor;
    }

    public Cursor getRandevuforHekim(String doctor){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select*from randevu where doctor=?",new String[]{doctor});
        return cursor;
    }


    public List<String> getSpinnerDataBolum() {
        List<String> veriler=new ArrayList<String>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select bolumListesi from bolumler",null);
        if(cursor.moveToFirst()){
            do {
                veriler.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return veriler;

    }








    /*public List<String> getAnimalData(String username){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("select animalName from animals where username=?",new String[]{username});
        List<String> animalNames=new ArrayList<String>();

        if (cursor.moveToFirst()){
            do {
                animalNames.add(cursor.getString(1));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return animalNames;

    }*/


}
