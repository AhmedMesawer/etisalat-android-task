package com.etisalat.sampletask.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {

    /**
     * check whether there is an internet or not
     * @return true if internet available false otherwise
     * */
    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) App.getContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (manager != null) {
            networkInfo = manager.getActiveNetworkInfo();
        }
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    /**
     * check string is not empty or null
     * @param s {@link String} object
     * @return true if s is not an empty or null string false otherwise
     * */
    public static boolean isNotNullOrEmpty(String s) {
        return s != null && !s.isEmpty();
    }
}
