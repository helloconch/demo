package com.conch.appanim.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.conch.appanim.R;

public class MyFrameLayout extends FrameLayout {
    private ImageView iconPeriod1;
    private ImageView iconPeriod3;
    private ImageView iconPeriod4;


    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        iconPeriod1 = findViewById(R.id.iconPeriod1);
        iconPeriod3 = findViewById(R.id.iconPeriod3);
        iconPeriod4 = findViewById(R.id.iconPeriod4);

        ObjectAnimator iconPeriod1RotationAnim =
                rotationAnim(iconPeriod1, 0, 45, -45);

        ObjectAnimator iconPeriod1ScaleXAnim =
                scaleXAnim(iconPeriod3, 1, 1.05f, 0.95f);
        ObjectAnimator iconPeriod1ScaleYAnim =
                scaleYAnim(iconPeriod3, 1, 1.05f, 0.95f);

        ObjectAnimator iconPeriod3RotationAnim =
                rotationAnim(iconPeriod3, 0, 45, -45);

        ObjectAnimator iconPeriod4TranslaitonX = translationXAnim(iconPeriod4, 0, -20, 20);
        ObjectAnimator iconPeriod4TranslaitonY = translationYAnim(iconPeriod4, 0, -20, 20);

        ObjectAnimator iconPeriod4ScaleXAnim =
                scaleXAnim(iconPeriod4, 1, 1.1f, 1.03f);
        ObjectAnimator iconPeriod4ScaleYAnim =
                scaleYAnim(iconPeriod4, 1, 1.1f, 1.03f);

        AnimatorSet animatorSet = new AnimatorSet();

        animatorSet.play(iconPeriod1RotationAnim).with(iconPeriod3RotationAnim)
                .with(iconPeriod1ScaleXAnim).with(iconPeriod1ScaleYAnim)
                .with(iconPeriod4TranslaitonX).with(iconPeriod4TranslaitonY)
                .with(iconPeriod4ScaleXAnim).with(iconPeriod4ScaleYAnim);

        animatorSet.play(iconPeriod4TranslaitonX);
        animatorSet.setDuration(15000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }

    private ObjectAnimator rotationAnim(View view, float... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                view, "rotation", values);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }

    private ObjectAnimator scaleXAnim(View view, float... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                view, "scaleX", values);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }

    private ObjectAnimator scaleYAnim(View view, float... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                view, "scaleY", values);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }

    private ObjectAnimator translationXAnim(View view, float... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                view, "translationX", values);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }

    private ObjectAnimator translationYAnim(View view, float... values) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                view, "translationX", values);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }
}
