package com.example.tusharking.systemfreeze;

import android.content.Context;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AppAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<AppList> listStorage;
   // private DisplayMetrics metrics_;
private int mode = 1;
    Context context;
    public AppAdapter(Context context, List<AppList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        listStorage = customizedListView;
      //  this.metrics_ = metrics;
    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.installed_app_list, parent, false);

            listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.list_app_name1);
            listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.app_icon);
            listViewHolder.btn = (Button)convertView.findViewById(R.id.btnnnnn);
            listViewHolder.txtxtxtxt = (TextView)convertView.findViewById(R.id.textView121212);
            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
      PackageManager  pm = context.getPackageManager();
        String app_un = listStorage.get(position).toString();
        String app = app_un.substring(0,app_un.indexOf("-"));
        listViewHolder.txtxtxtxt.setText(app);
        int appstate = pm.getApplicationEnabledSetting(app);

        //Toast.makeText(getApplicationContext(),aaa,Toast.LENGTH_LONG).show();
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_DISABLED){//is enabled
            listViewHolder.btn.setText("Enable");

//listViewHolder.btn.setBackgroundResource(R.drawable.freeze_button_back);
        }
        else
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_ENABLED){
            listViewHolder.btn.setText("Disable");
         //   listViewHolder.btn.setBackgroundResource(R.drawable.freeze_button_back);


        }
        else
        if(appstate == PackageManager.COMPONENT_ENABLED_STATE_DEFAULT){
            listViewHolder.btn.setText("Disable");
           // listViewHolder.btn.setBackgroundResource(R.drawable.freeze_button_back);


        }
        String app121 = listStorage.get(position).toString();
        String app_name = app121.substring(app121.indexOf("-")+1,app121.length());


        listViewHolder.textInListView.setText(app_name);
        listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());
listViewHolder.btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String state = listViewHolder.btn.getText().toString();
        if(state.equalsIgnoreCase("Disable"))
        {
            try {
                RootCmd root = new RootCmd();
                String cmd = "pm disable " + listViewHolder.txtxtxtxt.getText().toString();
                String[] ss = {cmd};
                root.execute(ss);
                Toast.makeText(context,"disabled",Toast.LENGTH_SHORT).show();
              //  Toast.makeText(getApplicationContext(),txt.getText().toString(),Toast.LENGTH_SHORT).show();

                listViewHolder.btn.setText("Enable");
              //  listViewHolder.btn.setBackgroundResource(R.drawable.freeze_button_back);

            }
            catch(Exception e)
            {
                e.printStackTrace();
               // Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }
        }
        else
        if(state.equalsIgnoreCase("Enable"))
        {
            try {
                RootCmd root = new RootCmd();
                String cmd = "pm enable " + listViewHolder.txtxtxtxt.getText().toString();
                String[] ss = {cmd};
                root.execute(ss);
                listViewHolder.btn.setText("Disable");
               // listViewHolder.btn.setBackgroundResource(R.drawable.freeeze_button_rel);

                Toast.makeText(context,"Enabled",Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(),txt.getText().toString(),Toast.LENGTH_SHORT).show();

            }
            catch(Exception e)
            {
                e.printStackTrace();
               // Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();

            }

        }


    }
});



        return convertView;
    }

    static class ViewHolder{

        TextView textInListView,txtxtxtxt;
        ImageView imageInListView;
        Button btn;
    }
}
