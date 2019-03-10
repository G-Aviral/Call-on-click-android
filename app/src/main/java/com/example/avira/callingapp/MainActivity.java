package com.example.avira.callingapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button call;
    private static final int request_call = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call = (Button)findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makephonhecall();

            }
        });

    }

    public void makephonhecall(){
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},request_call);
        }
        else {
            String dial = "tel:9756209394";
            startActivity(new Intent(Intent.ACTION_CALL,Uri.parse(dial)));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == request_call){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                makephonhecall();
            }
            else
            {
                Toast.makeText(MainActivity.this,"PERMISSION DENIED",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
