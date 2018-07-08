package com.bluetooth.le;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

public class XBleService extends Service {

    private boolean supportBLE;
    private BluetoothAdapter mBluetoothAdapter;
    public static final UUID DESC_CCC = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private Handler mHandler = new Handler();
    private boolean mScanning;
    private static final long SCAN_PERIOD = 10000 * 3;

    @Override
    public void onCreate() {
        super.onCreate();
        supportBLE = getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_BLUETOOTH_LE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        scan(true);
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void scan(boolean enable) {
        if (supportBLE) {
            BluetoothManager bluetoothManager =
                    (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
            mBluetoothAdapter = bluetoothManager.getAdapter();
            if (enable) {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mScanning = false;

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            mBluetoothAdapter.getBluetoothLeScanner().stopScan(scanCallback);
                        } else {
                            mBluetoothAdapter.stopLeScan(mLeScanCallback);
                        }

                        Toast.makeText(getApplicationContext(), "关闭扫描BLE设备", Toast.LENGTH_SHORT).show();
                    }
                }, SCAN_PERIOD);

                mScanning = true;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mBluetoothAdapter.getBluetoothLeScanner().startScan(scanCallback);
                } else {
                    mBluetoothAdapter.startLeScan(mLeScanCallback);
                }
                Toast.makeText(getApplicationContext(), "扫描BLE设备", Toast.LENGTH_SHORT).show();
            } else {
                mScanning = false;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mBluetoothAdapter.getBluetoothLeScanner().stopScan(scanCallback);
                } else {
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }
        }

    }

    ScanCallback scanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);

        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);

        }
    };

    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice bluetoothDevice, int i, byte[] bytes) {


        }
    };
}
