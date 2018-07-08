package com.android.appplayer.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.android.appplayer.AppVideoPlayer;
import com.android.appplayer.AppVideoPlayerSimple;
import com.android.appplayer.AppVideoPlayerStandard;
import com.android.appplayer.R;


public class VideoActivity extends AppCompatActivity {

    AppVideoPlayerStandard appVideoPlayerStandard;
    AppVideoPlayerSimple appVideoPlayerSimple;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        appVideoPlayerStandard = findViewById(R.id.appVideoPlayerStandard);
        appVideoPlayerSimple = findViewById(R.id.appVideoPlayerSimple);

    }

    @Override
    protected void onStart() {
        super.onStart();
        String url = "http://2449.vod.myqcloud.com/2449_22ca37a6ea9011e5acaaf51d105342e3.f20.mp4";
        String imgUrl = "http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640";
        appVideoPlayerStandard.setUp(url,
                AppVideoPlayer.SCREEN_LAYOUT_NORMAL,
                "嫂子闭眼睛");
        appVideoPlayerStandard.thumbImageView.setImageURI(
                Uri.parse(imgUrl));


        appVideoPlayerSimple.setUp(url,
                AppVideoPlayer.SCREEN_LAYOUT_NORMAL,
                "嫂子闭眼睛");


    }
}
