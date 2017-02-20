package com.example.tusharking.systemfreeze;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class freeze_app extends AppCompatActivity {
    Context context;
    private DisplayMetrics metrics;
    private int mode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        setContentView(R.layout.activity_freeze_app);

        context=this;
        ListView lstview=(ListView)findViewById(R.id.listv);
        //metrics = new DisplayMetrics();
       // getWindowManager().getDefaultDisplay().getMetrics(metrics);

        //lstview = new ListView(this);
        //lstview.setFadingEdgeLength(0);

        lstview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //Toast.makeText(context, "An item of the ListView is clicked.", Toast.LENGTH_LONG).show();
            }
        });
        List<AppList> installedApps1 = getInstalledApps();

        String[] items = new String[installedApps1.size()];
        int index = 0;
        for (AppList value : installedApps1) {
            items[index] = value.toString();
            index++;
        }
        //String[] items={"1","2","3","4","5"};
       // LstViewAdapter adapter=new LstViewAdapter(this,R.layout.list_item_freeze,R.id.txt,items);
        // Bind data to the ListView
       // lstview.setAdapter(adapter);

        AppAdapter installedAppAdapter3 = new AppAdapter(freeze_app.this, installedApps1);
        lstview.setAdapter(installedAppAdapter3);









    }
    public void clickMe(View view){
        Button bt=(Button)view;
        TextView txt= (TextView)view.findViewById(R.id.textView121212);
        Toast.makeText(this, "Button "+txt.getText().toString(),Toast.LENGTH_LONG).show();
/*
        String state = bt.getText().toString();
        if(state.equalsIgnoreCase("Disable"))
        {
            try {
                RootCmd root = new RootCmd();
                String cmd = "pm disable " + txt.getText().toString();
                String[] ss = {"su",cmd};
                root.execute(ss);
                Toast.makeText(getApplicationContext(),"disabled",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),txt.getText().toString(),Toast.LENGTH_SHORT).show();

                bt.setText("Enable");
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }
        }
        else
        if(state.equalsIgnoreCase("Enable"))
        {
            try {
                RootCmd root = new RootCmd();
                String cmd = "pm enable " + txt.getText().toString();
                String[] ss = {"su",cmd};
                root.execute(ss);
                bt.setText("Disable");
                Toast.makeText(getApplicationContext(),"Enabled",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),txt.getText().toString(),Toast.LENGTH_SHORT).show();

            }
            catch(Exception e)
            {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }

        }
        */
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
                String appName = app+"-"+name;
                res.add(new AppList(appName, icon));
            } else if ((isSystemPackage(p) == false)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();

                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app+"-"+name;

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
                String appName = app+"-"+name;
                res.add(new AppList(appName, icon));
            } else if ((isSystemPackage(p) == true)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();

                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app+"-"+name;

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
                String appName = app+"-"+name;
                res.add(new AppList(appName, icon));
            }
            if ((isSystemPackage(p) == false)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();

                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String appName = app+"-"+name;

                res.add(new AppList(appName, icon));
            }
        }
        return res;
    }


    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

}
