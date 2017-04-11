package thaothai.example.com.recipefinder;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static thaothai.example.com.recipefinder.R.id.text;

/**
 * Created by Bikram on 4/8/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    ArrayList<String> modelItems= new ArrayList();
    Context context;
    MainActivity _activity;
    AdapterView.OnItemClickListener onItemClickListener;
    public CustomAdapter(Context context, ArrayList<String> resource) {
        super(context,R.layout.row,resource);
        // TODO Auto-generated constructor stub
        this.context = context;
        this.modelItems=resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.row, parent, false);
        TextView textview = (TextView) convertView.findViewById(R.id.textView);
        //CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        textview.setText(modelItems.get(position));
        return convertView;
    }
}