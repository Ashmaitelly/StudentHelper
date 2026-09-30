package com.abdsh.studyapp;

import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class NotesActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    private void onListItemClick(int position, long id) {
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
        ListView listView = findViewById(R.id.notes_list);
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
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.notes_toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Notes");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ListView listView = findViewById(R.id.notes_list);
        listView.setOnItemClickListener((parent, view, position, id) -> onListItemClick(position, id));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
