package com.conch.appbase.utils;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;

public class RouteUtils {
    public static final String User_LOCK_SCREEN = "/lock/lockscreen";
    public static final String User_BLUE_TOOTH = "/blue/bluetooth";
    public static final String USER_PLAYER = "/player/video";
    public static final String FIND_FRANGMENT_MAIN = "/find/main";

    public static final String CHART_MAIN = "/chart/main";
    public static final String CHART_LINE_MAIN = "/chart/line/main";

    public static final String LOCK_SERVICE = "/lock/service";

    public static Fragment getFindFragment() {
        Fragment fragment = (Fragment) ARouter.getInstance().build(FIND_FRANGMENT_MAIN).navigation();
        return fragment;
    }


    public static void startPlayer(String movieName) {
        ARouter.getInstance().build(USER_PLAYER).withString("movieName", movieName).navigation();

    }

    public static void startBlueToothActivity() {
        ARouter.getInstance().build(User_BLUE_TOOTH).navigation();
    }

    public static void startLockScrenActivity() {
        ARouter.getInstance().build(User_LOCK_SCREEN).navigation();
    }

    public static void startLineChartActivity() {
        ARouter.getInstance().build(CHART_LINE_MAIN).navigation();
    }

    public static void startChartActivity() {
        ARouter.getInstance().build(CHART_MAIN).navigation();
    }
}
