package com.abdsh.studenthelper;

import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;
import androidx.fragment.app.FragmentTransaction;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                drawerLayout.closeDrawers();
                int id=menuItem.getItemId();
                Intent  intent=new Intent();
                if (id == R.id.nav_notes) {
                    intent = new Intent(MainActivity.this, NotesActivity.class);
                } else if (id == R.id.nav_tands) {
                    intent = new Intent(MainActivity.this, tands.class);
                } else if (id == R.id.nav_sched) {
                    intent = new Intent(MainActivity.this, ScheduleActivity.class);
                }
                startActivity(intent);
                return false;
                /* old implementation
                if(id==R.id.nav_notes){
                        Intent intent=new Intent(MainActivity.this,NotesActivity.class);
                        startActivity(intent);}
                else if(id==R.id.nav_tands){
                        Intent intent1=new Intent(MainActivity.this,tands.class);
                        startActivity(intent1);}
                else if(id==R.id.nav_sched){
                    Intent intent1=new Intent(MainActivity.this,ScheduleActivity.class);
                    startActivity(intent1);}*/



            }

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            greeting gf = new greeting();
            fragmentTransaction.replace(R.id.content_frame, gf);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();}

  }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
