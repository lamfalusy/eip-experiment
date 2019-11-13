package com.java.community.epi.orderprocessor.model;

import lombok.Data;

@Data
public class DeviceOrderItem extends OrderItem {

    public DeviceOrderItem(long id, String deviceName) {
        super(id);
        this.deviceName = deviceName;
    }

    private String deviceName;

    @Override
    public String toString() {
        return "DeviceOrderItem [deviceName=" + deviceName + ", id=" + getId() + "]";
    }
    
    
    
}
