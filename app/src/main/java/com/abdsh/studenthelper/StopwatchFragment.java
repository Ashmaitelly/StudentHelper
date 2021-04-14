package com.abdsh.studenthelper;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment implements View.OnClickListener {
    int ticks = 0;
    boolean running = false;
    boolean wasRunning = false;

    public StopwatchFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        runTimer(view);
        Button buttonStart = view.findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(this);
        Button buttonStop = view.findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(this);
        Button buttonReset = view.findViewById(R.id.buttonReset);
        buttonReset.setOnClickListener(this);
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
        final TextView textView = view.findViewById(R.id.textViewTimePassed);

        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int seconds = ticks % 60;
                int minutes = ticks / 60 % 60;
                int hours = ticks / 3600;
                String stringToDisplay = String.format("%d:%02d:%02d", hours, minutes, seconds);
                textView.setText(stringToDisplay);
                if (running) {
                    ticks++;
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
            case R.id.buttonStart:
                running = true;
                break;
            case R.id.buttonStop:
                running = false;
                break;
            case R.id.buttonReset:
                running = false;
                ticks = 0;
                break;
        }
    }
}
