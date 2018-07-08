package com.android.app;


import android.util.Log;

public class Logs {
    static String tagName = "ABCDEFAPP";

    public static void print(String msg) {
        Log.i(tagName, msg);
    }
}
