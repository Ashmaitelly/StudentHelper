package com.abdsh.studenthelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesSQLite extends SQLiteOpenHelper {

    private static final String DB_NAME = "NOTE_PART";
    private static final int DB_VERSION = 1;

    public NotesSQLite(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE NOTE (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "CONTENT TEXT);");
        insertNote(db,"Create A New Note"," ");

        db.execSQL("CREATE TABLE SCHED (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "DAY TEXT," +
                "TIME TEXT);");
        insertSched(db,"Monday"," ");
        insertSched(db,"Tuesday"," ");
        insertSched(db,"Wednesday"," ");
        insertSched(db,"Thursday"," ");
        insertSched(db,"Friday"," ");
    }

    private static void insertNote(SQLiteDatabase db, String name,
                                    String content) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", name);
        contentValues.put("CONTENT", content);
        db.insert("NOTE", null, contentValues);
    }
    private static void insertSched(SQLiteDatabase db, String day,
                                   String time) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("DAY", day);
        contentValues.put("TIME", time);
        db.insert("SCHED", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}