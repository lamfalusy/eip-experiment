package com.java.community.epi.orderprocessor.impl;

import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import com.java.community.epi.orderprocessor.model.AttachedDeviceOrderItem;
import com.java.community.epi.orderprocessor.model.DeviceOrderItem;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeviceOrderItemContentEnricher
        extends AbstractTransformer {

    private WarehouseService warehouseService;

    @Override
    protected Object doTransform(Message<?> message) throws Exception {
        DeviceOrderItem deviceOrderItem = (DeviceOrderItem) message.getPayload();
        AttachedDeviceOrderItem attachedDeviceOrderItem = new AttachedDeviceOrderItem(deviceOrderItem.getId(),
                deviceOrderItem.getDeviceName(), warehouseService.attachDevice(deviceOrderItem));
        return new GenericMessage<DeviceOrderItem>(attachedDeviceOrderItem, message.getHeaders());
    }



}
