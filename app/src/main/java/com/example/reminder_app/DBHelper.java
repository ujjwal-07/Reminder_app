package com.example.reminder_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
     public DBHelper( Context context) {
        super(context, "storeList",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {

        DB.execSQL("create Table storelistdata(list Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists storelistdata");
    }

    public Boolean insertlist(String list){

         SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("list",list);
        long result = DB.insert("storelistdata", null, contentValues);
        if (result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("select * from storelistdata", null);
        return cursor;
    }
}
