package com.example.healself.controller;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.healself.model.User;

public class LoginController {
    private static final String SHARED_PREF = "sharedPefs";
    private static LoginController instance = null;

    private static User user;
    private LoginController() {
        super();
    }

    public static final LoginController getInstance(Context context) {
        if (LoginController.instance == null) {
            LoginController.instance = new LoginController();
        }
        recapUser(context);

        return LoginController.instance;
    }


    private static void recapUser(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");
        String password = sharedPreferences.getString("password", "");
        user = new User(email, password);
    }

    public void createUser(String email, String password, Context context) {
        user = new User(email, password);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.apply();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getPassword() {
        return user.getPassword();
    }


}
