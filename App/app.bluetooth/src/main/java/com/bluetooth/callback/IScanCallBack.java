package com.bluetooth.callback;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanResult;

import java.util.List;

public interface IScanCallBack {

    void onLeScanTargetDevice(BluetoothDevice bluetoothDevice);

    void scanTargetDevice(ScanResult result);

    void scanTargetDevices(List<ScanResult> results);

    void scanFinish();

    void scanFailed();
}
