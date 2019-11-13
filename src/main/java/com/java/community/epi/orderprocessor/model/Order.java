package com.java.community.epi.orderprocessor.model;

import java.util.List;
import lombok.Data;

@Data
public class Order {
    
    private long orderId;
    private long customerId;
    private List<OrderItem> orderItems;
    
}
