package com.bluetooth.utils;

import java.util.UUID;

public class Params {
    public static final String BLE_HEALTHY_THERMOMETER_SERVICE_UUID =
            UUIDUtil.UUID_16bit_128bit("1809", true);
    //版本
    public static final String BLE_DEVICE_INFORMATION_FIRMWARE_REVISION_CHARACTERISTIC_UUID =
            UUIDUtil.UUID_16bit_128bit("2A26", true);
    public static final String BLE_YUNBI_HEALTHY_THERMOMETER_BIND_PHONE_CHARACTERISTIC_UUID =
            UUIDUtil.UUID_16bit_128bit("303C", true);
    public static final String BLE_HEALTHY_THERMOMETER_TIME_SYNC_CHARACTERISTIC_UUID =
            UUIDUtil.UUID_16bit_128bit("3031", true); // OK Indication
    public static final String BLE_DEVICE_INFORMATION_OAD_REQUEST_CHARACTERISTIC_UUID =
            "f000ffc1-0451-4000-b000-000000000000"; // 发送 OAD 请求指令
    public static final String BLE_DEVICE_INFORMATION_OAD_IMAGE_BLOCK_REQUEST_CHARACTERISTIC_UUID =
            "f000ffc2-0451-4000-b000-000000000000"; // 真正写的 OAD 数据

    public static final UUID[] TemperServiceUuids = {UUID.fromString(BLE_HEALTHY_THERMOMETER_SERVICE_UUID)};

}
