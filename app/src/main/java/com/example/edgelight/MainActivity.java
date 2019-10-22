package com.example.edgelight;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.edgelight.controller.AppSettingDao;

import com.example.edgelight.controller.AppSettings;
import com.example.edgelight.model.AppSetting;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppSettings.initSingleton(this);

        /*
        AppSettingDao dao = AppSettings.getAppSettingDao();
        AppSetting appSetting = new AppSetting();
        appSetting.setEnabled(true);
        appSetting.setPackageName("com.google.android.apps.messaging");
        appSetting.setTextPos(0);
        appSetting.setTitlePos(-1);

        dao.setSetting(appSetting);*/
    }

    public void onClick(View view){
        startActivity(new Intent(this, Settings.class));
    }

}
