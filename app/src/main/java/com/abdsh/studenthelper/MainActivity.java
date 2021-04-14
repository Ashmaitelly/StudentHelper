package com.abdsh.studenthelper;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
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
                switch (id){
                    case R.id.nav_notes:
                        intent=new Intent(MainActivity.this,NotesActivity.class);
                        break;
                    case R.id.nav_tands:
                        intent=new Intent(MainActivity.this,tands.class);
                        break;
                    case R.id.nav_sched:
                        intent=new Intent(MainActivity.this,ScheduleActivity.class);
                        break;
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
