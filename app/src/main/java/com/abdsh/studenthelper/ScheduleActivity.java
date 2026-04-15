package com.abdsh.studenthelper;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ScheduleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar myToolbar = findViewById(R.id.sched_toolbar);
        setSupportActionBar(myToolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sched, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView TMon=findViewById(R.id.Tday1);
        TextView TTue=findViewById(R.id.Tday2);
        TextView TWed=findViewById(R.id.Tday3);
        TextView TThu=findViewById(R.id.Tday4);
        TextView TFri=findViewById(R.id.Tday5);
        String mond=getDate("Monday");
        String tues=getDate("Tuesday");
        String wedn=getDate("Wednesday");
        String thurs=getDate("Thursday");
        String frid=getDate("Friday");
        TMon.setText(mond);
        TTue.setText(tues);
        TWed.setText(wedn);
        TThu.setText(thurs);
        TFri.setText(frid);
    }
    public String getDate(String date){
        String time="";
        try {
            SQLiteOpenHelper schedSQLiteOpenHelper = new NotesSQLite(this);
            SQLiteDatabase db = schedSQLiteOpenHelper.getReadableDatabase();
            Cursor cursor = db.query("SCHED",
                    new String[]{"TIME"},
                    "DAY=?",
                    new String[]{date},
                    null,null ,null);
            if (cursor.moveToFirst()) {
                time=cursor.getString(0);}

        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return time;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.sched_update){
            Intent intent=new Intent(ScheduleActivity.this,SchedAddActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
