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
}
