package com.abdsh.studenthelper;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;

public class NoteAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
    }

    public void add_note(View view) {
        EditText editText = findViewById(R.id.add_text);
        String name = editText.getText().toString();
        SQLiteOpenHelper sqLiteOpenHelper = new NotesSQLite(this);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("CONTENT", " ");
        db.insert("NOTE", null, contentValues);
        finish();

    }
}