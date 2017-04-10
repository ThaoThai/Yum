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
public class reg_CuisineFrag extends Fragment {

    reg_Activity _activity;
    private ArrayList<String> cuisines = new ArrayList<>();
    private ArrayList<String> selected = new ArrayList<>();
    private HashMap<String, Boolean> hash = new HashMap<>();
    RecyclerView v_options;
    StaggeredAdapter optionsAdapter;


    public reg_CuisineFrag() {
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
    public static reg_CuisineFrag newInstance(String param1, String param2) {
        reg_CuisineFrag fragment = new reg_CuisineFrag();
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
        View v = inflater.inflate(R.layout.reg_cuisine, container, false);
        Button continueButton = (Button) v.findViewById(R.id.continue_button);

        cuisines.add("Italian");
        cuisines.add("Vietnamese");
        cuisines.add("Polynesian");
        cuisines.add("Ethiopian");
        cuisines.add("Japanese");
        hash.put("Italian", true);
        hash.put("Vietnamese", false);
        hash.put("Polynesian", false);
        hash.put("Ethiopian", true);
        hash.put("Japanese", false);

        v_options = (RecyclerView) v.findViewById(R.id.v_options);
        v_options.setHasFixedSize(true);
        StaggeredGridLayoutManager options_lm = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        options_lm.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        v_options.setLayoutManager(options_lm);

        optionsAdapter = new StaggeredAdapter(this._activity, cuisines, hash);
        v_options.setAdapter(optionsAdapter);

 /*       optionsAdapter.setOnItemClickListener(new StaggeredAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int pos) {
                if(view.isSelected()){
                    selected.set(pos, null);
                    optionsAdapter.imgUrl = R.drawable.ic_action_add;
                    view.setSelected(false);
                }else{
                    view.setSelected(true);
                    optionsAdapter.imgUrl = R.drawable.ic_action_tick;
                }
                optionsAdapter.notifyItemChanged(pos);


            }
        });

*/
        continueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                _activity._reg_contact.cuisines = optionsAdapter.getSelected();
                _activity._reg_contact.loadCMap(optionsAdapter.getHash());
                _activity.switchFrag("reg_Diet");

            }
        });
        return v;
    }


    public void setActivity(reg_Activity activity) {
        _activity = activity;
    }


}