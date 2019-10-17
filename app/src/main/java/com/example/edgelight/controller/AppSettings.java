package com.example.edgelight.controller;

import android.content.Context;

import androidx.room.Room;

public class AppSettings {
    private static AppSettingDao singleton;

    public static AppSettingDao getAppSettingDao(){
        return singleton;
    }

    public static void initSingleton(Context context){
        AppSettingDatabase appSettingDatabase = Room.databaseBuilder(context, AppSettingDatabase.class, "database-name")
                .allowMainThreadQueries() //TODO: Change!
                .build();
        singleton = appSettingDatabase.appSettingDao();
    }
}
