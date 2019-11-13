package com.java.community.eip.orderprocessor.integrationtests;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.junit4.SpringRunner;
import com.java.community.epi.orderprocessor.Application;
import com.java.community.epi.orderprocessor.model.DeviceOrderItem;
import com.java.community.epi.orderprocessor.model.Order;
import com.java.community.epi.orderprocessor.model.OrderItem;
import com.java.community.epi.orderprocessor.model.ServiceOrderItem;

@RunWith(SpringRunner.class)
@EnableIntegration
@SpringBootTest(classes = Application.class)
public class OrderProcessorIT {

    @Autowired
    private MessageChannel orderChannel;
    
    @Test
    public void testValidOrderProcess() {
        Order order = new Order();
        order.setCustomerId(123);
        order.setOrderId(123);
        order.setOrderItems(Arrays.asList((OrderItem) new DeviceOrderItem(1, "Ultimate Device"), (OrderItem) new ServiceOrderItem(2, "Ultimate Service")));
        orderChannel.send(new GenericMessage<Order>(order));
    }
    
    @Test
    public void testInvalidOrderProcess() {
        Order order = new Order();
        order.setCustomerId(123);
        order.setOrderId(123);
        order.setOrderItems(Arrays.asList((OrderItem) new DeviceOrderItem(1, "Missing Device"), (OrderItem) new ServiceOrderItem(2, "Ultimate Service")));
        orderChannel.send(new GenericMessage<Order>(order));
    }
    
}
