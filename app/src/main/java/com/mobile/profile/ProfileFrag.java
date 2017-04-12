package com.mobile.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.app.Fragment;

/**
 * Created by Sean on 4/11/2017.
 */

public class ProfileFrag extends Fragment {

    MainActivity _activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.profile, container, false);

        final ImageButton edit = (ImageButton) v.findViewById(R.id.edit);
        final EditText email = (EditText) v.findViewById(R.id.email_input);
        final EditText bday = (EditText) v.findViewById(R.id.bday_input);
        final EditText diet = (EditText) v.findViewById(R.id.diet_input);
        final EditText user_edit = (EditText) v.findViewById(R.id.user_profile_edit);
        final TextView user = (TextView) v.findViewById(R.id.user_profile_name);


        email.setEnabled(false);
        bday.setEnabled(false);
        diet.setEnabled(false);

        edit.setTag("edit");

        edit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(edit.getTag().equals("edit")){
                    edit.setTag("save");
                    edit.setImageResource(R.drawable.ic_action_save);
                    email.setEnabled(true);
                    bday.setEnabled(true);
                    diet.setEnabled(true);

                    user_edit.setVisibility(View.VISIBLE);
                    user.setVisibility(View.GONE);
                }else{
                    edit.setTag("edit");
                    edit.setImageResource(R.drawable.ic_action_edit);
                    email.setEnabled(false);
                    bday.setEnabled(false);
                    diet.setEnabled(false);

                    user_edit.setVisibility(View.GONE);
                    user.setVisibility(View.VISIBLE);
                }
            }
        });

        return v;
    }



    public void setActivity(MainActivity activity) {
        _activity = activity;
    }

}
