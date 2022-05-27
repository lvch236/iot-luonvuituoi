package com.example.iot_luonvuituoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SleepingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleeping);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference();

        databaseReference.child("data").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int intValue = snapshot.getValue(int.class);
                if (intValue == 2)
                {
                    Intent intent = new Intent(SleepingActivity.this, StandActivity.class);
                    startActivity(intent);
                }
                if (intValue == 3)
                {
                    Intent intent = new Intent(SleepingActivity.this, RunningActivity.class);
                    startActivity(intent);
                }
//                if (intValue == 1)
//                {
//                    Intent intent = new Intent(SleepingActivity.this, SleepingActivity.class);
//                    startActivity(intent);
//                }
                if (intValue == 0)
                {
                    Intent intent = new Intent(SleepingActivity.this, OtherActivity.class);
                    startActivity(intent);
                }

                if (intValue!=3 && intValue!=2 &&intValue!=1 &&intValue!=0 )
                    Toast.makeText(SleepingActivity.this, "data error", Toast.LENGTH_SHORT).show();
                if (intValue!=2)
                Toast.makeText(SleepingActivity.this, "data value: "+intValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}