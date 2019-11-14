package com.example.edgelight.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "settings")
public class AppSetting {

    @PrimaryKey
    @NonNull
    String packageName;

    @ColumnInfo(name = "enabled")
    private Boolean enabled;

    @ColumnInfo(name = "title_pos")
    private int titlePos;

    @ColumnInfo(name = "text_pos")
    private int textPos;

    @ColumnInfo(name = "header")
    @Nullable
    private String header;

    public AppSetting(){}

    public String getPackageName(){
        return packageName;
    }

    public void setPackageName(String packageName){
        this.packageName = packageName;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public int getTitlePos() {
        return titlePos;
    }

    public int getTextPos() {
        return textPos;
    }

    public String getHeader() {
        return header;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setTitlePos(int titlePos) {
        this.titlePos = titlePos;
    }

    public void setTextPos(int textPos) {
        this.textPos = textPos;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    @NonNull
    @Override
    public String toString() {
        return "{package: " + packageName + ", titlePos: " + titlePos + ", textPos: " + textPos + ", enabled: " + enabled + "}";
    }
}
