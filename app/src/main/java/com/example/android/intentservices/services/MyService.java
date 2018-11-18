package com.example.android.intentservices.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class MyService extends IntentService {

    public static final String MY_SERVICE_MESSAGE = "myServiceMessage";
    public static final String MY_SERVICE_PAYLOAD = "myServicePayload";


    public MyService() {
        super("MyService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Uri uri = intent.getData();
        Log.i(TAG, "onHandleIntent: " + uri.toString());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Intent messageIntent = new Intent(MY_SERVICE_MESSAGE);
        messageIntent.putExtra(MY_SERVICE_PAYLOAD, "service is all done ");
        LocalBroadcastManager manager =
                LocalBroadcastManager.getInstance(getApplicationContext());
        manager.sendBroadcast(messageIntent);
    }

    public void onCreate(){
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
