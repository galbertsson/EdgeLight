package com.example.edgelight;

import androidx.appcompat.app.AppCompatActivity;;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.edgelight.controller.AppSettings;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppSettings.initSingleton(this);
    }

    public void onClick(View view){
        startActivity(new Intent(this, Settings.class));
    }

}
