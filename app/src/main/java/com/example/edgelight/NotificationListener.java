package com.example.edgelight;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.PowerManager;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.Display;
import android.widget.Toast;
import com.example.edgelight.controller.AppSettings;
import com.example.edgelight.model.AppSetting;


public class NotificationListener extends NotificationListenerService {

    public static final String ID = "edgeLight";

    @Override
    public void onCreate() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(ID,"edgeLight", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(false);
            channel.setSound(null, null);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }else {
            Toast.makeText(this, "Android version not supported", Toast.LENGTH_LONG).show();
        }
    }

    public String createNotificationText(CharSequence title, CharSequence text, AppSetting setting){
        StringBuilder notificationText = new StringBuilder();


        int titlePos = 0;
        int textPos = 1;

        if(setting != null) {
            Log.d("NotificationListener", setting.toString());
            titlePos = setting.getTitlePos();
            textPos = setting.getTextPos();
        }

        if(titlePos < textPos){
            notificationText.append(titlePos != 2 ? title : "");
            notificationText.append(" ");
            notificationText.append(textPos != 2 ? text : "");
        } else {
            notificationText.append(textPos != 2 ? text : "");
            notificationText.append(" ");
            notificationText.append(titlePos != 2 ? title : "");
        }

        Log.d("NotificationListener", notificationText.toString());
        return notificationText.toString();
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        if(!sbn.getNotification().getChannelId().equals(ID)) {

            DisplayManager displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
            if(displayManager.getDisplay(0).getState() == Display.STATE_OFF){
                PackageManager pm = getPackageManager();

                String packageName = sbn.getPackageName();

                CharSequence title = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE);
                CharSequence text = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT);

                Notification.Builder notificationBuilder = new Notification.Builder(this, ID);
                notificationBuilder.setColor(sbn.getNotification().color);

                try {
                    Log.d("NotificationListener","PackageName " + packageName);

                    ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);

                    CharSequence appName = pm.getApplicationLabel(appInfo);
                    AppSetting setting = AppSettings.getAppSettingDao().getSetting(packageName);
                    Log.d("NotificationListener", setting != null ? setting.toString() : "No Settings found for " + packageName);

                    if((setting != null ? setting.getEnabled() : (appInfo.flags & (ApplicationInfo.FLAG_SYSTEM)) != 0)) {
                        return;
                    }


                    notificationBuilder.setContentTitle(appName);
                    notificationBuilder.setContentText(createNotificationText(title, text, setting));
                    notificationBuilder.setChannelId(ID);
                    notificationBuilder.setSmallIcon(sbn.getNotification().getSmallIcon());
                    notificationBuilder.setTimeoutAfter(1000);

                    NotificationManager notificationManager = getSystemService(NotificationManager.class);

                    PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
                    PowerManager.WakeLock lock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, "EdgeLightLock::wakeLock");

                    lock.acquire(1);

                    Log.d("NotificationListener", "Sending Notification for " + appName);
                    notificationManager.notify(0, notificationBuilder.build());

                    lock.release();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
