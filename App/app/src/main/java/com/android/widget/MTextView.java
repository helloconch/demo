package com.android.widget;


import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.android.app.Logs;

public class MTextView extends AppCompatTextView {
    public MTextView(Context context) {
        super(context);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        Logs.print("MTextView触发dispatchTouchEvent>>>>");
        //继续传递给自身的onTouchEvent
        return super.dispatchTouchEvent(event);
        //不传递给自身的onTouchEvent,传递给上层MLinearLayout的onTouchEvent
        //return false;
        //自身消费，事件停止，不传递给自身的onTouchEvent
        //return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        Logs.print("MTextView触发onTouchEvent>>>>");


        return super.onTouchEvent(event);

        //自身消费，事件停止，不传递给上层MLinearLayout的onTouchEvent
        //return true;

    }
}
