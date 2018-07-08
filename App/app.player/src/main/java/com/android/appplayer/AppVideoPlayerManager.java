package com.android.appplayer;

/**
 * Put JCVideoPlayer into layout
 * From a JCVideoPlayer to another JCVideoPlayer
 */
public class AppVideoPlayerManager {

    public static AppVideoPlayer FIRST_FLOOR_JCVD;
    public static AppVideoPlayer SECOND_FLOOR_JCVD;

    public static void setFirstFloor(AppVideoPlayer appVideoPlayer) {
        FIRST_FLOOR_JCVD = appVideoPlayer;
    }

    public static void setSecondFloor(AppVideoPlayer appVideoPlayer) {
        SECOND_FLOOR_JCVD = appVideoPlayer;
    }

    public static AppVideoPlayer getFirstFloor() {
        return FIRST_FLOOR_JCVD;
    }

    public static AppVideoPlayer getSecondFloor() {
        return SECOND_FLOOR_JCVD;
    }

    public static AppVideoPlayer getCurrentJcvd() {
        if (getSecondFloor() != null) {
            return getSecondFloor();
        }
        return getFirstFloor();
    }

    public static void completeAll() {
        if (SECOND_FLOOR_JCVD != null) {
            SECOND_FLOOR_JCVD.onCompletion();
            SECOND_FLOOR_JCVD = null;
        }
        if (FIRST_FLOOR_JCVD != null) {
            FIRST_FLOOR_JCVD.onCompletion();
            FIRST_FLOOR_JCVD = null;
        }
    }
}
