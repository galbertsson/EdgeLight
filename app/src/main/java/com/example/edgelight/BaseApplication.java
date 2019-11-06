package com.example.edgelight;


import android.app.Application;
import android.content.Context;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;
import org.acra.annotation.AcraMailSender;

@AcraCore(buildConfigClass = BuildConfig.class)
@AcraMailSender(mailTo = "edgelight@protonmail.com")
public class BaseApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        ACRA.init(this);
    }
}
