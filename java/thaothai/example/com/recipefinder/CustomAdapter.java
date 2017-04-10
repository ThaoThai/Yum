package thaothai.example.com.recipefinder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bikram on 4/8/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    ArrayList<String> modelItems= new ArrayList();
    Context context;
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
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        cb.setText(modelItems.get(position));

        return convertView;
    }
}