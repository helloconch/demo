package com.android.widget;


import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.android.app.Logs;

public class MLinearLayout extends LinearLayout {

    public MLinearLayout(Context context) {
        super(context);
    }

    public MLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Logs.print("MLinearLayout触发dispatchTouchEvent>>>>");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Logs.print("MLinearLayout触发onInterceptTouchEvent>>>>");
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logs.print("MLinearLayout触发onTouchEvent>>>>");
        //事件终止，不传递给上层Activity onTouchEvent
        return true;
        //事件会继续传递到上层Activity onTouchEvent
        //return super.onTouchEvent(event);
    }
}
