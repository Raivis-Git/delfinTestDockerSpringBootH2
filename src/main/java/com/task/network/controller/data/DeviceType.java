package com.task.network.controller.data;


import com.fasterxml.jackson.annotation.JsonProperty;

public enum DeviceType implements Comparable<DeviceType> {
    @JsonProperty("Gateway")
    GATEWAY,
    @JsonProperty("Switch")
    SWITCH,
    @JsonProperty("Access Point")
    ACCESS_POINT;
    
}
