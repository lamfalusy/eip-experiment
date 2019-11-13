package com.java.community.epi.orderprocessor.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ChannelConfiguration {

    public static final String ORDER_CHANNEL = "orderChannel";
    public static final String ORDER_ITEM_CHANNEL = "orderItemChannel";
    public static final String DEVICE_ORDER_ITEM_CHANNEL = "deviceOrderItemChannel";
    public static final String SERVICE_ORDER_ITEM_CHANNEL = "serviceOrderItemChannel";
    public static final String ENHANCED_ORDER_ITEM_CHANNEL = "enhancedOrderItemChannel";
    public static final String AGGREGATED_ORDER_ITEM_CHANNEL = "aggregatedOrderItemChannel";
    public static final String AGGREGATED_ORDER_CHANNEL = "aggregatedOrderChannel";
    public static final String INVALID_CHANNEL = "invalidOrderChannel";
    public static final String PROCESSED_ORDER_CHANNEL = "processedOrderChannel";
    
    @Bean
    public MessageChannel orderChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageChannel orderItemChannel() {
        return new PublishSubscribeChannel();
    }
    
    @Bean
    public MessageChannel deviceOrderItemChannel() {
        return new PublishSubscribeChannel();
    }
    
    @Bean
    public MessageChannel serviceOrderItemChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageChannel enhancedOrderItemChannel() {
        return new PublishSubscribeChannel();
    }

    @Bean
    public MessageChannel aggregatedOrderItemChannel() {
        return new PublishSubscribeChannel();
    }
    
    @Bean
    public MessageChannel aggregatedOrderChannel() {
        return new PublishSubscribeChannel();
    }
    
    @Bean
    public MessageChannel invalidOrderChannel() {
        return new PublishSubscribeChannel();
    }
    
    @Bean
    public MessageChannel processedOrderChannel() {
        return new PublishSubscribeChannel();
    }
    
}
