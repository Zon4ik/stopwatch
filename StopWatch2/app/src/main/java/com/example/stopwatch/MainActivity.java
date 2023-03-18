package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView stopwatch;
    private Button stop;
    private Button start;

    private Chronometer mChronometer;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})


    private int seconds = 0;
    
    // Is the stopwatch running?

    private boolean running;


    private boolean wasRunning;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            // Get the previous state of the stopwatch
            // if the activity has been
            // destroyed and recreated.
            seconds
                    = savedInstanceState
                    .getInt("seconds");
            running
                    = savedInstanceState
                    .getBoolean("running");
            wasRunning
                    = savedInstanceState
                    .getBoolean("wasRunning");
        }

        runTimer();

    }
    @Override

    public void onSaveInstanceState(
            Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState
                .putInt("seconds", seconds);
        savedInstanceState
                .putBoolean("running", running);
        savedInstanceState
                .putBoolean("wasRunning", wasRunning);

    }
    protected void onPause()
    {
        super.onPause();
        wasRunning = running;
        running = false;
    }
    @Override

    protected void onResume()
    {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }
    //Start /Stop
    public void onClickStart(View view)
    {
        running = true;
    }
    public void onClickStop(View view)
    {
        running = false;
    }
//    public void onClickReset(View view)
//    {
//        running = false;
//        seconds = 0;
//    }

    private void runTimer() {
        final TextView timeView
                = (TextView)findViewById(
                R.id.stopwatch);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run()
            {
                int hours = seconds / 360000;              //int hours = seconds/3600;
                int minutes = (seconds  % 360000) /6000;  //int minutes = (seconds%3600)/60;
                int secs = (seconds%6000) /100;
                int msecs = seconds%100;

                @SuppressLint("DefaultLocale") String time = String.format("%01d:%02d:%02d:%02d",
                        hours, minutes, secs, msecs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this,1 );
            }
        });
    }


}
