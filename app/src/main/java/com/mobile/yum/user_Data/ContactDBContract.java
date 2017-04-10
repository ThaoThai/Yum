package com.mobile.yum.user_Data;

import android.provider.BaseColumns;

/**
 * Created by dave on 2/22/17.
 */

public final class ContactDBContract {
    private ContactDBContract() {
    }

    public static class ContactDBEntry implements BaseColumns {
        public static final String TABLE_NAME = "contacts";
        public static final String COLUMN_NAME_FIRST = "first";
        public static final String COLUMN_NAME_LAST = "last";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_B_YEAR = "year";
        public static final String COLUMN_NAME_B_MONTH = "month";
        public static final String COLUMN_NAME_B_DAY = "day";


    }

    /*Possible restrictions and selections will be loaded into this database
      Actual selections will start at initial index and go for selections.length
      ??? wouldn't mind a better design here
    */
    public static class UserSelections implements BaseColumns {
        public static final String TABLE_NAME = "selections";
        public static final String COLUMN_NAME_RESTRICT = "restrictions";
        public static final String COLUMN_NAME_CUISINE = "cuisines";
    }



}