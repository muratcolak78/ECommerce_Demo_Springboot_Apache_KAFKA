package com.ecommerce.order.kafka;


import com.ecommerce.events.shipping.ShippingEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderReceivedProducer {

    private final KafkaTemplate<String, ShippingEvent> kafkaTemplate;

    public OrderReceivedProducer(KafkaTemplate<String, ShippingEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendShippingEvent(ShippingEvent event){
            kafkaTemplate.send("order_received_event", event.getOrderId().toString(), event);
    }
}

