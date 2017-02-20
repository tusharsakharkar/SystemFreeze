package com.example.tusharking.systemfreeze;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class okk_unfreeze extends AppCompatActivity {
int time = 0;
    Context context = this; Snackbar snackbar = null;
    Button unfreeze_btn,bt;
    int flag = 0;
    Code_word w = new Code_word();
    String code = w.randomString(5);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        setContentView(R.layout.activity_okk_unfreeze);
        Intent intent = getIntent();
        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");
        ImageView img = (ImageView)findViewById(R.id.img_view_txt);
        img.setImageBitmap(bitmap);
       // final String pkg = intent.getStringExtra("okk_val");
       // TextView txt = (TextView)findViewById(R.id.txtxt_unfreezesfdfdsf);
       // String tc = txt.getText().toString();
        TextView imgtxt = (TextView)findViewById(R.id.image_text);
        imgtxt.setText("Unfreeze the Apps?");
       Button  bt = (Button)findViewById(R.id.generate_code);

        //  unfreeze_btn = (Button)findViewById(R.id._unfreeze_btn);
       // unfreeze_btn.setEnabled(false);
       // unfreeze_btn.setClickable(false);
        /*
        unfreeze_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.freeze_button_bubble);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                BounceInterpolator interpolator = new BounceInterpolator(0.3, 30);
                myAnim.setInterpolator(interpolator);
                unfreeze_btn.startAnimation(myAnim);
                Toast.makeText(getApplicationContext(),"unfrezzze",Toast.LENGTH_SHORT).show();

                Button  bt = (Button)findViewById(R.id.generate_code);

              //  bt.setEnabled(false);
               // unfreeze_btn.setEnabled(false);
                /*
                Intent intent = getIntent();
                final String[] pkg = intent.getStringArrayExtra("okk_val");
                String[] cmd = new String[pkg.length];
                for(int i = 0;i<pkg.length;i++)
                {
                    cmd[i] = "pm enable "+pkg[i];
                }
                RootCmd root = new RootCmd();
                try {
                    root.execute(cmd);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        });
*/


    }
    public void ok_up(View v)
    {
        Button  bt = (Button)findViewById(R.id.generate_code);

        final Animation myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.freeze_button_bubble);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        BounceInterpolator interpolator = new BounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        bt.startAnimation(myAnim);
        //Button bt = (Button)findViewById(R.id.generate_code);
        final RelativeLayout coordinatorLayout = (RelativeLayout)findViewById(R.id.layouttttt123);


        Toast.makeText(getApplicationContext(),"generate code",Toast.LENGTH_SHORT).show();

        snackbar = Snackbar
                .make(coordinatorLayout, code, Snackbar.LENGTH_INDEFINITE)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        snackbar.dismiss();
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.DKGRAY);
        //  TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        // textView.setTextColor(Color.YELLOW);
        snackbar.show();


        ////////////////////////////////////////////////////////////////////////////////////////////////

        LayoutInflater layoutInflater = LayoutInflater.from(okk_unfreeze.this);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(okk_unfreeze.this);
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.edittext_code);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                      //  resultText.setText("Hello, " + editText.getText());
                        String sss  = editText.getText().toString();


                        if(sss.equals(code)) {
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                            Toast.makeText(getApplicationContext(),"code matched!",Toast.LENGTH_SHORT).show();


                            source();
                            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                        }
                        else {
Toast.makeText(getApplicationContext(),"wrong code!",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

private void source()
{

    //  timee.setEnabled(false);
    // b1.setEnabled(true);
    final Calendar c = Calendar.getInstance();
    int  mHour = c.get(Calendar.HOUR_OF_DAY);
    int  mMinute = c.get(Calendar.MINUTE);
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
                    long     totalTimeCountInMilliseconds = 60 * time * 1000;
                    String key = String.valueOf(totalTimeCountInMilliseconds);
                  //  Button bt1 = (Button)findViewById(R.id._unfreeze_btn);
                   // bt1.setEnabled(true);
                    //   int   timeBlinkInMilliseconds = 30 * 1000;
                    //  Intent serviceIntent = new Intent(getApplicationContext(), BroadcastService.class);
                    Toast.makeText(getApplicationContext(),key,Toast.LENGTH_SHORT).show();
                    Intent serviceIntent = new Intent(getApplicationContext(), BroadcastService1.class);
                    serviceIntent.putExtra("key", key);
                    Intent intent = getIntent();
                    final String[] pkg = intent.getStringArrayExtra("okk_val");
                    String[] cmd1 = new String[pkg.length];
                    for(int i = 0;i<pkg.length;i++)
                    {
                        cmd1[i] = "pm disable "+pkg[i];
                    }

                    serviceIntent.putExtra("array",cmd1);
                    // serviceIntent.putExtra("array1",array1);

                    context.startService(serviceIntent);

                 //   flag = 1;
                    try {

                        Intent intent1 = getIntent();
                        final String[] pkgg = intent1.getStringArrayExtra("okk_val");
                        String[] cmd = new String[pkgg.length];
                        for(int i = 0;i<pkgg.length;i++)
                        {
                            cmd[i] = "pm enable "+pkgg[i];
                        }
                        RootCmd root = new RootCmd();
                        try {
                            root.execute(cmd);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"device is not rooted",Toast.LENGTH_SHORT).show();


                        }

                    }
                    catch (Exception e)
                    {
                         Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();

                    }
                    //  context.startService(serviceIntent);
                }
            }, mHour, mMinute, false);
    timePickerDialog.show();
    ////////////////////////////////////////////////////////////////////////////

}
        ////////////////////////////////////////////////////////////////////////////////////////////////







    private BroadcastReceiver br1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //	long millisUntilFinished = intent.getLongExtra("countdown", 0);
            //textViewShowTime.setText(String.valueOf(millisUntilFinished / 1000));
            //	Toast.makeText(getApplicationContext(),"on receive",Toast.LENGTH_SHORT).show();

            Button bt = (Button) findViewById(R.id.generate_code);
            if (intent.getExtras() != null) {
                long millisUntilFinished = intent.getLongExtra("countdown", 0);
                //textViewShowTime.setText(String.valueOf(millisUntilFinished / 1000));
                long seconds = millisUntilFinished / 1000;
                long sec = seconds;
                if(sec == 1)
                {
bt.setText("");
                }else
if(sec>1)
{

    long  hours = seconds / 3600;
    long minutes = (seconds % 3600) / 60;
    long seecec = seconds % 60;

    String timeString = String.format("%02d:%02d:%02d", hours, minutes, seecec);
    bt.setText(timeString);
                }
                if (sec == 0) {

                    //  bt = (Button)findViewById(R.id.generate_code);

                  //  unfreeze_btn = (Button)findViewById(R.id._unfreeze_btn);
                    bt.setEnabled(true);
                   // unfreeze_btn.setEnabled(true);

                }
                else
                    if(sec>0) {
                        bt = (Button)findViewById(R.id.generate_code);
                       // flag = 0;
                      //  unfreeze_btn = (Button)findViewById(R.id._unfreeze_btn);
                        bt.setEnabled(false);
                       // unfreeze_btn.setEnabled(true);


                    }else
                    {
                        bt = (Button)findViewById(R.id.generate_code);
                        // flag = 0;
                        //  unfreeze_btn = (Button)findViewById(R.id._unfreeze_btn);
                        bt.setEnabled(true);
                    }




            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(br1, new IntentFilter(BroadcastService1.COUNTDOWN_BR1));
        //Toast.makeText(getApplicationContext(),"resmume",Toast.LENGTH_SHORT).show();
        //Log.i(TAG, "Registered broacast receiver");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(br1);
        //  Toast.makeText(getApplicationContext(),"pause",Toast.LENGTH_SHORT).show();
        //Log.i(TAG, "Unregistered broacast receiver");
    }

    @Override
    public void onStop() {
        try {
            unregisterReceiver(br1);
            //   Toast.makeText(getApplicationContext(),"stop",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            // Receiver was probably already stopped in onPause()
        }
        super.onStop();
    }

    @Override
    public void onDestroy() {
        stopService(new Intent(this, BroadcastService1.class));
        // b1.setEnabled(true);
        //  timee.setEnabled(true);
        //Log.i(TAG, "Stopped service");
        super.onDestroy();
        //  Toast.makeText(getApplicationContext(),"destroy",Toast.LENGTH_SHORT).show();
    }

}
