package com.example.navbarapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.TextView;

public class map extends AppCompatActivity implements TextWatcher {

    EditText src_et,dst_et;
    //TextView data_tv;
    WebView mapView;

    public String getMapContent(String src,String des)
    {
        String mapContent="https://directionsdebug.firebaseapp.com/embed.html?";
        String source="origin=",destination="&destination=";

        for(int i=0;i<src.length();i++)
        {
            if(src.charAt(i) != ' ')
            {
                source=source+src.charAt(i);
            }
            else
            {
                source=source+"%20";
            }
        }

        for(int i=0;i<des.length();i++)
        {
            if(des.charAt(i) != ' ')
            {
                destination=destination+des.charAt(i);
            }
            else
            {
                destination=destination+"%20";
            }
        }

        destination=destination+"&mode=driving";

        mapContent=mapContent+source+destination;

        return mapContent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        src_et=findViewById(R.id.src_et);
        dst_et=findViewById(R.id.dst_et);
        mapView=findViewById(R.id.MapView);

        mapView.setWebViewClient(new WebViewClient());

        String mapContent=getMapContent("Bup","Osmany Hall");
        mapView.loadUrl(mapContent);

        mapView.getSettings().setJavaScriptEnabled(true);
        mapView.getSettings().setLoadWithOverviewMode(true);
        mapView.getSettings().setUseWideViewPort(true);
        // data_tv=findViewById(R.id.data_tv);

        src_et.addTextChangedListener(this);
        dst_et.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        String mapContent=getMapContent(src_et.getText().toString(),dst_et.getText().toString());
        mapView.loadUrl(mapContent);

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        String mapContent=getMapContent(src_et.getText().toString(),dst_et.getText().toString());
        mapView.loadUrl(mapContent);
    }

    @Override
    public void afterTextChanged(Editable s) {
        String mapContent=getMapContent(src_et.getText().toString(),dst_et.getText().toString());
        mapView.loadUrl(mapContent);
    }
}
