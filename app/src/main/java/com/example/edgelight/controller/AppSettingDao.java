package com.example.edgelight.controller;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.edgelight.model.AppSetting;

@Dao
public interface AppSettingDao {

    @Query("SELECT * FROM settings")
    public List<AppSetting> getAll();

    @Query("SELECT * FROM settings WHERE packageName = :packageName")
    public AppSetting getSetting(String packageName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void setSetting(AppSetting appSetting);

    @Update
    public void updateSetting(AppSetting appSetting);
}
