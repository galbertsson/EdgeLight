package com.example.edgelight;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import com.example.edgelight.controller.AppSettingDao;
import com.example.edgelight.controller.AppSettings;
import com.example.edgelight.model.AppSetting;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SettingsHelper {

    private AppSettingDao appSettingDao;
    private PackageManager packageManager;
    private List<ApplicationInfo> installedApplications;
    private List<ApplicationInfo> workingCopy;

    private boolean displaySystemApps = false;

    public SettingsHelper(final PackageManager packageManager) {
        this.appSettingDao = AppSettings.getAppSettingDao();
        this.installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        this.packageManager = packageManager;

        this.workingCopy = new ArrayList<>(installedApplications.stream()
                .filter(new Predicate<ApplicationInfo>() {
                    @Override
                    public boolean test(ApplicationInfo p) {
                        try {
                            return (packageManager.getApplicationInfo(p.packageName, 0).flags & (ApplicationInfo.FLAG_SYSTEM)) == 0;
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }
                return false;
            }
        }).collect(Collectors.<ApplicationInfo>toList()));
    }

    public void onBindSettingRowViewAtPosition(int position, SettingsViewHolder holder){
        ApplicationInfo applicationInfo = workingCopy.get(position);
        AppSetting appSetting = appSettingDao.getSetting(applicationInfo.packageName);

        holder.setTitle(appSetting != null ? appSetting.getPackageName() : applicationInfo.packageName);
        holder.setTitlePos(appSetting != null ? appSetting.getTitlePos() : 0);
        holder.setTextPos(appSetting != null ? appSetting.getTextPos() : 1);
        holder.setOnOff(appSetting != null ? appSetting.getEnabled() : true); //TODO: This is not a correct assumption, system apps are not on by default
    }

    public int getRowsCount(){
        return workingCopy.size();
    }

    public void update(String packageName, Boolean on, int titlePos, int textPos) {
        Log.d("EdgeLightning", "Going to save!");
        for (AppSetting setting : appSettingDao.getAll()){
            Log.d("EdgeLightning", setting.toString());
        }

        AppSetting appSetting = appSettingDao.getSetting(packageName);
        if (appSetting == null){
            appSetting = new AppSetting();
            appSetting.setPackageName(packageName);
        }

        appSetting.setTitlePos(titlePos);
        appSetting.setTextPos(textPos);
        appSetting.setEnabled(on);

        appSettingDao.setSetting(appSetting);
    }

    public boolean isDisplaySystemApps() {
        return displaySystemApps;
    }

    public void setDisplaySystemApps(boolean displaySystemApps) {
        this.displaySystemApps = displaySystemApps;
        if(displaySystemApps) {
            workingCopy.clear();
            workingCopy.addAll(installedApplications);
        }
        else {
            workingCopy.clear();
            workingCopy.addAll(installedApplications.stream()
                    .filter(new Predicate<ApplicationInfo>() {
                        @Override
                        public boolean test(ApplicationInfo p) {
                            try {
                                return (packageManager.getApplicationInfo(p.packageName, 0).flags & (ApplicationInfo.FLAG_SYSTEM)) == 0;
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                            return false;
                        }
                    }).collect(Collectors.<ApplicationInfo>toList()));
        }
    }

    public void textFilter(final String text) {
        Log.d("EdgeLightning", text);
        workingCopy.clear();
        workingCopy.addAll(installedApplications.stream()
                .filter(new Predicate<ApplicationInfo>() {
                    @Override
                    public boolean test(ApplicationInfo p) {
                        if (p.packageName.contains(text)) {
                            Log.d("EdgeLightning", p.packageName);
                            Log.d("EdgeLightning", p.packageName.contains(text)+"");
                        }

                        return p.packageName.contains(text);
                    }
                }).collect(Collectors.<ApplicationInfo>toList()));
    }
}
