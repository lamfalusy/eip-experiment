package com.java.community.epi.orderprocessor.impl;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.java.community.epi.orderprocessor.model.DeviceOrderItem;

@Service
public class WarehouseService {

    public UUID attachDevice(DeviceOrderItem order) {
        if("Missing Device".equals(order.getDeviceName())) {
            return null;
        }
        return UUID.randomUUID();
    }
    
}
