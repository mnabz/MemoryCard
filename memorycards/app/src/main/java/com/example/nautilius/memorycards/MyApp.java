package com.example.nautilius.memorycards;

import android.app.Application;
import android.content.Intent;

/**
 * Created by nautilius on 04/01/2017.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, ServiceRappelRevision.class));
    }
}
