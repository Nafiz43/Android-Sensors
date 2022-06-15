package com.example.navbarapplication;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class accelerometer extends AppCompatActivity implements SensorEventListener {


    private TextView tv_x1,tv_y1,tv_z1;
    private Sensor mySensor1;
    private SensorManager SM;
    private ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);

        SM= (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor1=SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this,mySensor1,SensorManager.SENSOR_DELAY_NORMAL);


        tv_x1=findViewById(R.id.acc_x_tv);
        tv_y1=findViewById(R.id.acc_y_tv);
        tv_z1=findViewById(R.id.acc_z_tv);
        bg=findViewById(R.id.bg);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int x = (int) event.values[0];
        int y = (int) event.values[1];
        int z = (int) event.values[2];

        if(y==0)
        {
            bg.setBackgroundColor(Color.CYAN);
        }
        else
        {
            bg.setBackgroundColor(Color.rgb(255,255,255));
        }

        tv_x1.setText(String.valueOf("X is: "+x));
        tv_y1.setText(String.valueOf("Y is: "+y));
        tv_z1.setText(String.valueOf("Z is: "+z));

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
