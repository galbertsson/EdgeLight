package com.example.edgelight.util;

import android.os.Handler;

public class Debounce {

    private Handler handler = new Handler();
    private long cooldown;

    public Debounce(long cooldown) {
        this.cooldown = cooldown;
    }

    public void execute(Runnable executable) {
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(executable, cooldown);
    }

    public void cancle() {
        handler.removeCallbacksAndMessages(null);
    }
}
