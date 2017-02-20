package com.example.tusharking.systemfreeze;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;




import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
int flag = 0,flag1 = 0;
    Button timee;
  //  private AdView mAdView;
    int index = 0, index1 = 0;   private AdView mAdView;
    private DrawerLayout drawerLayout;
private  DisplayMetrics metrics;
    // String appName = "";
    private Button buttonStartTime, buttonStopTime;
    Button b1;
    private EditText edtTimerValue;
    private TextView textViewShowTime; // will show the time
    private CountDownTimer countDownTimer; // built in android class
    // CountDownTimer
    private long totalTimeCountInMilliseconds; // total count down time in
    // milliseconds
    private long timeBlinkInMilliseconds; // start time of start blinking
    private boolean blink; // controls the blinking .. on and off
    int aaa = 0;
    static final int TIME_DIALOG_ID = 1111;
    private TextView output;
    public Button timer;


    Context context = this;
    int mHour;
    int mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // metrics = new DisplayMetrics();
       // getWindowManager().getDefaultDisplay().getMetrics(metrics);




        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6062836736470600~3683494770");
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mAdView.loadAd(adRequest);





/*

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");

        // Gets the ad view defined in layout/ad_fragment.xml with ad unit ID set in
        // values/strings.xml.
        mAdView = (AdView) findViewById(R.id.ad_view);

        // Create an ad request. Check your logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest);





*/










        //  NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //      .setAction("Action", null).show();
                try {
                    final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.freeze_button_bubble);

                    // Use bounce interpolator with amplitude 0.2 and frequency 20
                    BounceInterpolator interpolator = new BounceInterpolator(0.5, 30);
                    myAnim.setInterpolator(interpolator);

                    fab.startAnimation(myAnim);
drawer.openDrawer(Gravity.LEFT);
                }
                catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        LoginAnimation.startAnimation(this,this);
       // b1 = (Button)findViewById(R.id.button);
//        //b1.setBackgroundResource(R.drawable.freeze1);
        // b1.setEnabled(false);
        //  textViewShowTime = (TextView) findViewById(R.id.tvTimeCount);

        //  final ListView userInstalledApps = (ListView)findViewById(R.id.installed_app_list);
        List<AppList> installedApps1 = getInstalledApps1();
        timee = (Button) findViewById(R.id.time);

       // timee.setOnClickListener(this);
        //List<AppList> installedApps = getInstalledApps();
        List<AppList> installedApps3 = getInstalledApps3();
//

       // AppAdapter installedAppAdapter3 = new AppAdapter(MainActivity.this, installedApps1,);
        /*
        userInstalledApps.setAdapter(installedAppAdapter3);
        userInstalledApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent  = new Intent(getApplicationContext(),Main2Activity.class);
                //   String data=(String)arg0.getItemAtPosition(arg2);
                TextView txtxt = (TextView)view.findViewById(R.id.list_app_name);
                String pkname = txtxt.getText().toString();
                ///String xt = pkname.substring(0,pkname.indexOf("-"));
                //Toast.makeText(getApplicationContext(),xt,Toast.LENGTH_SHORT).show();
                intent.putExtra("pkg",pkname);
                startActivity(intent);
            }
        });
*/
        // Toast.makeText(getApplicationContext(),installedApps.toString(),Toast.LENGTH_SHORT).show();

        // String sta = b1.getText().toString();

        /*
        final String[] array = new String[installedApps.size()];
       int index = 0;
        for (Object value : installedApps) {
           array[index] = (String) value;
            Toast.makeText(getApplicationContext(),index,Toast.LENGTH_SHORT).show();


            index++;
        }
        Toast.makeText(getApplicationContext(),index,Toast.LENGTH_SHORT).show();

*/
        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        List<AppList> installedApps = getInstalledApps();
        // List<AppList> installedApps1 = getInstalledApps1();

        // Toast.makeText(getApplicationContext(),installedApps.toString(),Toast.LENGTH_SHORT).show();


        // index1 = index+1;


        ////////////////////////////////////////////////////////////////////////////////////////////////////////
