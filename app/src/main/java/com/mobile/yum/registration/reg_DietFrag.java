package com.mobile.yum.registration;

/**
 * Created by Sean on 4/3/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.yum.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link reg_DietFrag . OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link reg_DietFrag#newInstance} factory method to
 * create an instance of this fragment.
 */
public class reg_DietFrag extends Fragment {

    reg_Activity _activity;
    private ArrayList<String> restrictions = new ArrayList<>();
    private HashMap<String, Boolean> hash = new HashMap<>();
    private int weight;
    RecyclerView v_options;
    StaggeredAdapter optionsAdapter;

    public reg_DietFrag() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment reg_DietFrag.
     */
    // TODO: Rename and change types and number of parameters
    public static reg_DietFrag newInstance(String param1, String param2) {
        reg_DietFrag fragment = new reg_DietFrag();
/*        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 /*       if (getArguments() != null) {
            userName = getArguments().getString(ARG_PARAM1);
            email = getArguments().getString(ARG_PARAM2);
        }
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.diet_weight, container, false);

        Button doneButton = (Button) v.findViewById(R.id.done_button);
        final EditText editText2 = (EditText) v.findViewById(R.id.editText2);

        restrictions.add("blah");
        restrictions.add("heh");
        hash.put("blah",false);
        hash.put("heh",false);


        v_options = (RecyclerView) v.findViewById(R.id.v_options);
        v_options.setHasFixedSize(true);
        StaggeredGridLayoutManager options_lm = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        options_lm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        v_options.setLayoutManager(options_lm);

        optionsAdapter = new StaggeredAdapter(this._activity, restrictions, hash);
        v_options.setAdapter(optionsAdapter);
        doneButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                weight =  Integer.parseInt(editText2.getText().toString());

                _activity._reg_contact.restrictions = optionsAdapter.getSelected();
                _activity._reg_contact.loadRMap(optionsAdapter.getHash());
                _activity._reg_contact.setWeight(weight);
                //_activity.switchFrag("reg_Pass");

            }
        });
        return v;
    }


    public void setActivity(reg_Activity activity) {
        _activity = activity;
    }


}