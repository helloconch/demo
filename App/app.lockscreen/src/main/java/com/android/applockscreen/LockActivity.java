package com.android.applockscreen;


import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.applockscreen.adpter.MyAdapter;
import com.android.applockscreen.utils.BitmapUtil;
import com.android.applockscreen.utils.BlurBitmapUtil;
import com.android.applockscreen.view.BlurredView;
import com.conch.appbase.utils.RouteUtils;

@Route(path = RouteUtils.User_LOCK_SCREEN)
public class LockActivity extends AppCompatActivity {
    private BlurredView blurredView;
    private RecyclerView recyclerView;
    private int mScrollerY;
    private int mAlpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
    }


    private void initView() {
        blurredView = findViewById(R.id.blurredView);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mScrollerY += dy;
                //根据滚动距离控制模糊程度 滚动距离是模糊程度的十倍
                if (Math.abs(mScrollerY) > 1000) {
                    mAlpha = 100;
                } else {
                    mAlpha = Math.abs(mScrollerY) / 10;
                }
                //设置透明度等级
                blurredView.setBlurredLevel(mAlpha);
            }
        });
    }

    /**
     * 实现沉浸式通知栏与导航栏
     *
     * @param hasFocus 是否有焦点
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @SuppressWarnings({"ResourceType", "deprecation"})
    public Bitmap getBlurBitmap() {
        Bitmap bitmap = BitmapUtil.drawableToBitmap(getResources().getDrawable(R.drawable.pic));
        return BlurBitmapUtil.blurBitmap(this, bitmap, 20f);
    }
}
