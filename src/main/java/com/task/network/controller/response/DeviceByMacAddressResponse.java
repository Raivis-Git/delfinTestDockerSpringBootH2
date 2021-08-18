package com.task.network.controller.response;

import com.task.network.controller.data.DeviceType;

public class DeviceByMacAddressResponse {
    private DeviceType deviceType;
    private String macAddress;
    
    public DeviceByMacAddressResponse(DeviceType deviceType, String macAddress) {
        this.deviceType = deviceType;
        this.macAddress = macAddress;
    }
    
    public DeviceType getDeviceType() {
        return deviceType;
    }
    
    public String getMacAddress() {
        return macAddress;
    }
    
}
