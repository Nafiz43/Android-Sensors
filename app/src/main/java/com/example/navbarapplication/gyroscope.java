package com.example.navbarapplication;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class gyroscope extends AppCompatActivity implements SensorEventListener {

    private TextView tv_x1,tv_y1,tv_z1,arrow_tv;
    private Sensor mySensor1;
    private SensorManager SM;
    private ConstraintLayout bg;
    boolean saveGyroscopeDataStatus=false;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        SM= (SensorManager) getSystemService(SENSOR_SERVICE);

        mySensor1=SM.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        SM.registerListener(this,mySensor1,SensorManager.SENSOR_DELAY_NORMAL);


        tv_x1=findViewById(R.id.gyro_x_tv);
        tv_y1=findViewById(R.id.gyro_y_tv);
        tv_z1=findViewById(R.id.gyro_z_tv);
       // arrow_tv=findViewById(R.id.arrow_tv);
        bg=findViewById(R.id.bg2);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = (float) event.values[0];
        float y= (float) event.values[1];
        float z= (float) event.values[2];

        int x_i=(int)x*100;
        int y_i=(int)y*100;
        int z_i=(int)z*100;


        tv_x1.setText(String.valueOf("X is: "+x));
        tv_y1.setText(String.valueOf("Y is: "+y));
        tv_z1.setText(String.valueOf("Z is: "+z));

        if(x_i==0 && y_i==0 && z_i!=0)
        {
            FirebaseDatabase database;
            DatabaseReference myRef;
            String a=String.valueOf(x);
            String b=String.valueOf(y);
            String c=String.valueOf(z);

            SensorData s1=new SensorData(a,b,c);
            database = FirebaseDatabase.getInstance();
            myRef = FirebaseDatabase.getInstance().getReference("SensorData");

            String key=myRef.push().getKey();

            //myRef.setValue(s1);
            myRef.child(key).setValue(s1);
            Toast.makeText(getApplicationContext(),"Saved onto database",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(gyroscope.this,MainActivity.class);
            startActivity(intent);

        }




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
