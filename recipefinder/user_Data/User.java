package thaothai.example.com.recipefinder.user_Data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sean on 3/6/2017.
 */

public class User {
    public int icon;
    public Integer id;
    public String User_name_first;
    public String User_name_last;
    public String email;
    public int[] birthday;
    public String diet;

    /*
    create separate database tables for cuisines and restrictions,
    stored with the selected "chips" first and store only the number
    of "chips" selected. If either "list" is added or subtracted from,
    modify the "number selected" variable accordingly and adjust the array

    birthday can be left as three separate ints in the database, similar to fname/lname

     */

    public User(){
        super();
    }

    public void setFirst(String first){this.User_name_first = first;}
    public void setLast(String last) {this.User_name_last = last;}
    public void setEmail(String email){
        this.email = email;
    }

    public void setBirthday(int year, int month, int day){
        if (birthday == null){
            birthday = new int[3];
        }
        birthday[0] = year;
        birthday[1] = month;
        birthday[2] = day;
    }

    public void setRestrictions(String restrictions) {
        this.diet = restrictions;
    }

    public void setUser(Integer primary_key, String first, String last, String email){
        this.id = primary_key;
        this.User_name_first = first;
        this.User_name_last = last;
        this.email = email;
    }
}

