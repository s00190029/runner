package edu.rdonoghue.runner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.PI;
import static java.lang.Math.round;
import static java.math.BigDecimal.*;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView time, steps;
    Button btnShowRun;
    CountUpTimer timer;
    private final int maxStep = 11;
    private final int minStep = 8;
    boolean highLimit = false;

    private SensorManager mSensorManager;
    private Sensor mSensor;
    int internalSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // var delcare
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        steps = findViewById(R.id.tvStepsNum);
        //internalSteps = Integer.valueOf(steps);
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);

        btnShowRun = findViewById(R.id.btnShowRun);
        btnShowRun.setEnabled(false);

        time = findViewById(R.id.tvTimeNum);
        timer = new CountUpTimer(30000) {
            @Override
            public void onTick(int second) {
                time.setText(String.valueOf(second));
            }
        };



    }


    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this,mSensor);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);
    }

    public void doStart(View view){
        timer.start();
        internalSteps = 0;
        btnShowRun.setEnabled(false);
        Toast.makeText(this, "Started counting", Toast.LENGTH_LONG).show();
    }

    public void doStop(View view){
        timer.cancel();
        btnShowRun.setEnabled(true);
        /*
        if (time.getText().toString() == "0" || steps.getText().toString() == "0") {
            btnShowRun.setEnabled(false);
        }
        else {
            btnShowRun.setEnabled(true);
        }
        */

        Toast.makeText(this, "Stopped Run", Toast.LENGTH_SHORT).show();
    }

    public void doReset(View view){
        doStop(view);
        time.setText("0");
        steps.setText("0");
        internalSteps = 0;
        btnShowRun.setEnabled(false);
        Toast.makeText(this, "Timer reset", Toast.LENGTH_SHORT).show();
    }

    public void doShowRun(View view) {
        doStop(view);
        btnShowRun.setEnabled(true);

        Intent showRun = new Intent(view.getContext(), showRun.class);
        showRun.putExtra("seconds", time.getText().toString());
        showRun.putExtra("stepsOut", steps.getText().toString());


        startActivity(showRun);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float X = Math.abs(event.values[0]);
        float Y = Math.abs(event.values[1]);
        float Z = Math.abs(event.values[2]);

        double mag = (Math.sqrt((X*X) +(Y*Y) + (Z*Z)));

        if(mag>maxStep && highLimit ==false){
            highLimit = true;
        }
        if (mag<minStep && highLimit == true)
        {
            internalSteps++;
            steps.setText(String.valueOf(internalSteps));
            highLimit = false;
        }

/* get a magnitude number using Pythagorus's Theorem
        //double mag = round(Math.sqrt((X*X) + (Y*Y) + (Z*Z)), 2);
        double test = round

        // for me! if msg > 11 and then drops below 9, we have a step
        // you need to do your own mag calculating
        if ((mag > HI_STEP) && (highLimit == false)) {
            highLimit = true;
        }
        if ((mag < LO_STEP) && (highLimit == true)) {
            // we have a step
            counter++;
            tvSteps.setText(String.valueOf(counter));
            highLimit = false;
        }
*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}