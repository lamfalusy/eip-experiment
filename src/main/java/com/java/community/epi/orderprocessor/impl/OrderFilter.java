package com.java.community.epi.orderprocessor.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.messaging.Message;
import com.java.community.epi.orderprocessor.model.AttachedDeviceOrderItem;
import com.java.community.epi.orderprocessor.model.Order;
import com.java.community.epi.orderprocessor.model.OrderItem;

public class OrderFilter {

    public boolean isValid(Message<Order> message) {
        List<OrderItem> invalidOrderItems = message.getPayload().getOrderItems().stream()
            .filter(orderItem -> orderItem instanceof AttachedDeviceOrderItem && ((AttachedDeviceOrderItem) orderItem).getSerialNumber() == null)
            .collect(Collectors.toList());
        return invalidOrderItems.isEmpty();
    }
    
}
