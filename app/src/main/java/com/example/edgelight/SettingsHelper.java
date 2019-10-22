package com.example.edgelight;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import com.example.edgelight.controller.AppSettingDao;
import com.example.edgelight.controller.AppSettings;
import com.example.edgelight.model.AppSetting;

import java.util.List;

public class SettingsHelper {

    private AppSettingDao appSettingDao;
    //PackageManager packageManager;
    List<ApplicationInfo> installedApplications;

    public SettingsHelper(PackageManager packageManager) {
        this.appSettingDao = AppSettings.getAppSettingDao();
        //this.packageManager = packageManager;
        this.installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
    }

    //TODO: Fetch data
    public void onBindSettingRowViewAtPosition(int position, SettingsViewHolder holder){
        ApplicationInfo applicationInfo = installedApplications.get(position);
        Log.d("EdgeLightning", position+"");
        Log.d("EdgeLightning", applicationInfo.packageName);

        AppSetting appSetting = appSettingDao.getSetting(applicationInfo.packageName);

        holder.setTitle(appSetting != null ? appSetting.getPackageName() : "New app: " + applicationInfo.packageName);
        holder.setTitlePos(appSetting != null ? appSetting.getTitlePos() : 0);
        holder.setTextPos(appSetting != null ? appSetting.getTextPos() : 1);
        holder.setOnOff(appSetting != null ? appSetting.getEnabled() : true); //TODO: This is not a correct assumption, system apps are not on by default
    }

    public int getRowsCount(){
        Log.d("EdgeLightning", installedApplications.size()+"");
        return installedApplications.size();
    }

    public void update(String packageName, Boolean on, int titlePos, int textPos) {
        AppSetting appSetting = appSettingDao.getSetting(packageName);
        if (appSetting == null){
            appSetting = new AppSetting();
            appSetting.setPackageName(packageName);
        }

        appSetting.setTitlePos(titlePos);
        appSetting.setTextPos(textPos);
        appSetting.setEnabled(on);

        appSettingDao.updateSetting(appSetting);
    }
}
