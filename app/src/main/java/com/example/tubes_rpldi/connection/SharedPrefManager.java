package com.example.tubes_rpldi.connection;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.tubes_rpldi.Activity.LoginActivity;
import com.example.tubes_rpldi.model.Member;


public class SharedPrefManager {
    // constant
    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_ID = "keyid";

    private static SharedPrefManager mInstance;
    private static Context sharedPrefContext;

    private SharedPrefManager(Context context) {
        sharedPrefContext = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogin(Member user) {
        SharedPreferences sharedPreferences = sharedPrefContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId_member());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = sharedPrefContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NAME, null) != null;
    }

    public Member getUser() {
        SharedPreferences sharedPreferences = sharedPrefContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Member(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_USERNAME, null)
        );
    }

    public void logout() {
        SharedPreferences sharedPreferences = sharedPrefContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        sharedPrefContext.startActivity(new Intent(sharedPrefContext, LoginActivity.class));
    }
}
