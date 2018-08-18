package com.bluetooth.le;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.bluetooth.callback.IScanCallBack;
import com.bluetooth.model.BleDevice;
import com.bluetooth.model.GattResult;
import com.bluetooth.model.GattType;
import com.bluetooth.utils.Params;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class Xble {
    private static final String TAG = "XBLEABCD";
    private static volatile Xble instance;
    private BluetoothAdapter mBluetoothAdapter;
    private static WeakReference<Context> mContext;

    public static Xble getInstance() {
        if (instance == null) {
            synchronized (Xble.class) {
                if (instance == null) {
                    instance = new Xble();
                }
            }
        }
        return instance;
    }

    public void init(Context context) {
        if (mContext == null || mContext.get() == null) {
            mContext = new WeakReference<>(context);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            BluetoothManager bluetoothManager =
                    (BluetoothManager) mContext.get().getSystemService(Context.BLUETOOTH_SERVICE);
            mBluetoothAdapter = bluetoothManager.getAdapter();
        }

    }

    /**
     * 检测蓝牙状态
     *
     * @return
     */
    public boolean bluetoothState() {
        return mBluetoothAdapter == null || mBluetoothAdapter.isEnabled();
    }

    /**
     * 设备是否支持BLE
     *
     * @return
     */
    private boolean supportBLE() {
        if (mContext.get() != null) {
            boolean result = mContext.get().getPackageManager().hasSystemFeature(
                    PackageManager.FEATURE_BLUETOOTH_LE);
            return result;
        }
        return false;
    }


    private Observable<BleDevice> sanDevicesObservabe() {
        return Observable.create(new ObservableOnSubscribe<BleDevice>() {
            @Override
            public void subscribe(final ObservableEmitter<BleDevice> emitter) throws Exception {

                if (mBluetoothAdapter != null) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        mBluetoothAdapter.getBluetoothLeScanner().startScan(new ScanCallback() {
                            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                            @Override
                            public void onScanResult(int callbackType, ScanResult result) {
                                if (emitter != null && !emitter.isDisposed()) {
                                    BleDevice bleDevice = new BleDevice();
                                    bleDevice.setScanSuccess(true);
                                    bleDevice.setBluetoothDevice(result.getDevice());
                                    emitter.onNext(bleDevice);
                                }
                            }

                            @Override
                            public void onBatchScanResults(List<ScanResult> results) {
                                if (emitter != null && !emitter.isDisposed()) {
                                    BleDevice bleDevice = new BleDevice();
                                    bleDevice.setScanSuccess(true);
                                    emitter.onNext(bleDevice);
                                }
                            }

                            @Override
                            public void onScanFailed(int errorCode) {
                                if (emitter != null && !emitter.isDisposed()) {
                                    BleDevice bleDevice = new BleDevice();
                                    bleDevice.setScanSuccess(false);
                                    emitter.onNext(bleDevice);
                                }
                            }
                        });
                    } else {
                        mBluetoothAdapter.startLeScan(new BluetoothAdapter.LeScanCallback() {
                            @Override
                            public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bytes) {
                                if (emitter != null && !emitter.isDisposed()) {
                                    BleDevice bleDevice = new BleDevice();
                                    bleDevice.setScanSuccess(true);
                                    bleDevice.setBluetoothDevice(bluetoothDevice);
                                    emitter.onNext(bleDevice);
                                }
                            }
                        });
                    }
                }


            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * 扫描特定设备
     *
     * @param serviceUuids
     * @return
     */
    private Observable<BleDevice> sanDevicesObservabe(final UUID[] serviceUuids) {
        return Observable.create(new ObservableOnSubscribe<BleDevice>() {
            @Override
            public void subscribe(final ObservableEmitter<BleDevice> emitter) throws Exception {

                BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
                    /**
                     *
                     * @param bluetoothDevice
                     * @param rssi 表示ble设备的信号值，该值为负数，值越大表示信号值越好
                     * @param scanRecord ble设备发出的广播包数据
                     */
                    @Override
                    public void onLeScan(BluetoothDevice bluetoothDevice, int rssi, byte[] scanRecord) {
                        if (emitter != null && !emitter.isDisposed()) {
                            BleDevice bleDevice = new BleDevice();
                            bleDevice.setScanSuccess(true);
                            bleDevice.setBluetoothDevice(bluetoothDevice);
                            emitter.onNext(bleDevice);
                            mBluetoothAdapter.stopLeScan(this);
                        }
                    }
                };
                if (mBluetoothAdapter != null) {
                    mBluetoothAdapter.startLeScan(serviceUuids, leScanCallback);
                }


            }
        }).subscribeOn(Schedulers.io());
    }


    private Observable<GattResult> connGatt(final UUID[] serviceUuids) {

        return Observable.create(new ObservableOnSubscribe<GattResult>() {
            @Override
            public void subscribe(final ObservableEmitter<GattResult> emitter) throws Exception {
                BleDevice bleDevice = sanDevicesObservabe(serviceUuids).blockingFirst();
                if (bleDevice == null) return;
                bleDevice.getBluetoothDevice().connectGatt(mContext.get(),
                        true, new BluetoothGattCallback() {
                            @Override
                            public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                                if (gatt == null) return;
                                GattResult gattResult = new GattResult();
                                gattResult.setGatt(gatt);
                                gattResult.setStatus(newState);
                                if (newState == BluetoothProfile.STATE_CONNECTED) {
                                    gattResult.setStatusDes("onConnectionStateChange()>>>已连接");
                                    gatt.discoverServices();
                                } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                                    gattResult.setStatusDes("onConnectionStateChange()>>>已断开连接");
                                }
                                if (emitter != null && !emitter.isDisposed()) {
                                    emitter.onNext(gattResult);
                                }
                            }

                            @Override
                            public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                                if (gatt == null) return;
                                GattResult gattResult = new GattResult();
                                gattResult.setGatt(gatt);
                                gattResult.setStatus(status);
                                gattResult.setType(GattType.ServicesDiscovered);
                                if (status == BluetoothGatt.GATT_SUCCESS) {
                                    gattResult.setStatusDes("onServicesDiscovered()>>>GATT_SERVICES_DISCOVERED");
                                } else {
                                    gattResult.setStatusDes("onServicesDiscovered()>>>received:" + status);
                                }
                                if (emitter != null && !emitter.isDisposed()) {
                                    emitter.onNext(gattResult);
                                }
                            }

                            @Override
                            public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                                if (gatt == null) return;
                                GattResult gattResult = new GattResult();
                                gattResult.setGatt(gatt);
                                gattResult.setStatus(status);
                                if (status == BluetoothGatt.GATT_SUCCESS) {
                                    gattResult.setStatusDes("onCharacteristicRead()>>>DATA_AVAILABLE");
                                } else {
                                    gattResult.setStatusDes("onCharacteristicRead()>>>received:" + status);
                                }
                                gattResult.setCharacteristic(characteristic);
                                if (characteristic != null) {
                                    if (characteristic.getUuid().toString().equals(Params.BLE_DEVICE_INFORMATION_FIRMWARE_REVISION_CHARACTERISTIC_UUID)) {
                                        byte[] val = characteristic.getValue();
                                        String version = new String(val);
                                        gattResult.setStatusDes("onCharacteristicRead()>>读取固件版本：" + version);
                                    }
                                    gattResult.setValue(characteristic.getValue());
                                }
                                if (emitter != null && !emitter.isDisposed()) {
                                    emitter.onNext(gattResult);
                                }
                            }

                            @Override
                            public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                                if (gatt == null) return;
                                GattResult gattResult = new GattResult();
                                gattResult.setGatt(gatt);
                                gattResult.setStatusDes("onCharacteristicWrite()>>>");
                                gattResult.setCharacteristic(characteristic);
                                if (characteristic != null) {
                                    if (characteristic.getUuid().toString().equals(Params.BLE_HEALTHY_THERMOMETER_TIME_SYNC_CHARACTERISTIC_UUID)) {
                                        gattResult.setStatusDes("onCharacteristicWrite()>>触发写时间操作");
                                    }
                                    gattResult.setValue(characteristic.getValue());
                                }

                                if (emitter != null && !emitter.isDisposed()) {
                                    emitter.onNext(gattResult);
                                }
                            }

                            @Override
                            public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                                super.onDescriptorRead(gatt, descriptor, status);
                            }

                            @Override
                            public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
                                super.onDescriptorWrite(gatt, descriptor, status);
                            }

                            @Override
                            public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                                if (gatt == null) return;
                                GattResult gattResult = new GattResult();
                                gattResult.setGatt(gatt);
                                gattResult.setStatusDes("onCharacteristicChanged()>>>");
                                gattResult.setCharacteristic(characteristic);
                                if (characteristic != null) {
                                    if (characteristic.getUuid().toString().equals(Params.BLE_HEALTHY_THERMOMETER_TIME_SYNC_CHARACTERISTIC_UUID)) {
                                        gattResult.setStatusDes("onCharacteristicChanged()>>>时间同步成功");
                                    }
                                    gattResult.setValue(characteristic.getValue());
                                }
                                if (emitter != null && !emitter.isDisposed()) {
                                    emitter.onNext(gattResult);
                                }
                            }
                        });


            }
        }).subscribeOn(Schedulers.io());

    }


    /**
     * 扫描设备
     */
    public void scanDevices(final IScanCallBack scanCallBack, UUID[] serviceUuids) {

        connGatt(serviceUuids)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GattResult>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i(TAG, "onSubscribe()>>>");
                    }

                    @Override
                    public void onNext(GattResult gattResult) {
                        Log.i(TAG, "onNext()>>>mac:" + gattResult.getGatt().getDevice().getAddress());
                        Log.i(TAG, "onNext()>>>name:" + gattResult.getGatt().getDevice().getName());
                        Log.i(TAG, "onNext()>>>uuid:" + gattResult.getGatt().getDevice().getUuids());
                        Log.i(TAG, "onNext()>>>service size:" + gattResult.getGatt().getServices().size());
                        Log.i(TAG, "onNext()>>>statusDes:" + gattResult.getStatusDes());


                        if (gattResult.getType() == GattType.ServicesDiscovered)
                            handlerGatt(gattResult.getGatt());
                        Log.i(TAG, "--------------------------------------------------------");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError()>>>" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete()>>>");
                    }
                });

    }

    private void handlerGatt(BluetoothGatt gatt) {
        if (gatt.getServices().size() <= 0) return;
        for (BluetoothGattService bleGattService : gatt.getServices()) {
            List<BluetoothGattCharacteristic> bleGattCharacteristicList = bleGattService.getCharacteristics();
            for (BluetoothGattCharacteristic bleGattCharacteristic : bleGattCharacteristicList) {
                if (Params.BLE_YUNBI_HEALTHY_THERMOMETER_BIND_PHONE_CHARACTERISTIC_UUID.equals(bleGattCharacteristic.getUuid().toString())) {
                    Log.i(TAG, "发现 BIND_PHONE_CHARACTERISTIC_UUID!");
                } else if (Params.BLE_DEVICE_INFORMATION_FIRMWARE_REVISION_CHARACTERISTIC_UUID.equals(bleGattCharacteristic.getUuid().toString())) {
                    Log.i(TAG, "读取固件版本指令--回调onCharacteristicRead");

                    gatt.readCharacteristic(bleGattCharacteristic);

                } else if (Params.BLE_HEALTHY_THERMOMETER_TIME_SYNC_CHARACTERISTIC_UUID.equals(bleGattCharacteristic.getUuid().toString())) {
                    Log.i(TAG, "时间同步指令");
                    //I. 调用此接口syncTime，进行时间同步请求
                    //II. 在onCharacteristicChanged中对TIME_SYNC_UUID进行indication enable
                    //III. 在onCharacteristicChanged回调中收到UUID == TIME_SYNC_UUID的response，则为同步时间成功

                    //写数据
//                    writeTime(gatt, bleGattCharacteristic);
                    gatt.setCharacteristicNotification(bleGattCharacteristic, true);
                } else if (Params.BLE_DEVICE_INFORMATION_OAD_REQUEST_CHARACTERISTIC_UUID.equals(bleGattCharacteristic.getUuid().toString())) {
                    Log.i(TAG, "发送 OAD 请求指令");

                } else if (Params.BLE_DEVICE_INFORMATION_OAD_IMAGE_BLOCK_REQUEST_CHARACTERISTIC_UUID.equals(bleGattCharacteristic.getUuid().toString())) {
                    Log.i(TAG, "写的 OAD 数据");

                }
            }
        }

    }

    private void writeTime(BluetoothGatt gat, BluetoothGattCharacteristic bleGattCharacteristic) {
        long time = System.currentTimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date1 = new Date(time);
        String dateStr = format.format(date1);

        int year = Integer.parseInt("" + dateStr.subSequence(2, 4));
        int month = Integer.parseInt("" + dateStr.subSequence(5, 7));
        int day = Integer.parseInt("" + dateStr.subSequence(8, 10));
        int hour = Integer.parseInt("" + dateStr.subSequence(11, 13));
        int minute = Integer.parseInt("" + dateStr.subSequence(14, 16));

        byte[] timeValue = {(byte) 0x5A, (byte) 0x03, (byte) 0x06, (byte) year, (byte) month, (byte) day, (byte) hour, (byte) minute, (byte) 0xFA};
        bleGattCharacteristic.setValue(timeValue);
        gat.writeCharacteristic(bleGattCharacteristic);
    }

}
