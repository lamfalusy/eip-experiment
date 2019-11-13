package com.java.community.epi.orderprocessor.impl;

import java.util.List;
import org.springframework.integration.transformer.AbstractTransformer;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import com.java.community.epi.orderprocessor.model.Order;
import com.java.community.epi.orderprocessor.model.OrderItem;

public class AggregatedOrderTranformer extends AbstractTransformer {

    @Override
    protected Object doTransform(Message<?> message) throws Exception {
        List<OrderItem> orderItems = (List<OrderItem>) message.getPayload();
        Order order = new Order();
        order.setOrderItems(orderItems);
        order.setOrderId(message.getHeaders().get("orderId", Long.class));
        order.setCustomerId(message.getHeaders().get("customerId", Long.class));
        return new GenericMessage<Order>(order, message.getHeaders());
    }



}
