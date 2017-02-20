package com.example.tusharking.systemfreeze;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Unfreeze_app_adapter extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private List<AppList> listStorage;
    SparseBooleanArray mSparseBooleanArray;
    // private DisplayMetrics metrics_;
    private int mode = 1;
    Context context;
    public Unfreeze_app_adapter(Context context, List<AppList> customizedListView) {
        layoutInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        listStorage = customizedListView;
        mSparseBooleanArray = new SparseBooleanArray();
        //  this.metrics_ = metrics;
    }
    public List<AppList> getCheckedItems() {


        List<AppList> mTempArry = new ArrayList<AppList>();




        for(int i=0;i<listStorage.size();i++) {


            if(mSparseBooleanArray.get(i)) {


                mTempArry.add(listStorage.get(i));


            }


        }




        return mTempArry;


    }

    @Override
    public int getCount() {
        return listStorage.size();
    }

    @Override
    public Object getItem(int position) {


        // TODO Auto-generated method stub


        return listStorage.get(position);


    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder listViewHolder;
        if (convertView == null) {
            listViewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.un_freeze_single_list, parent, false);

            listViewHolder.textInListView = (TextView) convertView.findViewById(R.id.list_app_name1);
            listViewHolder.imageInListView = (ImageView) convertView.findViewById(R.id.app_icon_unfreeze);
            listViewHolder.txtxtxtxt = (TextView) convertView.findViewById(R.id.textView121212_unfreeze);
             listViewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.chkEnable);

            convertView.setTag(listViewHolder);
        } else {
            listViewHolder = (ViewHolder) convertView.getTag();
        }
        PackageManager pm = context.getPackageManager();
        String app_un = listStorage.get(position).toString();
        String app = app_un.substring(0, app_un.indexOf("-"));
        listViewHolder.txtxtxtxt.setText(app);

        //Toast.makeText(getApplicationContext(),aaa,Toast.LENGTH_LONG).show();
        String app121 = listStorage.get(position).toString();
        String app_name = app121.substring(app121.indexOf("-") + 1, app121.length());


        listViewHolder.textInListView.setText(app_name);
        listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());


        listViewHolder.mCheckBox.setTag(position);


        listViewHolder.mCheckBox.setChecked(mSparseBooleanArray.get(position));


        listViewHolder.mCheckBox.setOnCheckedChangeListener(mCheckedChangeListener);




        return convertView;
    }
    CompoundButton.OnCheckedChangeListener mCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {




        @Override


        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


            // TODO Auto-generated method stub


            mSparseBooleanArray.put((Integer) buttonView.getTag(), isChecked);


        }


    };


    static class ViewHolder{

        TextView textInListView,txtxtxtxt;
        ImageView imageInListView;
        CheckBox  mCheckBox;
    }
}
