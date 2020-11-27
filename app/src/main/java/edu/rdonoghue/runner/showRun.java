package edu.rdonoghue.runner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class showRun extends AppCompatActivity {
    float caloriesBurned, steps, metresRan, timeSec;
    LocalDateTime currentDate;
    TextView tvMetres, tvSeconds, tvDate, tvCals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_run);

        // linking
        steps = Float.valueOf(getIntent().getStringExtra("stepsOut"));
        timeSec = Float.valueOf(getIntent().getStringExtra("seconds"));
        currentDate = LocalDateTime.now();

        tvMetres = findViewById(R.id.tvMetresNum);
        tvSeconds = findViewById(R.id.tvSecondsNum);
        tvDate = findViewById(R.id.tvDate);
        tvCals = findViewById(R.id.tvCalBurnNum);

        metresRan = steps * 0.8f;
        caloriesBurned = steps * 0.04f;

        // bind & display
        tvSeconds.setText(String.valueOf(timeSec));
        tvMetres.setText(String.valueOf(metresRan));
        tvCals.setText(String.valueOf(caloriesBurned));
        tvDate.setText(String.valueOf(currentDate));
    }

    public void doClose(View view) {
        finish();
    }
}