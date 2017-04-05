package com.mobile.yum.user_Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by dave on 2/22/17.
 */

public class ContactDatabaseHelper extends SQLiteOpenHelper {
    // Need versioning in case database schema changes
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactManager.db";

    public ContactDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        // Create the database table
        String command = "CREATE TABLE " + ContactDBContract.ContactDBEntry.TABLE_NAME + " (";
        command += ContactDBContract.ContactDBEntry._ID + " INTEGER PRIMARY KEY,";
        command += ContactDBContract.ContactDBEntry.COLUMN_NAME_LAST + " TEXT,";
        command += ContactDBContract.ContactDBEntry.COLUMN_NAME_FIRST + " TEXT,";
        command += ContactDBContract.ContactDBEntry.COLUMN_NAME_EMAIL + " TEXT";
        command += ContactDBContract.ContactDBEntry.COLUMN_NAME_WEIGHT + " INTEGER";
        command += ContactDBContract.ContactDBEntry.COLUMN_NAME_B_YEAR + " INTEGER";
        command += ContactDBContract.ContactDBEntry.COLUMN_NAME_B_MONTH + " INTEGER";
        command += ContactDBContract.ContactDBEntry.COLUMN_NAME_B_DAY + " INTEGER";
        command += ")";
/*
        String command2 = "CREATE TABLE " + ContactDBContract.UserSelections.TABLE_NAME + " (";
        command2 += ContactDBContract.ContactDBEntry._ID + " INTEGER PRIMARY KEY,";
        command2 += ContactDBContract.UserSelections.COLUMN_NAME_CUISINE + " TEXT,";
        command2 += ContactDBContract.UserSelections.COLUMN_NAME_RESTRICT+ " TEXT,";
        command2 += ")";
*/
        db.execSQL(command);
      //  db.execSQL(command2);

        // Temporarily populate the database
        ContentValues values = new ContentValues();
        values.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_LAST, "Do");
        values.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_FIRST, "Jane");
        values.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_EMAIL, "Bambi_Jane@gmail.com");
        values.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_WEIGHT, 135);
        values.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_B_YEAR, 1989);
        values.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_B_MONTH, 2);
        values.put(ContactDBContract.ContactDBEntry.COLUMN_NAME_B_DAY, 10);

        db.insert(ContactDBContract.ContactDBEntry.TABLE_NAME, null, values);
/*
        ContentValues values1 = new ContentValues();
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_CUISINE, "Italian");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_CUISINE, "Vietnamese");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_CUISINE, "Polynesian");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_CUISINE, "Ethiopian");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_CUISINE, "Japanese");

        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_RESTRICT, "Celiacs");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_RESTRICT, "Paleo");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_RESTRICT, "See Food");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_RESTRICT, "Low Calorie");
        values1.put(ContactDBContract.UserSelections.COLUMN_NAME_RESTRICT, "Nut Allergy");

        db.insert(ContactDBContract.UserSelections.TABLE_NAME, null, values1);
*/

        Log.v("Contact Manager", "Populating database");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Delete the database and the recreate it
        String command = "DROP TABLE IF EXISTS " + ContactDBContract.ContactDBEntry.TABLE_NAME;
//        String command1 = "DROP TABLE IF EXISTS " + ContactDBContract.UserSelections.TABLE_NAME;

  //      db.execSQL(command1);
        db.execSQL(command);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
