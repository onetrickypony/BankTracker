package com.example.onetrickpony.banktracker.network;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.support.annotation.Nullable;
import ru.arturvasilov.sqlite.core.SQLite;

/**
 * Created by onetrickpony on 05.09.2017.
 */

public class SmsService extends IntentService {

    public SmsService() {
        super(SmsService.class.getName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        //SQLite.get().delete();


    }
}
