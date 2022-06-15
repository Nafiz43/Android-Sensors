package com.example.navbarapplication;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class proximity extends AppCompatActivity implements SensorEventListener {

    private TextView d_tv;
    private Sensor mySensor1;
    private SensorManager SM;
    private ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        SM= (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor1=SM.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        SM.registerListener(this,mySensor1,SensorManager.SENSOR_DELAY_NORMAL);


        d_tv=findViewById(R.id.d_tv);
        bg=findViewById(R.id.bg3);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int x = (int) event.values[0];

        if(x<5)
        {
            Vibrator v = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
            v.vibrate(400);
        }

        d_tv.setText(String.valueOf("D is: "+x));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    protected  void onResume()
    {
        super.onResume();
        SM.registerListener(this,mySensor1,SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected  void onPause()
    {
        super.onPause();

        SM.unregisterListener(this);
    }
}
