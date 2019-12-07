package com.example.pe3datodenisse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    String tableName = "versions";
    String col1 = "ID";
    String col2 = "codeName";
    String col3 = "verNos";
    String col4 = "relDate";
    String col5 = "apiLevel";

    public DBHelper(@Nullable Context context) {
        super(context, "android.db", null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE versions (ID INTEGER PRIMARY KEY AUTO, codeName TEXT, verNos TEXT, relDate TEXT, apiLevel TEXT)";
        db.execSQL(createTable); //executes the statement
        //this.getWritableDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public long insert(String cn, String vn, String rd, String al){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2,cn);
        cv.put(col3,vn);
        cv.put(col4,rd);
        cv.put(col5,al);
        return db.insert(tableName,null, cv);
    }

    public Cursor getTable (String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String selectAll = "SELECT * FROM versions";
        return db.rawQuery(selectAll,null);

    }

    public int delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(tableName, "ID=?", new String[] {id});
    }

    public int update(String id, String cName, String vNo, String rDate, String apiLevel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2,cName);
        cv.put(col3,vNo);
        cv.put(col4, rDate);
        cv.put(col5, apiLevel);
        return db.update(tableName,cv,"ID=?", new String[]{id});
    }
}
