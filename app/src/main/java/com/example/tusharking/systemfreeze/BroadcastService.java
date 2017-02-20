package com.example.tusharking.systemfreeze;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

public class BroadcastService extends Service {

    private final static String TAG = "BroadcastService";
private Context context = this;
    public static final String COUNTDOWN_BR = "com.example.tusharking.systemfreeze.countdown_br";
    Intent bi = new Intent(COUNTDOWN_BR);

    CountDownTimer cdt = null;
    WifiManager wifiManager = null;

/*
    public void OnCreate(){
        super.onCreate();
         wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);

        Log.w("TAG", "ScreenListenerService---OnCreate ");
    }
    */

    ////public void onCreate() {
     //   super.onCreate();
    @Override
    public void onTaskRemoved(Intent intent) {
        super.onTaskRemoved(intent);
       // Intent intent1=new Intent(this,this.getClass());
        //startService(intent1);
        Log.i(TAG, "on task removed");


    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "Starting timer...");
        String userID = intent.getStringExtra("key");
      final String[] array = intent.getStringArrayExtra("array");
        final String[] array1 = intent.getStringArrayExtra("array1");

        final long mil = Long.parseLong(userID);
        cdt = new CountDownTimer(mil, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
                //  Toast.makeText(getApplicationContext(),"on tick",Toast.LENGTH_SHORT).show();
                /// if(millisUntilFinished < 25000)
                //   {
                try {
                    wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
                    boolean wifiEnabled = wifiManager.isWifiEnabled();
                    if(wifiEnabled == true) {
                        wifiManager.setWifiEnabled(false);
                        Toast.makeText(getApplicationContext(),"wifi service stopped",Toast.LENGTH_SHORT).show();

                    }else
                    {
                        //
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    //Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }

                bi.putExtra("countdown", millisUntilFinished/1000);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");

                try {
                    RootCmd root = new RootCmd();

                    root.execute(array);
                    root.execute(array1);
                    wifiManager.setWifiEnabled(true);


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };

        cdt.start();
        // }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {

      ///  cdt.cancel();
//        super.onDestroy();
       // startService(new Intent(this, BroadcastService.class));
    }



    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}