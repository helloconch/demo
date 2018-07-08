package com.bluetooth.model;

import android.bluetooth.BluetoothDevice;

public class BleDevice {
    private BluetoothDevice bluetoothDevice;
    private boolean scanSuccess;

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public boolean isScanSuccess() {
        return scanSuccess;
    }

    public void setScanSuccess(boolean scanSuccess) {
        this.scanSuccess = scanSuccess;
    }
}
