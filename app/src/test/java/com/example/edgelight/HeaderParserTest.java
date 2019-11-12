package com.example.edgelight;

import com.example.edgelight.model.AppSetting;
import com.example.edgelight.util.NotificationUtils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeaderParserTest {
    @Test
    public void simple() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$name");
        String appName = "Gmail";
        String title = "Sender";
        String text = "text";


        String expected = appName;

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void hardCodedTitle() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("Banana");
        String appName = "Gmail";
        String title = "Sender";
        String text = "text";


        String expected = "Banana";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void dynamicTitle() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$title");

        String appName = "Gmail";
        String title = "Sender";
        String text = "text";


        String expected = title;

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void dynamicText() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$text");
        String appName = "Gmail";
        String title = "Sender";
        String text = "text";


        String expected = text;

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void dynamicNoTitle() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$title");
        String appName = "Gmail";
        String title = null;
        String text = "text";


        String expected = "";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void noSetting() {
        AppSetting appSetting = new AppSetting();
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "title";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void titleAndHardcoded() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("foo $title bar");
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "foo title bar";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void textAndHardcoded() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("foo $text bar");
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "foo text bar";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void titleAndText() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$title foo $text bar");
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "title foo text bar";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }


    @Test
    public void appNameAndHardcoded() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$name foo bar");
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "Gmail foo bar";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void appNameAndTitle() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$name foo bar $title");
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "Gmail foo bar title";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void appNameAndText() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$name foo bar $text");
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "Gmail foo bar text";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);

        assertEquals(expected, notification);
    }

    @Test
    public void appNameTextTitle() {
        AppSetting appSetting = new AppSetting();
        appSetting.setHeader("$name foo $text $title");
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "Gmail foo text title";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);
        assertEquals(expected, notification);
    }

    @Test
    public void nullSetting() {
        AppSetting appSetting = null;
        String appName = "Gmail";
        String title = "title";
        String text = "text";


        String expected = "title";

        String notification = NotificationUtils.parseHeader(appSetting, appName, title, text);
        assertEquals(expected, notification);
    }
}
