package com.example.navbarapplication;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.SensorManager;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.seismic.ShakeDetector;

public class shake extends AppCompatActivity implements ShakeDetector.Listener {

    private SensorManager SM;
    ShakeDetector shakeDetector;
    TextView shake_tv;
    Boolean f=false;
    ConstraintLayout bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake);
        shake_tv=findViewById(R.id.shake_tv);
        bg=findViewById(R.id.bg4);

        SM= (SensorManager) getSystemService(SENSOR_SERVICE);
        shakeDetector=new ShakeDetector(this);
        shakeDetector.start(SM);
    }

    @Override
    public void hearShake() {


            shake_tv.setText("Shake Detected!!!");
            bg.setBackgroundColor(Color.RED);
            Uri u = Uri.parse("tel:" + "01795950319");
            Intent i = new Intent(Intent.ACTION_DIAL, u);
            startActivity(i);



    }

    protected  void onResume()
    {
        super.onResume();
        shakeDetector.start(SM);

    }

    @Override
    protected  void onPause()
    {
        super.onPause();
        shakeDetector.stop();
    }
}
