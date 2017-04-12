package thaothai.example.com.recipefinder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends FragmentActivity implements ReceipeListFragment.OnDataPass,RecipeViewFragment.OnDataPass1 {
    ReceipeListFragment _receipeListFragment;
    RecipeViewFragment _recipeViewFragment;
    CheckListFragment _checkListFragment;
    ProfileFrag _profileFrag;
    SQLiteDatabase _db;
    int recipe_id;
    public static final String PREFS_NAME = "MyPrefsFile";
    String title;
    boolean delete = false;
    boolean getList = false;
    FragmentManager manager = getSupportFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_main);

        // Initialize the fragments if they do not exist
        if (_receipeListFragment == null) {
            _receipeListFragment = new ReceipeListFragment();
            _receipeListFragment.setActivity(this);
        }
        if (_recipeViewFragment == null) {
            _recipeViewFragment = new RecipeViewFragment();
            _recipeViewFragment.setActivity(this);
        }
        if (_checkListFragment == null) {
            _checkListFragment = new CheckListFragment();
            _checkListFragment.setActivity(this);
        }

        if(_profileFrag == null){
            _profileFrag = new ProfileFrag();
            _profileFrag.setActivity(this);
        }
        // Add the initial fragment
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.recipe_main, _receipeListFragment).commit();
    }

    public void switchFragment(String newFragment) {
        if (newFragment == "recipe view") {
            Bundle bundle = new Bundle();
            bundle.putInt("id",recipe_id);
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.recipe_main, _recipeViewFragment).commit();
        } else if (newFragment == "recipe list") {
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.recipe_main, _receipeListFragment).commit();
        }else if(newFragment=="check list"){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.recipe_main, _checkListFragment).commit();
        }else if(newFragment == "profile"){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.recipe_main, _profileFrag).commit();
        }

    }

    public void onDataPass(String data) {
        Bundle args = new Bundle();
        args.putString(RecipeViewFragment.DATA_RECEIVE, data);
        _recipeViewFragment.setArguments(args);
        switchFragment("recipe view");

    }
    public void onDataPass1(String data) {
        Bundle args = new Bundle();
        args.putString(CheckListFragment.DATA_RECEIVE1, data);
        _checkListFragment.setArguments(args);
        switchFragment("check list");

    }

    public void passData(String key, String data) {
        Bundle args = new Bundle();
        args.putString(key, data);
        _checkListFragment.setArguments(args);
    }


}
