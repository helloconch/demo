package com.android;

import android.util.Log;

import com.conch.appbase.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class Temp {

    public Temp() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void test(MessageEvent event) {
        Log.i("GUGYGSV", ">>>>>");
    }

}
