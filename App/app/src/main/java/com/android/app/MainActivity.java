package com.android.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.server.BookController;
import com.conch.appbase.activity.BaseActivity;
import com.conch.appbase.event.MessageEvent;
import com.conch.appbase.utils.ModuleRouteService;
import com.conch.appbase.utils.RouteUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends BaseActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    private BookController bookController;

    private boolean connected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        findViewById(R.id.player).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPlayer();
            }
        });

        findViewById(R.id.lock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLock();
            }
        });

        findViewById(R.id.aidl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aidl();
            }
        });
        findViewById(R.id.bluetooth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bltooth();
            }
        });
        //RouteUtils.startLineChartActivity();
        RouteUtils.startChartActivity();
    }

    private void startPlayer() {
//        Intent intent = new Intent(MainActivity.this, VideoActivity.class);
//        startActivity(intent);
    }

    private void startLock() {
        RouteUtils.startLockScrenActivity();
    }

    private void aidl() {
//        try {
//            List<Book> datas = bookController.getBookList();
//            int size = datas != null ? datas.size() : 0;
//            Toast.makeText(MainActivity.this, "获取服务端数据大小:" + size, Toast.LENGTH_SHORT)
//                    .show();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } finally {
//        }
    }

    private void bltooth() {
        RouteUtils.startBlueToothActivity();
    }

    private void bindServer() {
        Intent intent = new Intent();
        intent.setPackage("com.android.server");
        intent.setAction("com.android.server.action");
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            bookController = BookController.Stub.asInterface(service);
            connected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            connected = false;
        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Logs.print("MainActivity触发onTouchEvent>>>>");
        return super.onTouchEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        if (event != null) {
            Toast.makeText(MainActivity.this, event.name, Toast.LENGTH_SHORT)
                    .show();
        }

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
