package com.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bluetooth.callback.IScanCallBack;
import com.bluetooth.le.Xble;
import com.bluetooth.utils.Params;
import com.conch.appbase.utils.ModuleRouteService;
import com.conch.appbase.utils.RouteUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 蓝牙
 * 在6.0设备需要开启android.permission.ACCESS_COARSE_LOCATION以及GPS
 */
@Route(path = RouteUtils.User_BLUE_TOOTH)
public class BlueToothActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOCATION_SETTINGS = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        findViewById(R.id.scanDevices)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(BlueToothActivity.this,
                                ModuleRouteService.getUserAddress(""), Toast.LENGTH_SHORT).show();
//                        handleBLE();
                        //模块间通信-服务调用，未直接引用lockscreen module,确使用lockscreen中数据
//                        String msg = ModuleRouteService.getUserAddress("");
//                        Toast.makeText(BlueToothActivity.this,
//                                msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkPermission();
    }

    private void checkPermission() {

        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        boolean locationState = isLocationEnable(this);

        if (!locationState && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setLocationService();
        }
    }

    private void setLocationService() {
        Intent locationIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        this.startActivityForResult(locationIntent, REQUEST_CODE_LOCATION_SETTINGS);
    }

    public boolean isLocationEnable(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean networkProvider = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean gpsProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (networkProvider || gpsProvider) return true;
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_LOCATION_SETTINGS) {
            if (isLocationEnable(this)) {
                Toast.makeText(BlueToothActivity.this, "定位已处理", Toast.LENGTH_SHORT).show();
            } else {
                //定位依然没有打开的处理
                Toast.makeText(BlueToothActivity.this, "定位未处理", Toast.LENGTH_SHORT).show();
            }
        } else super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleBLE() {
        Xble.getInstance().init(this);
        Xble.getInstance().scanDevices(new IScanCallBack() {
            @Override
            public void onLeScanTargetDevice(BluetoothDevice bluetoothDevice) {

            }

            @Override
            public void scanTargetDevice(ScanResult result) {

            }

            @Override
            public void scanTargetDevices(List<ScanResult> results) {

            }

            @Override
            public void scanFinish() {

            }

            @Override
            public void scanFailed() {

            }
        }, Params.TemperServiceUuids);
    }
}