/*
        PackageManager pm = getPackageManager();


        //Toast.makeText(getApplicationContext(),aaa,Toast.LENGTH_LONG).show();
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_DISABLED){//is enabled
           // b1.setText(" ");
            //tus.setText("disable");
            //Toast.makeText(getApplicationContext(),"enable",Toast.LENGTH_LONG).show();
          //  b1.setText("enable");

        }
        else
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_ENABLED){
            //  ds.setText(" ");
            //chk.setText("");
            // tus.setText("enable");
            //Toast.makeText(getApplicationContext(),"disable",Toast.LENGTH_LONG).show();
          //  b1.setText("disable");

        }else
        {
            //  chk.setText("");
         //   Toast.makeText(getApplicationContext(),"dont know",Toast.LENGTH_LONG).show();
            //chk.setText("dont know");
            //tus.setText("dont know");

        }
*/
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        final String[] array = new String[installedApps.size()];
        final String[] array1 = new String[installedApps1.size()];


        // List<AppList> installedApps = getInstalledApps();

        // Toast.makeText(getApplicationContext(),installedApps.toString(),Toast.LENGTH_SHORT).show();

        // int size = installedApps.size()+installedApps1.size();

/*

        for (AppList value : installedApps) {

            String bbb = value.toString();
            if (bbb.equalsIgnoreCase("com.example.tusharking.systemfreeze")) {
//
            } else {
                array[index] ="pm disable "+ bbb;
                index++;

            }
        }
//com.example.tusharking.systemfreeze
        // index1 = index+1;

        // List<AppList> installedApps1 = getInstalledApps1();

        //  int index1 = 0;

        for (AppList value1 : installedApps1) {

            String bbb = value1.toString();
            if (bbb.equalsIgnoreCase("com.android.chrome")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.android.email")) {
                array1[index1] = bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.play.games")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.videos")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.youtube")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.sec.android.app.camera")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.sec.android.app.samsungapps")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.sec.android.gallery3d")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.sec.chaton")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.android.vending")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.dropbox.android")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.apps.books")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.apps.docs")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.apps.magazines")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.apps.plus")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.gm")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.gms")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.google.android.music")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.sec.android.app.fm")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.sec.android.app.videoplayer")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            } else if (bbb.equalsIgnoreCase("com.sec.android.app.voicerecorder")) {
                array1[index1] ="pm disable "+  bbb;
                index1++;

            }else if (bbb.equalsIgnoreCase("com.android.musicfx")) {
                array1[index1] = "pm disable " + bbb;
                index1++;

            }else if (bbb.equalsIgnoreCase("com.google.android.googlequicksearchbox")) {
                array1[index1] = "pm disable " + bbb;
                index1++;

            }
            else if (bbb.equalsIgnoreCase("com.google.android.talk")) {
                array1[index1] = "pm disable " + bbb;
                index1++;

            }else if (bbb.equalsIgnoreCase("com.android.providers.downloads.ui")) {
                array1[index1] = "pm disable " + bbb;
                index1++;

            }

            }


        try {
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                      //  flag1 = 1;

                        final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.freeze_button_bubble);

                        // Use bounce interpolator with amplitude 0.2 and frequency 20
                        BounceInterpolator interpolator = new BounceInterpolator(0.5, 30);
                        myAnim.setInterpolator(interpolator);

                      //  b1.startAnimation(myAnim);
                        //  new LongOperation().execute("");

                        Button b2 = (Button)findViewById(R.id.button);
                        //b2.setEnabled(false);
                        //timee.setEnabled(false);


                        // Toast.makeText(getApplicationContext(),"array 1",Toast.LENGTH_SHORT).show();
                        // for (int i = 0;i<index;i++)
                        //   Toast.makeText(getApplicationContext(),array[i],Toast.LENGTH_SHORT).show();


                        // Toast.makeText(getApplicationContext(),"array 2",Toast.LENGTH_SHORT).show();
                        //  for (int i = 0;i<index1;i++)
                        //     Toast.makeText(getApplicationContext(),array1[i],Toast.LENGTH_SHORT).show();


                        // String sta = b1.getText().toString();
                        //   if(sta.equalsIgnoreCase("disable"))
                        // {
                        //;
                        //  Thread.sleep(5000);
                        try {
                            //PackageManager pm = con.getPackageManager();


                            //     pm.setApplicationEnabledSetting(pkgname, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                            // Toast.makeText(getApplicationContext(),"successfully disabled",Toast.LENGTH_LONG).show();


                         //   RootCmd root = new RootCmd();
                         //   root.execute(array);
                          //  root.execute(array1);
                            // String cmd1[] = {"su"};
                            //  root.RunAsRoot(cmd1);
                            //for (int i = 0; i < index; i++)

                            //for (String s : array) {
                            // String str = "pm disable " + s;
                            //  RootCmd root = new RootCmd();
                            //  String cmd[] = {str};
                            //  root.RunAsRoot(cmd);
                            //    Thread.sleep(2000);

                            //    Toast.makeText(getApplicationContext(), "done disable  = "+array[i], Toast.LENGTH_LONG).show();
                            // b1.setText("enable");
                            // }
                            // for (String s : array1) {
                            // String str = "pm disable " + s;
                            // String cmd[] = {str};
                            //root.RunAsRoot(cmd);
                            //  Thread.sleep(2000);

                            // Toast.makeText(getApplicationContext(), "done disable = "+array1[i], Toast.LENGTH_LONG).show();
                            // }

                            Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        //  }
                        // new LongOperation().execute("");


                        //   Toast.makeText(getApplicationContext(),"okk",Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//

                    }

                }


            });
        } catch (Exception ee)

        {
            Toast.makeText(getApplicationContext(), ee.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }

*/


    }
