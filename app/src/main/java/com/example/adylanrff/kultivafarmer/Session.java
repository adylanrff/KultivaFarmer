package com.example.adylanrff.kultivafarmer;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context context) {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setAuth(String auth) {
        prefs.edit().putString("auth", auth).apply();
    }

    public String getAuth() {
        return prefs.getString("auth", "");
    }
}
