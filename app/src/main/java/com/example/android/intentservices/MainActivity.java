package com.example.android.intentservices;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.intentservices.services.MyService;
import com.example.android.intentservices.utils.NetworkHelper;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private boolean hasNetwork;
    public static final String URL = "http://560057.youcanlearnit.net/services/json/itemsfeed.php";
    TextView output;


    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message =
                    intent.getStringExtra(MyService.MY_SERVICE_PAYLOAD);
            output.append(message + "\n");

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = findViewById(R.id.output);
        LocalBroadcastManager.getInstance(getApplicationContext())
                .registerReceiver(mBroadcastReceiver,
                        new IntentFilter(MyService.MY_SERVICE_MESSAGE));

        hasNetwork = NetworkHelper.hasNetworkAccess(this);
        output.append("Network is available " + hasNetwork);
    }
    protected void onDestroy(){
        super.onDestroy();

        LocalBroadcastManager.getInstance(getApplicationContext())
                .unregisterReceiver(mBroadcastReceiver);
    }

    public void runClickHandler(View view){

        Intent intent = new Intent(this, MyService.class);
        intent.setData(Uri.parse(URL));
        startService(intent);
        startService(intent);
        startService(intent);
    }

    public void clearClickHandler(View view){
        output.setText("");
    }
}
