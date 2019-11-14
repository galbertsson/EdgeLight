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
import com.example.edgelight.util.NotificationUtils;


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

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        if(sbn == null) {
            return;
        }

        if(sbn.getNotification().getChannelId() == null || !sbn.getNotification().getChannelId().equals(ID)) {

            DisplayManager displayManager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
            if(displayManager.getDisplay(0).getState() == Display.STATE_OFF){
                PackageManager pm = getPackageManager();

                String packageName = sbn.getPackageName();

                CharSequence title = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE);
                CharSequence text = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT);

                Notification.Builder notificationBuilder = new Notification.Builder(this, ID);
                notificationBuilder.setColor(sbn.getNotification().color);

                try {
                    ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);

                    CharSequence appName = pm.getApplicationLabel(appInfo);
                    AppSetting setting = AppSettings.getAppSettingDao().getSetting(packageName);

                    if((setting != null ? !setting.getEnabled() : (appInfo.flags & (ApplicationInfo.FLAG_SYSTEM)) != 0)) {
                        return;
                    }


                    notificationBuilder.setContentTitle(NotificationUtils.parseHeader(setting, appName, title, text));
                    notificationBuilder.setContentText(NotificationUtils.createNotificationText(title, text, setting));
                    notificationBuilder.setChannelId(ID);
                    notificationBuilder.setSmallIcon(sbn.getNotification().getSmallIcon());
                    notificationBuilder.setTimeoutAfter(1000);

                    NotificationManager notificationManager = getSystemService(NotificationManager.class);

                    PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
                    PowerManager.WakeLock lock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK|PowerManager.ACQUIRE_CAUSES_WAKEUP, "EdgeLightLock::wakeLock");

                    lock.acquire(1);

                    notificationManager.notify(0, notificationBuilder.build());

                    lock.release();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
