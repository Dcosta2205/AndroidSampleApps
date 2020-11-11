package com.xyz.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author Lloyd Dcosta
 * This class is used to save and retrieve data to and from the preferece.
 */
public class PreferenceHelper {

    private static final String SHARED_PREFERENCE_KEY = "com.xyz.sharedpreferences";

    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    /**
     * This method is used to write an integer to the preference
     */
    public static void writeIntToPreference(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * This method is used to String an integer to the preference
     */
    public static void writeStringToPreference(Context context, String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference(context).edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * This method is used to get an String from the preference which is already saved in it
     */
    public static String getStringFromPreference(Context context, String key) {
        return getSharedPreference(context).getString(key, null);
    }

    /**
     * This method is used to get an integer from the preference which is already saved in it
     */
    public static int getIntFromPreference(Context context, String key) {
        return getSharedPreference(context).getInt(key, 0);
    }
}
