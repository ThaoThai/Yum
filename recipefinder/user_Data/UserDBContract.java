package thaothai.example.com.recipefinder.user_Data;

import android.provider.BaseColumns;

public final class UserDBContract {
    private UserDBContract() {}

    public static class UserDBEntry implements BaseColumns {
        public static final String TABLE_NAME = "UserProfile";
        public static final String COLUMN_NAME_FIRST = "first";
        public static final String COLUMN_NAME_LAST = "last";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_B_YEAR = "year";
        public static final String COLUMN_NAME_B_MONTH = "month";
        public static final String COLUMN_NAME_B_DAY = "day";
        public static final String COLUMN_NAME_DIET = "diet";
    }



}