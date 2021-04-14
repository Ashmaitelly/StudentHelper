package com.abdsh.studenthelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class SchedAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sched_add);
    }
    public void sched_upd(View view) {
        SQLiteOpenHelper sqLiteOpenHelper=new NotesSQLite(this);
        SQLiteDatabase db=sqLiteOpenHelper.getWritableDatabase();
        EditText u=findViewById(R.id.update_text);
        Spinner  us=findViewById(R.id.update_spinner);
        String update=u.getText().toString();
        String uspin=us.getSelectedItem().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("TIME",update);
        db.update("SCHED",
                contentValues,
                "DAY=?",
                new String[]{uspin});

        finish();

    }
}
