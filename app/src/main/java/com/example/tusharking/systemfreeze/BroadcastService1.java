package com.example.tusharking.systemfreeze;

import android.app.Service;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;

public class BroadcastService1 extends Service {

    private final static String TAG = "BroadcastService1";

    public static final String COUNTDOWN_BR1 = "com.example.tusharking.systemfreeze.countdown_br1";
    Intent bi = new Intent(COUNTDOWN_BR1);

    CountDownTimer cdt = null;

    @Override
    ////public void onCreate() {
    //   super.onCreate();
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(TAG, "Starting timer...");
        String userID = intent.getStringExtra("key");
        final String[] array = intent.getStringArrayExtra("array");
     //   final String[] array1 = intent.getStringArrayExtra("array1");

        final long mil = Long.parseLong(userID);
        cdt = new CountDownTimer(mil, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000);
                //  Toast.makeText(getApplicationContext(),"on tick",Toast.LENGTH_SHORT).show();
                /// if(millisUntilFinished < 25000)
                //   {

                bi.putExtra("countdown", millisUntilFinished);
                sendBroadcast(bi);
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "Timer finished");
                RootCmd roo = new RootCmd();
                try {
                    roo.execute(array);
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
    }


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }
}