package thaothai.example.com.recipefinder;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import thaothai.example.com.recipefinder.user_Data.User;
import thaothai.example.com.recipefinder.user_Data.UserDBContract;
import thaothai.example.com.recipefinder.user_Data.UserDatabaseHelper;

import java.util.Calendar;
import java.util.Date;

import static android.support.v7.appcompat.R.styleable.Spinner;

/**
 * Created by thaothai on 4/11/17.
 */

public class RegisterActivity extends AppCompatActivity {
    private String email;
    private int b_year;
    private int b_month;
    private int b_day;
    EditText emailView;
    RegisterActivity _activity;
    DatePicker date_picker;
    Button continueButton;
    User _reg_User = new User();
    String diet ="";
    SQLiteDatabase _db;

    static final int DATE_DIALOG_ID = 100;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        if (_db == null) {
            UserDatabaseHelper dbHelper = new UserDatabaseHelper(getBaseContext());
            _db = dbHelper.getWritableDatabase();
        }

        android.widget.Spinner dropdown = (android.widget.Spinner) findViewById(R.id.diet_restriction);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.diet_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        final Spinner restriction = (Spinner) findViewById(R.id.diet_restriction);
        continueButton= (Button) findViewById(R.id.continue_button);
        emailView = (EditText) findViewById(R.id.email);

        setBirthday();

        emailView.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View arg0, boolean arg1){
                email = emailView.getText().toString().trim();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern)){
                    emailView.setError(null);
                }
                else{
                    emailView.setError("Email is invalid.");
                }
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                updateActiveUser();
                Toast.makeText(getApplicationContext(),diet,Toast.LENGTH_SHORT);
                Intent myIntent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });



    }

    public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            diet = (String) parent.getItemAtPosition(pos);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            diet = "";
        }
    }

    public void updateActiveUser(){
        EditText firstname = (EditText) findViewById(R.id.firstname);
        String f_name = firstname.getText().toString();
        EditText lastname = (EditText) findViewById(R.id.lastname);
        String l_name = lastname.getText().toString();
        b_year = date_picker.getYear();
        b_month = date_picker.getMonth();
        b_day = date_picker.getDayOfMonth();
        email =  emailView.getText().toString();
        if(emailView.getError() == null) {
            _reg_User.setEmail(email);
            _reg_User.setRestrictions(diet);
            _reg_User.setBirthday(b_year, b_month, b_day);

        }

        ContentValues cv = new ContentValues();
        cv.put(UserDBContract.UserDBEntry.COLUMN_NAME_FIRST, f_name);
        cv.put(UserDBContract.UserDBEntry.COLUMN_NAME_LAST, l_name);
        cv.put(UserDBContract.UserDBEntry.COLUMN_NAME_EMAIL, email);
        cv.put(UserDBContract.UserDBEntry.COLUMN_NAME_B_YEAR, b_year);
        cv.put(UserDBContract.UserDBEntry.COLUMN_NAME_B_MONTH, b_month);
        cv.put(UserDBContract.UserDBEntry.COLUMN_NAME_B_DAY, b_day);
        cv.put(UserDBContract.UserDBEntry.COLUMN_NAME_DIET, diet);


        _db.insert(UserDBContract.UserDBEntry.TABLE_NAME, null,cv);
    }

    public void setBirthday() {

        date_picker = (DatePicker) findViewById(R.id.datepicker);
        final Calendar calendar = Calendar.getInstance();
        b_year = calendar.get(Calendar.YEAR);
        b_month = calendar.get(Calendar.MONTH);
        b_day = calendar.get(Calendar.DAY_OF_MONTH);

        // set current date into datepicker
        date_picker.init(b_year, b_month, b_day, null);

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
    }
}
