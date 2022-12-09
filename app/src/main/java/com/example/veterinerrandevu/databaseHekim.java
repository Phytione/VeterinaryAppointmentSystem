package com.example.veterinerrandevu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class databaseHekim extends SQLiteOpenHelper {
    public static final String DBNAME="hekimler.db";
    public databaseHekim(Context context) {super(context, "hekimler.db", null, 1);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table doctors(username TEXT primary key,password TEXT,name TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists doctors");

    }
    public Boolean insertData(String username,String password,String name){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("username",username);
        values.put("password",password);
        values.put("name",name);
        long result=db.insert("doctors",null,values);
        if (result==-1) return false;
        else
            return true;
    }
    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select*from doctors where username=?",new String[]{username});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from doctors where username=? and password=?",new String[]{username,password});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
