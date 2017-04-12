package com.mobile.profile;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    ProfileFrag _profileFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(_profileFrag == null){
            _profileFrag = new ProfileFrag();
            _profileFrag.setActivity(this);
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.recipe_main, _profileFrag).commit();
    }

    public void switchFragment(String newFragment){
        if(newFragment == "profile"){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.recipe_main, _profileFrag).commit();
        }
    }

}
