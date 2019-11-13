package com.java.community.epi.orderprocessor.configuration;

import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.DEVICE_ORDER_ITEM_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.ENHANCED_ORDER_ITEM_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.ORDER_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.ORDER_ITEM_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.PROCESSED_ORDER_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.handler.LoggingHandler.Level;
import org.springframework.messaging.MessageHandler;

@Configuration
public class LoggerHandlerConfiguration {

    @Bean
    @ServiceActivator(inputChannel = ORDER_CHANNEL)
    public MessageHandler orderChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Order message: ' + payload");
        return loggingHandler;
    }
    
    @Bean
    @ServiceActivator(inputChannel = ORDER_ITEM_CHANNEL)
    public MessageHandler orderItemChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Order Item message: ' + payload + ', headers: ' + headers");
        return loggingHandler;
    }
 
    @Bean
    @ServiceActivator(inputChannel = SERVICE_ORDER_ITEM_CHANNEL, outputChannel = ENHANCED_ORDER_ITEM_CHANNEL)
    public MessageHandler serviceOrderItemChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Service Order Item message: ' + payload + ', headers: ' + headers");
        return loggingHandler;
    }
    
    @Bean
    @ServiceActivator(inputChannel = DEVICE_ORDER_ITEM_CHANNEL)
    public MessageHandler deviceOrderItemChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Device Order Item message: ' + payload + ', headers: ' + headers");
        return loggingHandler;
    }
    
    @Bean
    @ServiceActivator(inputChannel = ENHANCED_ORDER_ITEM_CHANNEL)
    public MessageHandler enhancedOrderItemChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Enhanced Order Item message: ' + payload + ', headers: ' + headers");
        return loggingHandler;
    }

    @Bean
    @ServiceActivator(inputChannel = AGGREGATED_ORDER_CHANNEL)
    public MessageHandler aggregatedOrderChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Aggregated Order message: ' + payload + ', headers: ' + headers");
        return loggingHandler;
    }
    
    @Bean
    @ServiceActivator(inputChannel = INVALID_CHANNEL)
    public MessageHandler invalidOrderChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Invalid Order message: ' + payload + ', headers: ' + headers");
        return loggingHandler;
    }
    
    @Bean
    @ServiceActivator(inputChannel = PROCESSED_ORDER_CHANNEL)
    public MessageHandler processedOrderChannelLogger() {
        LoggingHandler loggingHandler = new LoggingHandler(Level.INFO);
        loggingHandler.setLogExpressionString("'Processed Processed Order message: ' + payload + ', headers: ' + headers");
        return loggingHandler;
    }
    
}
