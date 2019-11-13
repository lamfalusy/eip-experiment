package com.java.community.epi.orderprocessor.model;

import lombok.Data;

@Data
public class ServiceOrderItem extends OrderItem {

    public ServiceOrderItem(long id, String serviceName) {
        super(id);
        this.serviceName = serviceName;
    }

    private String serviceName;

    @Override
    public String toString() {
        return "ServiceOrderItem [serviceName=" + serviceName + ", id()=" + getId() + "]";
    }
    
}
