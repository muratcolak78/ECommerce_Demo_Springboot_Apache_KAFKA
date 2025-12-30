package com.ecommerce.mail.kafka;

import com.ecommerce.events.shipping.ShippingEvent;
import com.ecommerce.mail.service.MailService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderReceivedConsumer {
    private final MailService service;

    public OrderReceivedConsumer(MailService service) {
        this.service = service;
    }
    @KafkaListener(topics = "order_received_event")
    public void orderReceievedListener(ShippingEvent event){
        service.sendEmail(event);
    }
}
