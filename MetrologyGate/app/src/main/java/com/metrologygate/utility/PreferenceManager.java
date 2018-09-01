package com.metrologygate.utility;

import android.content.Context;
import android.content.SharedPreferences;


public class PreferenceManager {
    Context context;
    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.MG_PREFS, 0);
        editor = sharedPreferences.edit();
        this.context = context;
    }

    public static void setPrefBool(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static void setPrefString(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public static void setPrefInt(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static void setPrefKarmaPercentage(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public static boolean getPrefBool(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public static String getPrefString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public static int getPrefInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public static int getPrefKarmaPercentage(String key) {
        return sharedPreferences.getInt(key, 100);
    }

    public static void deleteAll() {
        editor.clear();
        editor.commit();
    }
}
