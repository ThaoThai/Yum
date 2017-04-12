package thaothai.example.com.recipefinder;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import thaothai.example.com.recipefinder.user_Data.UserDBContract;

/**
 * Created by Sean on 4/11/2017.
 */

public class ProfileFrag extends Fragment {

    MainActivity _activity;
    int data_id;

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
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            data_id=bundle.getInt("id");

        }

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


    public void displayInformation(View view) {
        List names = new ArrayList<String>();

        if (data_id==-1) {
            names.add("first");
            names.add("last");
            names.add("email");
            names.add("year");
            names.add("month");
            names.add("day");
            names.add("diet");
        }
        else {
            String WHERE = "SELECT * from " + UserDBContract.UserDBEntry.TABLE_NAME + " where " +
                    UserDBContract.UserDBEntry._ID + "= " + data_id;


            // Perform the query
            Cursor cursor = _activity._db.rawQuery(WHERE, null);
            // Iterate through results and store them in a list
            while (cursor.moveToNext()) {
                String firstname, lastname, email, diet, year, month, day;
                firstname = cursor.getString(cursor.getColumnIndex(UserDBContract.UserDBEntry.COLUMN_NAME_FIRST));
                lastname = cursor.getString(cursor.getColumnIndex(UserDBContract.UserDBEntry.COLUMN_NAME_LAST));
                email = cursor.getString(cursor.getColumnIndex(UserDBContract.UserDBEntry.COLUMN_NAME_EMAIL));
                diet = cursor.getString(cursor.getColumnIndex(UserDBContract.UserDBEntry.COLUMN_NAME_DIET));
                year = cursor.getString(cursor.getColumnIndex(UserDBContract.UserDBEntry.COLUMN_NAME_B_YEAR));
                month = cursor.getString(cursor.getColumnIndex(UserDBContract.UserDBEntry.COLUMN_NAME_B_MONTH));
                day = cursor.getString(cursor.getColumnIndex(UserDBContract.UserDBEntry.COLUMN_NAME_B_DAY));
                String birthday = day+"/"+month+"/"+year;
                names.add(firstname);
                names.add(lastname);
                names.add(email);
                names.add(diet);
                names.add(birthday);

                break;
            }
            cursor.close();
        }


    }



    public void setActivity(MainActivity activity) {
        _activity = activity;
    }

}