/*
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }


    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }


    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
    */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.app_freeze) {
            Intent intent = new Intent(getApplicationContext(),freeze_app.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.root_ckeck123) {
            Intent intent = new Intent(getApplicationContext(),root__check.class);
            startActivity(intent);


        } else if (id == R.id.unfreeze_app123) {
            Intent intent = new Intent(getApplicationContext(),nfreeze_app_act.class);
            startActivity(intent);


        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void okkk(View v) {
       // if (v.getId() == R.id.time) {
            final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.freeze_button_bubble);

            // Use bounce interpolator with amplitude 0.2 and frequency 20
            BounceInterpolator interpolator = new BounceInterpolator(0.5, 30);
            myAnim.setInterpolator(interpolator);

            timee.startAnimation(myAnim);
      //  b1.setEnabled(true);
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            //txtTime.setText(hourOfDay + ":" + minute);
                            // Toast.makeText(getApplicationContext(),hourOfDay + ":" + minute,Toast.LENGTH_SHORT).show();

                            Calendar c1 = Calendar.getInstance();
                            c1.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            c1.set(Calendar.MINUTE, minute);


                            Date x = c.getTime();
                           Date xy = c1.getTime();
                            long diff = xy.getTime() - x.getTime();
                            long time = diff / (60 * 1000);
                            if(time < 0)
                            {
                                time = -(time);
                            }

                            /*
                            long sub = c1.getTimeInMillis() - c.getTimeInMillis();
                            long diffInSec = TimeUnit.SECONDS.toSeconds(sub);

                           long time = ((diffInSec / (1000 * 60)) % 60);
                            if(time<0)
                            {
                                time = -(time);
                            }
*/
                           //   Toast.makeText(getApplicationContext(),"time"+String.valueOf(time),Toast.LENGTH_SHORT).show();
/////////////////////////enavble code................for service....................
                            List<AppList> installedApps = getInstalledApps();
                            List<AppList> installedApps1 = getInstalledApps1();

                            try {

                                String[] array = new String[installedApps.size()];

                            int index = 0;

                            int index1 = 0;
                            String[] array1 = new String[installedApps1.size()];




                                // Toast.makeText(getApplicationContext(),installedApps.toString(),Toast.LENGTH_SHORT).show();

                                // int size = installedApps.size()+installedApps1.size();
                                for (AppList value : installedApps) {

                                    String bbb = value.toString();
                                    if (bbb.equalsIgnoreCase("com.example.tusharking.systemfreeze")) {
//
                                    } else {
                                        array[index] ="pm enable "+ bbb;
                                        index++;

                                    }
                                }

                                // index1 = index+1;


                                for (AppList value1 : installedApps1) {

                                    String bbb = value1.toString();
                                    if (bbb.equalsIgnoreCase("com.android.chrome")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.android.email")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.play.games")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.videos")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.youtube")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.camera")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.samsungapps")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.gallery3d")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.chaton")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.android.vending")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.dropbox.android")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.books")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.docs")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.magazines")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.plus")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.gm")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.gms")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.music")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.fm")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.videoplayer")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.voicerecorder")) {
                                        array1[index1] ="pm enable "+ bbb;
                                        index1++;

                                    }else if (bbb.equalsIgnoreCase("com.android.musicfx")) {
                                        array1[index1] = "pm enable " + bbb;
                                        index1++;

                                    }else if (bbb.equalsIgnoreCase("com.google.android.googlequicksearchbox")) {
                                        array1[index1] = "pm enable " + bbb;
                                        index1++;

                                    }
                                    else if (bbb.equalsIgnoreCase("com.google.android.talk")) {
                                        array1[index1] = "pm enable " + bbb;
                                        index1++;

                                    }else if (bbb.equalsIgnoreCase("com.android.providers.downloads.ui")) {
                                        array1[index1] = "pm enable " + bbb;
                                        index1++;

                                    }


                                }


                                // Toast.makeText(getApplicationContext(),"array 1",Toast.LENGTH_SHORT).show();
                                // for (int i = 0;i<index;i++)
                                //   Toast.makeText(getApplicationContext(),array[i],Toast.LENGTH_SHORT).show();


                                // Toast.makeText(getApplicationContext(),"array 2",Toast.LENGTH_SHORT).show();
                                //  for (int i = 0;i<index1;i++)
                                //     Toast.makeText(getApplicationContext(),array1[i],Toast.LENGTH_SHORT).show();


                                //  String sta = b1.getText().toString();
                                //   if(sta.equalsIgnoreCase("disable"))


                                // }


                                // Toast.makeText(getApplicationContext(),"okk",Toast.LENGTH_SHORT).show();





                            /////////////////////code end enable.........................
