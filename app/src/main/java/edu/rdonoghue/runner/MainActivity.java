package edu.rdonoghue.runner;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView time, steps;
    Button btnShowRun;
    CountUpTimer timer;

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
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_NORMAL);

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


    public void doStart(View view){
        timer.start();
        Toast.makeText(this, "Started counting", Toast.LENGTH_LONG).show();
    }

    public void doStop(View view){
        timer.cancel();
        Toast.makeText(this, "Stopped Run", Toast.LENGTH_SHORT).show();
    }

    public void doReset(View view){
        time.setText("0");
        Toast.makeText(this, "Timer reset", Toast.LENGTH_SHORT).show();
    }

    public void doShowRun(View view) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}