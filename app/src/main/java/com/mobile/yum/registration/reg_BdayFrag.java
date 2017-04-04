package com.mobile.yum.registration;

/**
 * Created by Sean on 4/3/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mobile.yum.R;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link reg_BdayFrag . OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link reg_BdayFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reg_BdayFrag extends Fragment {

    reg_Activity _activity;

    private String email;

    private int b_year;
    private int b_month;
    private int b_day;

    static final int DATE_DIALOG_ID = 100;

    //  private OnFragmentInteractionListener mListener;

    public reg_BdayFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reg_BdayFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static reg_BdayFrag newInstance(String param1, String param2) {
        reg_BdayFrag fragment = new reg_BdayFrag();
/*        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
*/        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
/*        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
*/  }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.reg_email_bday, container, false);

        final Button continueButton = (Button) v.findViewById(R.id.continue_button);
        final EditText editText = (EditText) v.findViewById(R.id.editText);

        editText.requestFocus();

        final DatePicker date_picker = (DatePicker) v.findViewById(R.id.date_picker);

        final Calendar calendar = Calendar.getInstance();

        b_year = calendar.get(Calendar.YEAR);
        b_month = calendar.get(Calendar.MONTH);
        b_day = calendar.get(Calendar.DAY_OF_MONTH);

        // set current date into Date Picker
        date_picker.init(b_year, b_month, b_day, null);

        //editText2.setError("Passwords are either not valid or matching");
        date_picker.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //modify to accomodate different keyboards????
                if(keyCode == 66){
                    continueButton.performClick();
                    return true;
                }
                return false;
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View arg0, boolean arg1){
                email = editText.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


                if (email.matches(emailPattern)){
                    editText.setError(null);
                }
                else{
                    editText.setError("Email is invalid.");
                }
            }
        });

        continueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                b_year = date_picker.getYear();
                b_month = date_picker.getMonth();
                b_day = date_picker.getDayOfMonth();

                if(editText.getError() == null) {
                    _activity._reg_contact.setEmail(email);
                    _activity._reg_contact.setBirthday(b_year, b_month, b_day);

                    //CREATE CONTACT
                    _activity.switchFrag("reg_Cuisine");

                }
            }
        });
        return v;
    }

    public void setActivity(reg_Activity activity) {
        _activity = activity;
    }


/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    /*
    public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
    }
    */
}