//timee.setText(hourOfDay+" : "+minute);
                           // timee.setTextAppearance(getApplicationContext(),
                                   // R.style.normalText);
                            //setTimer();
                            totalTimeCountInMilliseconds = 60 * time * 1000;
                            String key = String.valueOf(totalTimeCountInMilliseconds);
                            timeBlinkInMilliseconds = 30 * 1000;
                            // Intent mIntent = new Intent(getApplicationContext(), BroadcastService.class);
                            // Bundle extras = mIntent.getExtras();
                            //extras.putString("key",key);

                            // startService(mIntent);
                            Intent serviceIntent = new Intent(getApplicationContext(), BroadcastService.class);
                            serviceIntent.putExtra("key", key);
                            serviceIntent.putExtra("array",array);
                            serviceIntent.putExtra("array1",array1);

                            context.startService(serviceIntent);
                           // timee.setEnabled(false);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                // Toast.makeText(getApplicationContext()," not okk",Toast.LENGTH_SHORT).show();


                            }
/////////////////////////////////////////////

                            index = 0;
                            index1 = 0;
                            try {

                                final String[] aarray = new String[installedApps.size()];
                                final String[] aarray1 = new String[installedApps1.size()];


                                // List<AppList> installedApps = getInstalledApps();

                                // Toast.makeText(getApplicationContext(),installedApps.toString(),Toast.LENGTH_SHORT).show();

                                // int size = installedApps.size()+installedApps1.size();


                                for (AppList value : installedApps) {

                                    String bbb = value.toString();
                                    if (bbb.equalsIgnoreCase("com.example.tusharking.systemfreeze")) {
//
                                    } else {
                                        aarray[index] = "pm disable " + bbb;
                                        index++;

                                    }
                                }
//com.example.tusharking.systemfreeze
                                // index1 = index+1;

                                // List<AppList> installedApps1 = getInstalledApps1();

                                //  int index1 = 0;

                                for (AppList value1 : installedApps1) {

                                    String bbb = value1.toString();
                                    if (bbb.equalsIgnoreCase("com.android.chrome")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.android.email")) {
                                        aarray1[index1] = bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.play.games")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.videos")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.youtube")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.camera")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.samsungapps")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.gallery3d")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.chaton")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.android.vending")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.dropbox.android")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.books")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.docs")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.magazines")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.apps.plus")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.gm")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.gms")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.music")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.fm")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.videoplayer")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.sec.android.app.voicerecorder")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.android.musicfx")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.googlequicksearchbox")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.google.android.talk")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    } else if (bbb.equalsIgnoreCase("com.android.providers.downloads.ui")) {
                                        aarray1[index1] = "pm disable " + bbb;
                                        index1++;

                                    }

                                }
                                try {
                                    RootCmd root = new RootCmd();
                                    root.execute(aarray);
                                    root.execute(aarray1);

                                    Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

                                }
                                catch (Exception e)
                                {
                                    Toast.makeText(getApplicationContext(),"device is not rooted", Toast.LENGTH_SHORT).show();

                                }

                            }
                            catch (Exception e)

                            {
                                e.printStackTrace();
                               // Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                            }
                            //////////////////////////////////




                            //b1.setEnabled(true);
                            flag = 1;
                            // Toast.makeText(getApplicationContext(),"service start",Toast.LENGTH_SHORT).show();

                            //  buttonStopTime.setVisibility(View.VISIBLE);
                            //   buttonStartTime.setVisibility(View.GONE);
                            // edtTimerValue.setVisibility(View.GONE);
                            //   edtTimerValue.setText("");

                            //  startTimer();
                            //timee.setEnabled(false);


                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
            ////////////////////////////////////////////////////////////////////////////



    }

    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app;
                res.add(new AppList(appName, icon));
            } else if ((isSystemPackage(p) == false)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();

                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app;

                res.add(new AppList(appName, icon));
            }
        }
        return res;
    }

    private List<AppList> getInstalledApps1() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == true)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app;
                res.add(new AppList(appName, icon));
            } else if ((isSystemPackage(p) == true)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();

                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app;

                res.add(new AppList(appName, icon));
            }
        }
        return res;
    }

    private List<AppList> getInstalledApps3() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == true)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app;
                res.add(new AppList(appName, icon));
            }
            if ((isSystemPackage(p) == false)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();

                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app;

                res.add(new AppList(appName, icon));
            }
        }
        return res;
    }


    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }


    private BroadcastReceiver br = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //	long millisUntilFinished = intent.getLongExtra("countdown", 0);
            //textViewShowTime.setText(String.valueOf(millisUntilFinished / 1000));
            //	Toast.makeText(getApplicationContext(),"on receive",Toast.LENGTH_SHORT).show();


            updateGUI(intent); // or whatever method used to update your GUI fields
        }
    };

    private BroadcastReceiver br1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //	long millisUntilFinished = intent.getLongExtra("countdown", 0);
            //textViewShowTime.setText(String.valueOf(millisUntilFinished / 1000));
            //	Toast.makeText(getApplicationContext(),"on receive",Toast.LENGTH_SHORT).show();

            Button bt = (Button) findViewById(R.id.button_sec_rec);
            if (intent.getExtras() != null) {
                long millisUntilFinished = intent.getLongExtra("countdown", 0);
                //textViewShowTime.setText(String.valueOf(millisUntilFinished / 1000));
                long seconds = millisUntilFinished / 1000;
                if(seconds == 1)
                {

bt.setText("");
                }else
                if(seconds >1) {
                    bt.setText(String.format("%02d", seconds / 3600) + ":" + String.format("%02d", (seconds % 3600) / 60)
                            + ":" + String.format("%02d", seconds % 60));
                }

            }
        }
    };
    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br, new IntentFilter(BroadcastService.COUNTDOWN_BR));
        registerReceiver(br1, new IntentFilter(BroadcastService1.COUNTDOWN_BR1));
        if (mAdView != null) {
            mAdView.resume();
        }

         }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
        unregisterReceiver(br);
        unregisterReceiver(br1);

    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br);
            unregisterReceiver(br1);

        } catch (Exception e) {
e.printStackTrace();
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, BroadcastService.class));
        stopService(new Intent(this, BroadcastService1.class));

        super.onDestroy();
        if (mAdView != null) {
            mAdView.destroy();
        }
    }

    private void updateGUI(Intent intent) {
        if (intent.getExtras() != null) {
            long millisUntilFinished = intent.getLongExtra("countdown", 0);
            //textViewShowTime.setText(String.valueOf(millisUntilFinished / 1000));
            long seconds = millisUntilFinished;
         //   if (seconds == 0) {

              //  timee.setText("finished");

                final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
              //  fab.setEnabled(true);


                /// }

                //////////////////////////////////////////////////enable code.................////////////////////////

           // }

                if(seconds>0) {
                   // final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                   /// fab.setEnabled(false);


                    timee.setEnabled(false);
                   // timee.setText("");
                }else
                if(seconds == 0){
                    timee.setEnabled(true);
                    // b1.setEnabled(true);
                    timee.setText("");
                }
else
                {
                    timee.setEnabled(true);

                }



//Toast.makeText(getApplicationContext(),"milisec : "+millisUntilFinished+" second : "+seconds,Toast.LENGTH_SHORT).show();
            ////////////////////////////////////////////////////////////////////////////////////////


            long  hours = seconds / 3600;
            long minutes = (seconds % 3600) / 60;
            long seecec = seconds % 60;

            String timeString = String.format("%02d:%02d:%02d", hours, minutes, seecec);
            timee.setText(timeString);
            // format the textview to show the easily readable format



        }
    }




}
