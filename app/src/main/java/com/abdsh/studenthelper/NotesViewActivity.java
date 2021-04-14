package com.abdsh.studenthelper;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class NotesViewActivity extends AppCompatActivity {
    String edi="";
    private SQLiteDatabase db;
    private Cursor cursor;
    public static final String ITEM_NUMBER = "item_number";
    int sadel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);
        Toolbar myToolbar = findViewById(R.id.view_toolbar);
        setSupportActionBar(myToolbar);
        Intent intent = getIntent();
        int noteNo= intent.getIntExtra(ITEM_NUMBER,0);
        sadel=noteNo;
        try {
            SQLiteOpenHelper NotesSQLiteOpenHelper = new NotesSQLite(this);
            SQLiteDatabase db = NotesSQLiteOpenHelper.getReadableDatabase();
            Cursor cursor = db.query("NOTE",
                    new String[]{"CONTENT"},
                    "_id = ?",
                    new String[]{Integer.toString(noteNo)},
                    null, null, null);
            if (cursor.moveToFirst()) {
                 edi =""+cursor.getString(0);
                EditText content = findViewById(R.id.edn);
                content.setText(edi,TextView.BufferType.EDITABLE);

            }
            cursor.close();
            db.close();
        } catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database not available", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_save:
                Adel(sadel, true);
                return true;
            case R.id.view_delete:
                Adel(sadel, false);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
    public void Adel(int ad,Boolean b){
        SQLiteOpenHelper sqLiteOpenHelper=new NotesSQLite(this);
        SQLiteDatabase db=sqLiteOpenHelper.getWritableDatabase();
        if(b==true){
        EditText s=findViewById(R.id.edn);
        String save=s.getText().toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CONTENT",save);
        db.update("NOTE",
                contentValues,
                "_id=?",
                new String[]{Integer.toString(ad)});
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();}
        else{
            db.delete("NOTE",
                    "_id=?",
                    new String[]{Integer.toString(ad)});
            finish();
        }
    }
}
