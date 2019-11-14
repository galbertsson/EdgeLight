package com.example.edgelight.util;

import com.example.edgelight.model.AppSetting;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class NotificationUtils {
    public static String createNotificationText(CharSequence title, CharSequence text, AppSetting setting){
        StringBuilder notificationText = new StringBuilder();
        HashMap<Integer, String> mapping = new HashMap<>();

        int titlePos = 0;
        int textPos = 1;

        if(setting != null) {
            titlePos = setting.getTitlePos();
            textPos = setting.getTextPos();
        }

        if(titlePos != 2 && title != null) {
            mapping.put(titlePos, title.toString());
        }

        if(textPos != 2 && text != null) {
            if(textPos == titlePos && title != null){
                textPos++;
            }
            mapping.put(textPos, text.toString());
        }

        LinkedList<Integer> mappingKeys = new LinkedList<>(mapping.keySet());
        mappingKeys.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        for (int i = 0; i < mappingKeys.size(); i++) {
            if (mappingKeys.size() > 1 && i == 1){
                notificationText.append(" ");
            }
            int key = mappingKeys.get(i);
            notificationText.append(mapping.get(key));
        }
        return notificationText.toString();
    }

    public static String parseHeader(AppSetting setting, CharSequence appName, CharSequence title, CharSequence text) {
        if (setting == null || setting.getHeader() == null) {
            return title != null ? title.toString() : "";
        }
        String header = setting.getHeader();

        int appNameIndex = header.indexOf("$name");
        if (appNameIndex > -1) {
            String tmp = header.substring(0, appNameIndex);
            if (appName != null) {
                tmp += appName;
            }
            tmp += header.substring(appNameIndex+5);
            header = tmp;
        }

        int titleIndex = header.indexOf("$title");
        if (titleIndex > -1) {
            String tmp = header.substring(0, titleIndex);
            if (title != null) {
                tmp += title;
            }
            tmp += header.substring(titleIndex+6);
            header = tmp;
        }

        int textIndex = header.indexOf("$text");
        if (textIndex > -1) {
            String tmp = header.substring(0, textIndex);
            if (text != null) {
                tmp += text;
            }
            tmp += header.substring(textIndex+5);
            header = tmp;
        }

        return header;
    }
}
