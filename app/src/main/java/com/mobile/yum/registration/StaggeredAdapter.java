package com.mobile.yum.registration;

/**
 * Created by Sean on 4/10/2017.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.yum.R;

import java.util.ArrayList;
import java.util.HashMap;


public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.ViewHolder> {

    private ArrayList<String> list_items;
    private final reg_Activity _activity;
    public int imgUrl;
    private HashMap<String, Boolean> map = new HashMap<String, Boolean>();


    public StaggeredAdapter(reg_Activity activity, ArrayList<String> list_items, HashMap<String, Boolean> item_states){
        this.list_items = list_items;
        this.imgUrl = R.drawable.ic_action_add;
        _activity = activity;
        this.map = item_states;
    }
    // Create new views (invoked by the layout manager)
    @Override
    public StaggeredAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chip, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new ViewHolder(v);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder,final int position) {
        String text = list_items.get(position);

        if(!(map.get(text))){
            imgUrl = R.drawable.ic_action_add;

        }else{
            imgUrl = R.drawable.ic_action_tick;
        }
        holder.action.setImageResource(imgUrl);
        holder.item.setText(text);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            String key = list_items.get(position);
            @Override
            public void onClick(View view){
                if(map.get(key)){
                    map.put(key, false);
                }else{
                    map.put(key,true);
                }
                notifyItemChanged(position);
            }
        });


    }




    // Provide a reference to the views for each data item
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView item;
        public ImageView action;

        public ViewHolder(View itemView) {
            super(itemView);
            item = (TextView) itemView.findViewById(R.id.list_item);
            action = (ImageView) itemView.findViewById(R.id.action);

            itemView.setOnClickListener(this);
        }

   /*     public void onClick(View v) {
            int pos = getAdapterPosition();
            if(selected.get(pos) == null){
                selected.add(pos, list_items.get(pos));
                action.setImageResource(R.drawable.ic_action_tick);

            }else{
                selected.set(pos, null);
                action.setImageResource(R.drawable.ic_action_add);
            }
            notifyItemChanged(pos);

        }
     */   @Override
        public void onClick(View v){

        }
    }

    public ArrayList<String> getSelected(){
        return list_items;
    }
    public HashMap<String,Boolean> getHash(){
        return map;
    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list_items.size();
    }
}
