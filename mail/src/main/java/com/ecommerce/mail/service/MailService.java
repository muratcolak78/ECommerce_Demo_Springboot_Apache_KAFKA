package com.ecommerce.mail.service;


import com.ecommerce.events.shipping.ShippingEvent;

public interface MailService {
    void sendEmail(ShippingEvent event);
}
