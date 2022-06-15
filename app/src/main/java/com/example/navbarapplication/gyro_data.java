package com.example.navbarapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class gyro_data extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro_data);

        final ListView list;
        final ArrayAdapter <String> adapter;

        final List<String> slist;

        slist=new ArrayList<>();

        list=findViewById(R.id.list);

        adapter=new ArrayAdapter<String>(this,R.layout.sample_layout,R.id.t1,slist);



        DatabaseReference reference;
        reference= FirebaseDatabase.getInstance().getReference("SensorData");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                slist.clear();

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    SensorData s=dataSnapshot1.getValue(SensorData.class);
                   // s1=s1+"Name :"+student.getName().toString()+"\n"+"ID :"+student.getAge()+"\n";
                    slist.add("X axis :"+s.getX()+" Y axis "+s.getY()+" Z axis "+s.getZ());

                    //Toast.makeText(getApplicationContext(),"entering",Toast.LENGTH_LONG).show();

                }
               // textView.setText(s1);
                list.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
