package com.example.edgelight.controller;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.example.edgelight.model.AppSetting;

@Database(entities = {AppSetting.class}, version = 1, exportSchema = false)
public abstract class AppSettingDatabase extends RoomDatabase {

    public abstract AppSettingDao appSettingDao();

}
