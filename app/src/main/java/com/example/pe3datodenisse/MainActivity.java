package com.example.pe3datodenisse;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName, etNumber, etDate, etLevel;
    com.example.pe3datodenisse.DBHelper helper;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new com.example.pe3datodenisse.DBHelper(this);
        cursor = helper.getTable();
        etName = findViewById(R.id.et1);
        etNumber = findViewById(R.id.et2);
        etDate = findViewById(R.id.et3);
        etLevel= findViewById(R.id.et4);
    }

    public void addRecord(View v){
        String codeName         = etName.getText().toString();
        String versionNumber    = "Ver. "+etNumber.getText().toString();
        String releaseDate      = "Released "+etDate.getText().toString();
        String APILevel         = "API Level"+etLevel.getText().toString();
        long isInserted = helper.insert(codeName, versionNumber, releaseDate, APILevel);
        if (isInserted == -1){
            Toast.makeText(this, "Record not added", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Record saved..", Toast.LENGTH_LONG).show();
        }
    }

    public void moveFirst(View v){
        cursor.moveToFirst();
        String codeName         = cursor.getString(1);
        String versionNumber    = cursor.getString(2);
        String releaseDate      = cursor.getString(3);
        String APILevel         = cursor.getString(4);
        etName.setText(codeName);
        etNumber.setText(versionNumber);
        etDate.setText(releaseDate);
        etLevel.setText(APILevel);
    }

    public void moveNext(View v) {
        cursor.moveToNext();
        String codeName = cursor.getString(1);
        String versionNumber = cursor.getString(2);
        String releaseDate = cursor.getString(3);
        String APILevel = cursor.getString(4);
        etName.setText(codeName);
        etNumber.setText(versionNumber);
        etDate.setText(releaseDate);
        etLevel.setText(APILevel);
    }

    public void moveBack (View v){
        cursor.moveToPrevious();
        String codeName = cursor.getString(1);
        String versionNumber = cursor.getString(2);
        String releaseDate = cursor.getString(3);
        String APILevel = cursor.getString(4);
        etName.setText(codeName);
        etNumber.setText(versionNumber);
        etDate.setText(releaseDate);
        etLevel.setText(APILevel);
    }

    public void moveLast (View v){
        cursor.moveToLast();
        String codeName = cursor.getString(1);
        String versionNumber = cursor.getString(2);
        String releaseDate = cursor.getString(3);
        String APILevel = cursor.getString(4);
        etName.setText(codeName);
        etNumber.setText(versionNumber);
        etDate.setText(releaseDate);
        etLevel.setText(APILevel);
    }

    public void editRecord(View v){
        String codeName        = etName.getText().toString();
        String versionNumber   = "Ver. "+etNumber.getText().toString();
        String releaseDate     = "Released "+etDate.getText().toString();
        String APILevel        = "API Level"+etLevel.getText().toString();
        String id              = cursor.getString(0);
        int numUpdated = helper.update(id, codeName, versionNumber, releaseDate, APILevel);
        if (numUpdated == 1){
            Toast.makeText(this, "Record updated..", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Update failed..", Toast.LENGTH_LONG).show();
        }
    }

    public void removeRecord(View v){
        String id = cursor.getString(0);
        int numDeleted =  helper.delete(id);
        if (numDeleted == 1){
            Toast.makeText(this, "Record removed..", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, "Deletion failed..", Toast.LENGTH_LONG).show();
        }
    }

    public void clearRecord(View v){
        etName.setText("");
        etNumber.setText("");
        etDate.setText("");
        etLevel.setText("");

    }


}
