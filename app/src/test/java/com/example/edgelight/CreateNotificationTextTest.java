package com.example.edgelight;

import com.example.edgelight.model.AppSetting;
import com.example.edgelight.util.NotificationUtils;

import org.junit.Test;
import static org.junit.Assert.*;

public class CreateNotificationTextTest {
    @Test
    public void simple() {
        CharSequence title = "title";
        CharSequence text = "text";
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(0);
        appSetting.setTextPos(1);
        appSetting.setPackageName("com.example");

        String expected = title + " " + text;

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void disableTitle() {
        CharSequence title = "title";
        CharSequence text = "text";
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(2);
        appSetting.setTextPos(0);
        appSetting.setPackageName("com.example");

        String expected = text.toString();

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void disableText() {
        CharSequence title = "title";
        CharSequence text = "text";
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(0);
        appSetting.setTextPos(2);
        appSetting.setPackageName("com.example");

        String expected = title.toString();

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void bothDisabled() {
        CharSequence title = "title";
        CharSequence text = "text";
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(2);
        appSetting.setTextPos(2);
        appSetting.setPackageName("com.example");

        String expected = "";

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void titleNull() {
        CharSequence title = null;
        CharSequence text = "text";
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(0);
        appSetting.setTextPos(1);
        appSetting.setPackageName("com.example");

        String expected = text.toString();

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void textNull() {
        CharSequence title = "title";
        CharSequence text = null;
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(0);
        appSetting.setTextPos(1);
        appSetting.setPackageName("com.example");

        String expected = title.toString();

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void bothNull() {
        CharSequence title = null;
        CharSequence text = null;
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(0);
        appSetting.setTextPos(1);
        appSetting.setPackageName("com.example");

        String expected = "";

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void bothfirst() {
        CharSequence title = "title";
        CharSequence text = "text";
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(0);
        appSetting.setTextPos(0);
        appSetting.setPackageName("com.example");

        String expected = title + " " + text;

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }

    @Test
    public void bothLast() {
        CharSequence title = "title";
        CharSequence text = "text";
        AppSetting appSetting = new AppSetting();
        appSetting.setTitlePos(1);
        appSetting.setTextPos(1);
        appSetting.setPackageName("com.example");

        String expected = title + " " + text;

        String notification = NotificationUtils.createNotificationText(title, text, appSetting);

        assertEquals(expected, notification);
    }
}
