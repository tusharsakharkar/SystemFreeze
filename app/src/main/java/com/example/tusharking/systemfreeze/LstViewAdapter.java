package com.example.tusharking.systemfreeze;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class LstViewAdapter extends ArrayAdapter<String> {
    int groupid;
    String[] item_list;
    ArrayList<String> desc;
    PackageManager pm;
    Context context;
    public LstViewAdapter(Context context, int vg, int id, String[] item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;

    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView textview;
        public Button button;

    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the list_item.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textview= (TextView) rowView.findViewById(R.id.txt);
            viewHolder.button= (Button) rowView.findViewById(R.id.bt);
            rowView.setTag(viewHolder);

        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();
         pm = context.getPackageManager();
String app = item_list[position];
        int appstate = pm.getApplicationEnabledSetting(app);

        //Toast.makeText(getApplicationContext(),aaa,Toast.LENGTH_LONG).show();
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_DISABLED){//is enabled
            holder.button.setText("disabled");


        }
        else
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_ENABLED){
            holder.button.setText("enabled");

        }
        else
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT){
            holder.button.setText("default");

        }
        holder.textview.setText(item_list[position]);
     //   holder.button.setText(item_list[position]);
        return rowView;
    }

}
