package com.qays3dd.sqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DB_Sqlit extends SQLiteOpenHelper {
    public static final String BDname = "data.db";

    public DB_Sqlit(@Nullable Context context) {
        super(context, BDname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mytable (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, naumber TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldversion , int newversion) {
        db.execSQL("DROP TABLE IF EXISTS mytable");
        onCreate(db);

    }
//  TODO for add data
    public boolean insertData(String name, String naumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues ContentValues = new ContentValues();

        ContentValues.put("name", name);
        ContentValues.put("naumber", naumber);

        long result = db.insert("mytable", null, ContentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public ArrayList getAllrecord() {

        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from mytable",null);

        res.moveToFirst();

        while (res.isAfterLast()==false) {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);

            arrayList.add(t1 +" - "+ t2 +"  "+ t3);
            res.moveToNext();
        }
        return  arrayList;
    }

//  TODO  خاص ب الاب ديت for update btn
    public boolean updateData (String id, String name,String naumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("naumber", naumber);
        db.update("mytable", contentValues,"id= ?",new String[]{id});
        return  true;

    }

    public Integer Delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete("mytable", "id = ?",new String[]{id});
    }

}
/*
 public boolean updateData (String id, String name,String naumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        ContentValues.put("name", name);
        ContentValues.put("naumber", naumber);
        db.update("mytable", contentValues,"id= ?",new String[]{id});
        return  true;
 */