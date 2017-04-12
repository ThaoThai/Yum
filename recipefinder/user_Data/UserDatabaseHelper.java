package thaothai.example.com.recipefinder.user_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class UserDatabaseHelper extends SQLiteOpenHelper {
    // Need versioning in case database schema changes
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "UserInfo.db";

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        // Create the database table
        String command = "CREATE TABLE " + UserDBContract.UserDBEntry.TABLE_NAME + " (";
        command += UserDBContract.UserDBEntry._ID + " INTEGER PRIMARY KEY,";
        command += UserDBContract.UserDBEntry.COLUMN_NAME_LAST + " TEXT,";
        command += UserDBContract.UserDBEntry.COLUMN_NAME_FIRST + " TEXT,";
        command += UserDBContract.UserDBEntry.COLUMN_NAME_EMAIL + " TEXT,";
        command += UserDBContract.UserDBEntry.COLUMN_NAME_B_YEAR + " INTEGER,";
        command += UserDBContract.UserDBEntry.COLUMN_NAME_B_MONTH + " INTEGER,";
        command += UserDBContract.UserDBEntry.COLUMN_NAME_B_DAY + " INTEGER,";
        command += UserDBContract.UserDBEntry.COLUMN_NAME_DIET + " TEXT";
        command += ");";

        db.execSQL(command);

        // Temporarily populate the database
        ContentValues values = new ContentValues();
        values.put(UserDBContract.UserDBEntry.COLUMN_NAME_LAST, "Do");
        values.put(UserDBContract.UserDBEntry.COLUMN_NAME_FIRST, "Jane");
        values.put(UserDBContract.UserDBEntry.COLUMN_NAME_EMAIL, "Bambi_Jane@gmail.com");
        values.put(UserDBContract.UserDBEntry.COLUMN_NAME_B_YEAR, 1989);
        values.put(UserDBContract.UserDBEntry.COLUMN_NAME_B_MONTH, 2);
        values.put(UserDBContract.UserDBEntry.COLUMN_NAME_B_DAY, 10);
        values.put(UserDBContract.UserDBEntry.COLUMN_NAME_DIET, "vegan");

        db.insert(UserDBContract.UserDBEntry.TABLE_NAME, null, values);


        Log.v("User Manager", "Populating database");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete the database and the recreate it
        String command = "DROP TABLE IF EXISTS " + UserDBContract.UserDBEntry.TABLE_NAME;
        db.execSQL(command);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
