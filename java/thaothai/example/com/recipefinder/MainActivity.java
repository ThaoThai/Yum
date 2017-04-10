package thaothai.example.com.recipefinder;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.ContentValues;
import android.database.sqlite.*;

public class MainActivity extends FragmentActivity implements ReceipeListFragment.OnDataPass,RecipeViewFragment.OnDataPass1 {
    ReceipeListFragment _receipeListFragment;
    RecipeViewFragment _recipeViewFragment;
    CheckListFragment _checkListFragment;
    reg_welcomeFrag _reg_welcomeFrag;
    reg_DietFrag _regDietFrag;
    reg_BdayFrag _regBdayFrag;
    reg_CuisineFrag _regCuisineFrag;
    Contact _reg_contact;
    SQLiteDatabase _db;

    private static boolean fragmentsSupported = false
    int recipe_id;
    String title;
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
        if(_reg_contact == null){
            _reg_contact = new Contact();
        }
        if(_reg_welcomeFrag == null){
            _reg_welcomeFrag = new reg_welcomeFrag();
            _reg_welcomeFrag.setActivity(this);
        }

        if(_regDietFrag == null){
            _regDietFrag = new reg_DietFrag();
            _regDietFrag.setActivity(this);
        }

        if(_regBdayFrag == null){
            _regBdayFrag = new reg_BdayFrag();
            _regBdayFrag.setActivity(this);
        }
        if(_regCuisineFrag == null){
            _regCuisineFrag = new reg_CuisineFrag();
            _regCuisineFrag.setActivity(this);
        }
        // Initialize the database if it does not exist
        if (_db == null) {
            ContactDatabaseHelper dbHelper = new ContactDatabaseHelper(getApplicationContext());
            _db = dbHelper.getWritableDatabase();
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
        }
        else if(newFrag.equals("reg_Cuisine")){
            fm.beginTransaction().replace(R.id.initialactivity, _regCuisineFrag).commit();
            fm.beginTransaction().remove(_regBdayFrag).commit();
        }
        else if (newFrag.equals("reg_Bday")){
            fm.beginTransaction().replace(R.id.initialactivity, _regBdayFrag).commit();
            fm.beginTransaction().remove(_reg_welcomeFrag).commit();

        }
        else if (newFrag.equals("reg_Diet")){
            fm.beginTransaction().replace(R.id.initialactivity, _regDietFrag).commit();
            fm.beginTransaction().remove(_regCuisineFrag).commit();

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

    public void updateActiveContact(){

        ContentValues cv = new ContentValues();
        cv.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_FIRST, _reg_contact.contact_name_first);
        cv.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_LAST, _reg_contact.contact_name_last);
        cv.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_EMAIL, _reg_contact.email);
        cv.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_WEIGHT, _reg_contact.weight);
        cv.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_B_YEAR, _reg_contact.birthday[0]);
        cv.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_B_MONTH, _reg_contact.birthday[1]);
        cv.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_B_DAY, _reg_contact.birthday[2]);


        _db.update(ContactDBContract.ContactDBEntry.TABLE_NAME, cv, "_id="+_reg_contact.id, null);
    }
}
