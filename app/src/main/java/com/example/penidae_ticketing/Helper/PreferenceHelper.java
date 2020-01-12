package com.example.penidae_ticketing.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.penidae_ticketing.model.User;

public class PreferenceHelper {
    private SharedPreferences sharedPreferences;
    private final String PREFERENCES_NAME="shared_preferences";
    private final String LOGIN = "login";
    private final String ID = "id";
    private final String NAME = "name";
    private final String USERNAME = "username";
    private final String PHONE = "phone";
    private final String EMAIL = "email";

    public PreferenceHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void setLogin(boolean login){
        sharedPreferences.edit()
                .putBoolean(LOGIN,login)
                .apply();
    }

    public boolean getLogin(){
        return sharedPreferences.getBoolean(LOGIN,false);
    }

    public void setId(String id){
        sharedPreferences.edit()
                .putString(ID,id)
                .apply();
    }

    public String getId(){
        return sharedPreferences.getString(ID,"");
    }

    public void setName(String name){
        sharedPreferences.edit()
                .putString(NAME,name)
                .apply();
    }

    public String getName(){
        return sharedPreferences.getString(NAME,"");
    }

    public void setUsername(String username){
        sharedPreferences.edit()
                .putString(USERNAME,username)
                .apply();
    }

    public String getUsername(){
        return sharedPreferences.getString(USERNAME,"");
    }

    public void setEmail(String email){
        sharedPreferences.edit()
                .putString(EMAIL,email)
                .apply();
    }

    public String getEmail(){
        return sharedPreferences.getString(EMAIL,"");
    }

    public void setPhone(String phone){
        sharedPreferences.edit()
                .putString(PHONE,phone)
                .apply();
    }

    public String getPhone(){
        return sharedPreferences.getString(PHONE,"");
    }

    public void setLogout(){
        sharedPreferences.edit()
                .clear()
                .apply();
    }

    public void setUser(User user){
        setLogin(true);
        setId(user.getId());
        setName(user.getName());
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPhone(user.getPhone());
    }
}
