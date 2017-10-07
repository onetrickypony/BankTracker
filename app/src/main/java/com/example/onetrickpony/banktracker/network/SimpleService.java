package com.example.onetrickpony.banktracker.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by onetrickpony on 05.09.2017.
 */

public class SimpleService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // TODO : do you work here
        // This code runs in the UI thread
        return super.onStartCommand(intent, flags, startId);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
