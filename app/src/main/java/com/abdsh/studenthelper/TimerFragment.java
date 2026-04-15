package com.abdsh.studenthelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TimerFragment extends Fragment implements View.OnClickListener  {
    int ticks = 0;
    int def= ticks;
    boolean running = false;
    boolean wasRunning = false;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        runTimer(view);
        Button tbuttonStart = view.findViewById(R.id.TbuttonStart);
        tbuttonStart.setOnClickListener(this);
        Button tbuttonStop = view.findViewById(R.id.TbuttonStop);
        tbuttonStop.setOnClickListener(this);
        Button tbuttonReset = view.findViewById(R.id.TbuttonReset);
        tbuttonReset.setOnClickListener(this);
        Button tadd10 = view.findViewById(R.id.Tadd10);
        tadd10.setOnClickListener(this);
        Button tadd50 = view.findViewById(R.id.Tadd50);
        tadd50.setOnClickListener(this);
        Button tadd2 = view.findViewById(R.id.Tadd2);
        tadd2.setOnClickListener(this);
        Button tclear=view.findViewById(R.id.Tbuttonclear);
        tclear.setOnClickListener(this);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            ticks = savedInstanceState.getInt("ticks");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if (wasRunning)
            running = true;
    }


    @Override
    public void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }

    public void runTimer(View view) {
        final TextView textView = view.findViewById(R.id.textViewTimeRemaining);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int seconds = ticks % 60;
                int minutes = ticks / 60 % 60;
                int hours = ticks / 3600;
                String stringToDisplay = String.format("%d:%02d:%02d", hours, minutes, seconds);
                textView.setText(stringToDisplay);
                if (running && ticks>0) {
                    ticks--;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("ticks", ticks);
        outState.putBoolean("running", running);
        outState.putBoolean("wasRunning", wasRunning);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TbuttonStart:
                running = true;
                break;
            case R.id.TbuttonStop:
                running = false;
                break;
            case R.id.TbuttonReset:
                running = false;
                ticks = def;
                break;
            case R.id.Tadd10:
                ticks=ticks+600;
                def=ticks;
                break;
            case R.id.Tadd50:
                ticks=ticks+(600*5);
                def=ticks;
                break;
            case R.id.Tadd2:
                ticks=ticks+(600*12);
                def=ticks;
                break;
            case R.id.Tbuttonclear:
                ticks=0;
                def=ticks;
                break;
        }
    }
}
