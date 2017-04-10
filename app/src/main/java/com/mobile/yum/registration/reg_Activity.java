package com.mobile.yum.registration;

/**
 * Created by Sean on 4/3/2017.
 */

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.mobile.yum.R;
import com.mobile.yum.user_Data.Contact;
import com.mobile.yum.user_Data.ContactDBContract;
import com.mobile.yum.user_Data.ContactDatabaseHelper;


/**
 * Created by Sean on 2/28/2017.
 */

public class reg_Activity extends AppCompatActivity{

    reg_welcomeFrag _reg_welcomeFrag;
    reg_DietFrag _regDietFrag;
    reg_BdayFrag _regBdayFrag;
    reg_CuisineFrag _regCuisineFrag;
    Contact _reg_contact;
    SQLiteDatabase _db;

    private static boolean fragmentsSupported = false;
    /*
        private static void checkFragmentsSupported() throws NoClassDefFoundError {
            fragmentsSupported = android.app.Fragment.class != null;
        }


        static{
            try{
                checkFragmentsSupported();
            } catch (NoClassDefFoundError e){
                fragmentsSupported = false;
            }
        }

        private static final int REQUEST_READ_CONTACTS = 0;
*/

    @Override
    public void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);

      //  Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
      /*  setSupportActionBar(tb);

        final ActionBar ab = getSupportActionBar();
        //ab.setHomeAsUpIndicator(R.drawable.ic_menu); // set a custom icon for the default home button
        ab.setDisplayShowHomeEnabled(true); // show or hide the default home button
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowCustomEnabled(true); // enable overriding the default toolbar layout
        ab.setDisplayShowTitleEnabled(false); // disable the default title element here (for centered title)
*/
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

        FragmentManager fm = getSupportFragmentManager();
       // fm.beginTransaction().add(R.id.initialactivity, _reg_welcomeFrag).commit();
        fm.beginTransaction().add(R.id.initialactivity, _regCuisineFrag).commit();
    }

    public void switchFrag(String newFrag){
        FragmentManager fm = getSupportFragmentManager();

        if(newFrag.equals("reg_Cuisine")){
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
