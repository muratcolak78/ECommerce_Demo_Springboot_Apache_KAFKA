package com.ecommerce.order.kafka;

import com.ecommerce.order.model.OrderSavedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderSavedListener {

    @KafkaListener(topics = "order.created", groupId = "mygroup")
    public void onOrderSaved(OrderSavedEvent event){
        System.out.println(event);
    }
}
