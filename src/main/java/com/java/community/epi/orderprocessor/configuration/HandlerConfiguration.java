package com.java.community.epi.orderprocessor.configuration;

import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.AGGREGATED_ORDER_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.AGGREGATED_ORDER_ITEM_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.DEVICE_ORDER_ITEM_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.ENHANCED_ORDER_ITEM_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.INVALID_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.ORDER_ITEM_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.PROCESSED_ORDER_CHANNEL;
import static com.java.community.epi.orderprocessor.configuration.ChannelConfiguration.SERVICE_ORDER_ITEM_CHANNEL;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.aggregator.AggregatingMessageHandler;
import org.springframework.integration.aggregator.DefaultAggregatingMessageGroupProcessor;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.router.PayloadTypeRouter;
import org.springframework.integration.store.MessageGroupStore;
import org.springframework.integration.store.SimpleMessageStore;
import org.springframework.messaging.MessageHandler;
import com.java.community.epi.orderprocessor.impl.AggregatedOrderTranformer;
import com.java.community.epi.orderprocessor.impl.DeviceOrderItemContentEnricher;
import com.java.community.epi.orderprocessor.impl.OrderFilter;
import com.java.community.epi.orderprocessor.impl.WarehouseService;
import com.java.community.epi.orderprocessor.model.DeviceOrderItem;
import com.java.community.epi.orderprocessor.model.ServiceOrderItem;

@Configuration
public class HandlerConfiguration {

    @Bean
    @ServiceActivator(inputChannel = ORDER_ITEM_CHANNEL)
    public PayloadTypeRouter orderItemRouter() {
        PayloadTypeRouter router = new PayloadTypeRouter();
        router.setChannelMapping(DeviceOrderItem.class.getName(), DEVICE_ORDER_ITEM_CHANNEL);
        router.setChannelMapping(ServiceOrderItem.class.getName(), SERVICE_ORDER_ITEM_CHANNEL);
        router.setChannelMapping(ServiceOrderItem.class.getName(), ENHANCED_ORDER_ITEM_CHANNEL);
        return router;
    }
    
    @Bean
    @Transformer(inputChannel = DEVICE_ORDER_ITEM_CHANNEL, outputChannel = ENHANCED_ORDER_ITEM_CHANNEL)
    public DeviceOrderItemContentEnricher deviceOrderItemContentEnricher(WarehouseService warehouseService) {
        return new DeviceOrderItemContentEnricher(warehouseService);
    }
 
    @Bean
    public MessageGroupStore messageGroupStore() {
        return new SimpleMessageStore();
    }
    
    @Bean
    @ServiceActivator(inputChannel = ENHANCED_ORDER_ITEM_CHANNEL)
    public MessageHandler aggregator(MessageGroupStore messageGroupStore) {
        AggregatingMessageHandler aggregator =
                new AggregatingMessageHandler(new DefaultAggregatingMessageGroupProcessor(), messageGroupStore);
        aggregator.setOutputChannelName(AGGREGATED_ORDER_ITEM_CHANNEL);
        return aggregator;
    }
    
    @Bean
    @Transformer(inputChannel = AGGREGATED_ORDER_ITEM_CHANNEL, outputChannel = AGGREGATED_ORDER_CHANNEL)
    public AggregatedOrderTranformer aggregatedOrderTranformer() {
        return new AggregatedOrderTranformer();
    }
    
    @Bean
    @Filter(inputChannel = AGGREGATED_ORDER_CHANNEL,
            outputChannel = PROCESSED_ORDER_CHANNEL,
            discardChannel = INVALID_CHANNEL)
    public OrderFilter orderFilter() {
        return new OrderFilter();
    }
    
}
