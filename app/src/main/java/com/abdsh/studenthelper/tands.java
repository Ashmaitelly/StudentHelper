package com.abdsh.studenthelper;

import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class tands extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tands);
        Toolbar tasToolbar = findViewById(R.id.tands_toolbar);
        setSupportActionBar(tasToolbar);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        HelloFragment helloFragment = new HelloFragment();
        fragmentTransaction.replace(R.id.content_frame, helloFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tands, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.tands_timer) {
            FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
            TimerFragment timerFragment = new TimerFragment();
            fragmentTransaction2.replace(R.id.content_frame, timerFragment);
            fragmentTransaction2.addToBackStack(null);
            fragmentTransaction2.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction2.commit();
            return true;
        } else if (id == R.id.tands_stopwatch) {
            FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            fragmentTransaction1.replace(R.id.content_frame, stopwatchFragment);
            fragmentTransaction1.addToBackStack(null);
            fragmentTransaction1.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction1.commit();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
