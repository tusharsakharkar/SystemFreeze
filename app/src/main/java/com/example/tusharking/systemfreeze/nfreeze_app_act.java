package com.example.tusharking.systemfreeze;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class nfreeze_app_act extends AppCompatActivity {
    Unfreeze_app_adapter installedAppAdapter3 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

        setContentView(R.layout.activity_nfreeze_app_act);

        ListView list = (ListView)findViewById(R.id.unfreeze_app_list);
        List<AppList> installedApps1 = getInstalledApps();

        String[] items = new String[installedApps1.size()];
        int index = 0;
        for (AppList value : installedApps1) {
            items[index] = value.toString();
            index++;
        }
       /*
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                TextView txt = (TextView)view.findViewById(R.id.textView121212_unfreeze);
                String str = txt.getText().toString();
                ImageView imageView = (ImageView)view.findViewById(R.id.app_icon_unfreeze);

                imageView.buildDrawingCache();
                Bitmap bitmap = imageView.getDrawingCache();

                TextView apptxt = (TextView)view.findViewById(R.id.list_app_name1);





            }
        });
*/
        //String[] items={"1","2","3","4","5"};
        // LstViewAdapter adapter=new LstViewAdapter(this,R.layout.list_item_freeze,R.id.txt,items);
        // Bind data to the ListView
        // lstview.setAdapter(adapter);

        //AppAdapter installedAppAdapter3 = new AppAdapter(nfreeze_app_act.this, installedApps1);
         installedAppAdapter3 = new Unfreeze_app_adapter(nfreeze_app_act.this, installedApps1);
        list.setAdapter(installedAppAdapter3);

        Button btn_next = (Button)findViewById(R.id.butt_next);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (installedAppAdapter3 != null) {


                    List<AppList> mArrayProducts = installedAppAdapter3.getCheckedItems();
if(mArrayProducts.size() == 0) {
    Toast.makeText(getApplicationContext(),"please select at least one application!" , Toast.LENGTH_SHORT).show();

}
else {
    try {
        String[] items = new String[mArrayProducts.size()];
        int index = 0;
        for (AppList value : mArrayProducts) {

            String aa = value.toString();
            String bb = aa.substring(0, aa.indexOf("-"));
            if (bb.equalsIgnoreCase("com.example.tusharking.systemfreeze")) {
//
            } else {
                items[index] = bb;
                index++;
            }
        }

        // Log.d(MainActivity.class.getSimpleName(), "Selected Items: " + mArrayProducts.toString());
        Intent intent = new Intent(getApplicationContext(), okk_unfreeze.class);
        intent.putExtra("okk_val", items);
        startActivity(intent);
        for (int i = 0; i < items.length; i++)
            Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_SHORT).show();


    }catch (Exception e)
    {
        Toast.makeText(getApplicationContext(),e.getMessage().toString(), Toast.LENGTH_SHORT).show();

    }


                }}
                else if(installedAppAdapter3 == null) {
                    Toast.makeText(getApplicationContext(),"please select at least one application!" , Toast.LENGTH_SHORT).show();

                }
            }});

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
                if(app.equalsIgnoreCase("com.example.tusharking.systemfreeze")) {

                }
else {
                    String appName = app + "-" + name;

                    res.add(new AppList(appName, icon));
                }
            } else if ((isSystemPackage(p) == false)) {
                String app = p.packageName.toString();
                String name = p.applicationInfo.loadLabel(getPackageManager()).toString();

                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                if(app.equalsIgnoreCase("com.example.tusharking.systemfreeze")) {

                }
                else {
                    String appName = app + "-" + name;

                    res.add(new AppList(appName, icon));
                }
            }
        }
        return res;
    }
    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

}
