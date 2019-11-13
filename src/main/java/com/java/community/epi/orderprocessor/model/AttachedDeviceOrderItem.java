package com.java.community.epi.orderprocessor.model;

import java.util.UUID;
import lombok.Data;

@Data
public class AttachedDeviceOrderItem extends DeviceOrderItem {

    public AttachedDeviceOrderItem(long id, String deviceName, UUID serialNumber) {
        super(id, deviceName);
        this.serialNumber = serialNumber;
    }

    private UUID serialNumber;

    @Override
    public String toString() {
        return "AttachedDeviceOrderItem [serialNumber=" + serialNumber + ", deviceName=" + getDeviceName() + ", id="
                + getId() + "]";
    }

}
