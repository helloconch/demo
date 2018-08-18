package com.conch.appanim.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.conch.appanim.R;

public class MyOvulationFrameLayout extends FrameLayout {
    private ImageView iconFollicular1;
    private ImageView iconFollicular2;
    private ImageView iconFollicular3;
    /**
     * 最底层背景
     */
    private ImageView iconFollicular4;


    public MyOvulationFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyOvulationFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyOvulationFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        iconFollicular1 = findViewById(R.id.iconFollicular1);
        iconFollicular2 = findViewById(R.id.iconFollicular2);
        iconFollicular3 = findViewById(R.id.iconFollicular3);
        iconFollicular4 = findViewById(R.id.iconFollicular4);
        iconFollicular1.animate().translationX(80).setDuration(0);

        ObjectAnimator globalXAnim =
                translationXAnim(this, 0, -15, 15, 15);

        ObjectAnimator globalYAnim =
                translationYAnim(this, 0, 20, 25, -20);


        ObjectAnimator iconFollicular4ScaleXAnim =
                scaleXAnim(iconFollicular4, 1, 1.08f, 1.02f);

        ObjectAnimator iconFollicular4ScaleYAnim =
                scaleYAnim(iconFollicular4, 1, 1.1f, 1.02f);

        ObjectAnimator iconFollicular3ScaleXAnim =
                scaleXAnim(iconFollicular3, 1, 1.05f, 0.95f);
        ObjectAnimator iconFollicular3ScaleYAnim =
                scaleYAnim(iconFollicular3, 1, 1.05f, 0.95f);


        ObjectAnimator iconFollicular2RotationAnim =
                rotationAnim(iconFollicular2, 0, 15, -5);
        ObjectAnimator iconFollicular2ScaleXAnim =
                scaleXAnim(iconFollicular2, 1f, 0.9f);
        ObjectAnimator iconFollicular2ScaleYAnim =
                scaleYAnim(iconFollicular2, 1f, 0.9f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(globalXAnim)
                .with(globalYAnim)
                .with(iconFollicular4ScaleXAnim)
                .with(iconFollicular4ScaleYAnim)
                .with(iconFollicular3ScaleXAnim)
                .with(iconFollicular3ScaleYAnim)
                .with(iconFollicular2RotationAnim)
                .with(iconFollicular2ScaleXAnim)
                .with(iconFollicular2ScaleYAnim);

        animatorSet.setDuration(8000);
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
                view, "translationY", values);
        objectAnimator.setRepeatCount(-1);
        objectAnimator.setRepeatMode(ValueAnimator.REVERSE);
        return objectAnimator;
    }
}
