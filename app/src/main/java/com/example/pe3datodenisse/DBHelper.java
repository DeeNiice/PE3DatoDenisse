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
    String col2 = "CodeName";
    String col3 = "VersionNumber";
    String col4 = "ReleaseDate";
    String col5 = "APILevel";

    public DBHelper(@Nullable Context context){
        super (context, "android.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE versions(ID INTEGER PRIMARY KEY AUTOINCREMENT, CodeName TEXT, VersionNumber TEXT, ReleaseDate TEXT, APILevel TEXT)";
        db.execSQL(createTable);

    }

    //CRUD settings
    public Cursor getTable() {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectAll = "SELECT * FROM versions";
        return db.rawQuery(selectAll, null);
    }

    public long insert(String cn, String vn, String rd, String al) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, cn);
        cv.put(col3, vn);
        cv.put(col4, rd);
        cv.put(col5, al);
        return db.insert(tableName, null, cv);
    }

    public int delete(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "ID=?", new String[]{id});
    }

    public int update(String id, String cName, String vNo, String rDate, String apiLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col2, cName);
        cv.put(col3, vNo);
        cv.put(col4, rDate);
        cv.put(col5, apiLevel);
        return db.update(tableName, cv, "ID=?", new String[]{id});
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
