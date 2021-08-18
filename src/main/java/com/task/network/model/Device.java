package com.task.network.model;

import com.task.network.controller.data.DeviceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "devices")
public class Device {
    @Id
    @Column(name = "device_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;
    
    private String macAddress;
    
    private String uplinkMacAddress;
    
    public Device() {
    }
    
    public Device(Long id, DeviceType deviceType, String macAddress, String uplinkMacAddress) {
        this.id = id;
        this.deviceType = deviceType;
        this.macAddress = macAddress;
        this.uplinkMacAddress = uplinkMacAddress;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public DeviceType getDeviceType() {
        return deviceType;
    }
    
    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }
    
    public String getMacAddress() {
        return macAddress;
    }
    
    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
    
    public String getUplinkMacAddress() {
        return uplinkMacAddress;
    }
    
    public void setUplinkMacAddress(String uplinkMacAddress) {
        this.uplinkMacAddress = uplinkMacAddress;
    }
}
