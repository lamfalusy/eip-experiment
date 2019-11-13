package com.java.community.epi.orderprocessor.impl;

import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.ORDER_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.ORDER_ITEM_CHANNEL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.integration.annotation.Splitter;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import com.java.community.epi.orderprocessor.model.Order;
import com.java.community.epi.orderprocessor.model.OrderItem;

@Component
public class OrderSplitter {

    @Splitter(inputChannel = ORDER_CHANNEL, outputChannel = ORDER_ITEM_CHANNEL)
    public List<Message<OrderItem>> splitItem(Order order) {
        return order.getOrderItems().stream()
                .map(orderItem -> convertToMessage(order, orderItem))
                .collect(Collectors.toList());
    }

    private Message<OrderItem> convertToMessage(Order order, OrderItem orderItem) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("orderId", order.getOrderId());
        headers.put("customerId", order.getCustomerId());
        return new GenericMessage<OrderItem>(orderItem, headers);
    }

}
