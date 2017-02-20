package com.example.tusharking.systemfreeze;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class root__check extends AppCompatActivity {

    TextView txt,ker,mod_no,os_ver,build_num;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        setContentView(R.layout.activity_root__check);
    txt = (TextView) findViewById(R.id.txt_root_check);
        ker = (TextView)findViewById(R.id.kernal_ver);
        mod_no = (TextView)findViewById(R.id.model_no);
        os_ver = (TextView)findViewById(R.id.os_ver);
        build_num = (TextView)findViewById(R.id.build_num);

      try{
                Root_Check chk = new Root_Check();
            Boolean res = chk.executeShellCommand();
            if (res == true) {

                txt.setText("Rooted");
            } else if (res == false) {
                txt.setText("Not Rooted");

            }
                //Toast.makeText(getApplicationContext(),"executed",Toast.LENGTH_SHORT).show();

ker.setText(System.getProperty("os.version"));
          mod_no.setText(Build.MODEL);
          os_ver.setText(Build.VERSION.RELEASE);
          build_num.setText(Build.FINGERPRINT);

        }catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
        }



    }

}
