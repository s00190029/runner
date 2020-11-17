package edu.rdonoghue.runner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CountUpTimer timer;
    TextView counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = findViewById(R.id.tvCounter);
        timer = new CountUpTimer(30000) {
            @Override
            public void onTick(int second) {
                counter.setText(String.valueOf(second));
            }
        };
    }

    public void doStart(View view){
        timer.start();
        Toast.makeText(this, "Started counting", Toast.LENGTH_LONG).show();
    }

    public void doStop(View view){
        timer.cancel();
        Toast.makeText(this, "Stopped Run", Toast.LENGTH_SHORT).show();
    }

    public void doReset(View view){
        counter.setText("0");
        Toast.makeText(this, "Timer reset", Toast.LENGTH_SHORT).show();
    }

}