package com.conch.appbase.utils;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class RouteUtils {
    public static final String User_LOCK_SCREEN = "/user/lockscreen";
    public static final String USER_PLAYER = "/user/player";
    public static final String FIND_FRANGMENT_MAIN = "/find/main";

    public static Fragment getFindFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(FIND_FRANGMENT_MAIN).navigation();
        return fragment;
    }


    public static void startPlayer(String movieName) {
        ARouter.getInstance().build(USER_PLAYER).withString("movieName", movieName).navigation();

    }

    public static void startLockScrenActivity() {
        ARouter.getInstance().build(User_LOCK_SCREEN).navigation();
    }
}
