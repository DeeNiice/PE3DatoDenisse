package com.example.pe3datodenisse;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText cn, vn, rd, al;
    DBHelper helper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cursor = helper.getTable();
        helper = new DBHelper(this);
        cn = findViewById(R.id.et1);
        vn = findViewById(R.id.et2);
        rd = findViewById(R.id.et3);
        al = findViewById(R.id.et4);

    }

    public void addRecord(View v){
        String codeName = cn.getText().toString();
        String verNo = vn.getText().toString();
        String rDate = rd.getText().toString();
        String api = al.getText().toString();
        long isInserted =  helper.insert(codeName, verNo, rDate, api);
        if(isInserted == -1){
            Toast.makeText(this, "record not inserted", Toast.LENGTH_LONG);
        }else{
            Toast.makeText(this,"record inserted", Toast.LENGTH_LONG);
        }
    }

    public void moveFirst(View v){

        cursor.moveToFirst();
        cursor.getString();
        String codeName = cursor.getString(1);
        String verNo = cursor.getString(2);
        String relDate = cursor.getString(3);
        String apiLevel = cursor.getString(4);

        cn.setText(codeName);
        vn.setText(verNo);
        rd.setText(relDate);
        al.setText(apiLevel);
    }

    public void moveNext(View v){

        cursor.moveToNext();
        cursor.getString();
        String codeName = cursor.getString(1);
        String verNo = cursor.getString(2);
        String relDate = cursor.getString(3);
        String apiLevel = cursor.getString(4);

        cn.setText(codeName);
        vn.setText(verNo);
        rd.setText(relDate);
        al.setText(apiLevel);

    }

    public void removeRecord(View v){
        String id = cursor.getString(0);
        int numDeleted = helper.delete(id);

        if(numDeleted == 1){
            Toast.makeText(this,"record removed", Toast.LENGTH_LONG);
        }else{
            Toast.makeText(this,"record not removed", Toast.LENGTH_LONG);
        }
    }

    public void editRecord(View v){
        String codeName = cn.getText().toString();
        String verNo = vn.getText().toString();
        String rDate = rd.getText().toString();
        String api = al.getText().toString();
        String id = cursor.getString(0);
        int numUpdated = helper.update(id, codeName,verNo,rDate,api);

        if(numUpdated == 1){
            Toast.makeText(this,"record updated", Toast.LENGTH_LONG);
        } else{
            Toast.makeText(this,"record not updated", Toast.LENGTH_LONG);
        }
    }



}
