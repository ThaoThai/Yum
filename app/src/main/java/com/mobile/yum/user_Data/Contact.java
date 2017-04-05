package com.mobile.yum.user_Data;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by Sean on 3/6/2017.
 */

public class Contact {
    public int icon;
    public Integer id;
    public String contact_name_first;
    public String contact_name_last;
    public String email;
    public int[] birthday;
    public ArrayList <String> restrictions;
    public ArrayList<String> cuisines;
    public int weight;
    public int restrict_count = 0;
    public int cuisine_count = 0;

    /*
    create separate database tables for cuisines and restrictions,
    stored with the selected "chips" first and store only the number
    of "chips" selected. If either "list" is added or subtracted from,
    modify the "number selected" variable accordingly and adjust the array

    birthday can be left as three separate ints in the database, similar to fname/lname

     */

    public Contact(){
        super();
    }

    public void setFirst(String first){this.contact_name_first = first;}
    public void setLast(String last) {this.contact_name_last = last;}
    public void setEmail(String email){
        this.email = email;
    }
    public void setWeight(int weight){this.weight = weight;}

    public void setBirthday(int year, int month, int day){
        if (birthday == null){
            birthday = new int[3];
        }
        birthday[0] = year;
        birthday[1] = month;
        birthday[2] = day;
    }

    public void loadRestrictions(List<String> list){
        restrictions = new ArrayList<>(list.size());
        restrictions.addAll(list);
    }

    public void loadCuisines(List<String> list){
        cuisines = new ArrayList<>(list.size());
        cuisines.addAll(list);
    }

    /*Add Functions
    Check to see if the string is in the array, if so removes it
    and adds it at the proper index inside the user selection half of the array, incrementing the count
     */
    public void addRestriction(String restriction) {
        if(restrictions.size() == 0){
            Log.v("Contact","ERROR: load restrictions with loadRestrictions function");
        }else{
            int index = restrictions.indexOf(restriction);

            if (index != -1) {
                restrictions.remove(index);
            }
            int i = 0;
            while (restrictions.get(i).compareTo(restriction) > 0 ){
                i++;
            }
            restrictions.add(i, restriction);
            restrict_count++;
        }
    }

    public void addCuisine(String cuisine) {
        if(cuisines.size() == 0){
            Log.v("Contact","ERROR: load cuisines with loadCuisines function");
        }else{
            int index = cuisines.indexOf(cuisine);

            if (index != -1) {
                cuisines.remove(index);
            }
            int i = 0;
            while (cuisines.get(i).compareTo(cuisine) > 0 ){
                i++;
            }
            cuisines.add(i, cuisine);
            cuisine_count++;
        }
    }
    /*Remove Functions
    Check if string is in Array and then shifts all indices left until reinserting at the proper index
     */
    public void removeRestriction (String restriction) {
        int index = restrictions.indexOf(restriction);
        if(restrict_count == 0 || restrictions.size() == 0 || index == -1){
            Log.v("Contact","ERROR: removeRestrictions");
        }
        else{
            restrict_count--;
            for (int i = index; i < restrictions.size(); i++){
                if( i < restrict_count -1 || restrictions.get(i).compareTo(restriction) > 0){
                    restrictions.set(i, restrictions.get(i+1));
                }else{
                    restrictions.set(i, restriction);
                    i = restrictions.size();
                }
            }
        }
    }

    public void removeCuisine (String cuisine) {
        int index = cuisines.indexOf(cuisine);
        if(cuisine_count == 0 || cuisines.size() == 0 || index == -1){
            Log.v("Contact","ERROR: removeCuisine");
        }
        else{
            cuisine_count--;
            /*for length of array
              if we're inside user selections or if we aren't at the strings
              sorted index yet, move on. Else, set that index.
            */
            for (int i = index; i < cuisines.size(); i++){
                if( i < cuisine_count -1 || cuisines.get(i).compareTo(cuisine) > 0){
                    cuisines.set(i, cuisines.get(i+1));
                }else{
                    cuisines.set(i, cuisine);
                    i = cuisines.size();
                }
            }
        }
    }

    //the string being added or removed

    public void setContact(Integer primary_key, String first, String last, String email){
        this.id = primary_key;
        this.contact_name_first = first;
        this.contact_name_last = last;
        this.email = email;
    }
}

