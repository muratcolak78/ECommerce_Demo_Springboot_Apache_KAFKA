package com.ecommerce.order.kafka;

import com.ecommerce.order.model.OrderSavedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {
    private final KafkaTemplate<String, OrderSavedEvent> kafkaTemplate;

    public OrderEventProducer(KafkaTemplate<String, OrderSavedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void publishOrderSaved(OrderSavedEvent event){
        kafkaTemplate.send("order.created", event.getOrderId().toString(),event);
    }
}
