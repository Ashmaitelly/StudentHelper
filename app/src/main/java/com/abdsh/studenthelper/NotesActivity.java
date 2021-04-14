package com.abdsh.studenthelper;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NotesActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        if (position==0){
            Intent intent=new Intent(NotesActivity.this,NoteAddActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(NotesActivity.this, NotesViewActivity.class);
            intent.putExtra(NotesViewActivity.ITEM_NUMBER, (int) id);
            startActivity(intent);

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        ListView listView = getListView();
        try {
            SQLiteOpenHelper sqLiteOpenHelper = new NotesSQLite(this);
            db = sqLiteOpenHelper.getReadableDatabase();
            cursor = db.query("NOTE",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);

            CursorAdapter cursorAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listView.setAdapter(cursorAdapter);
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }
}