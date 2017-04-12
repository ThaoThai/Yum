package thaothai.example.com.recipefinder.user_Data;

import java.util.ArrayList;
import java.util.HashMap;


public class User {
    public int icon;
    public Integer id;
    public String User_name_first;
    public String User_name_last;
    public String email;
    public int[] birthday;
    public String diet;

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

