package com.ecommerce.mail.service.impl;

import com.ecommerce.events.shipping.ShippingEvent;
import com.ecommerce.events.shipping.ShippingItemEvent;
import com.ecommerce.mail.model.MailObject;
import com.ecommerce.mail.model.MailStatus;
import com.ecommerce.mail.model.dto.MailBodyRequest;
import com.ecommerce.mail.model.dto.ShippingItemDto;
import com.ecommerce.mail.repository.MailRepository;
import com.ecommerce.mail.service.MailDispatchService;
import com.ecommerce.mail.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailServiceImpl implements MailService{

private final MailRepository repository;
private final WebClient webClient;
private final MailDispatchService mailSender;
private final static Logger LOGGER= LoggerFactory.getLogger(MailServiceImpl.class);


    public MailServiceImpl(MailRepository repository, WebClient webClient, MailDispatchService mailSender) {
        this.repository = repository;
        this.webClient = webClient;
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(ShippingEvent event) {
        LOGGER.info(String.format(">>> Shipping Event receieved : -> %s",event.getOrderId()));

        if (repository.existsByOrderId(event.getOrderId())) {
            return;
        }
        if (event.getEmail() == null || event.getEmail().trim().isEmpty()) {
            LOGGER.error(">>> Email address is empty or null: Order ID -> {}", event.getOrderId());
            return;
        }

        MailObject mailObject=new MailObject();
        mailObject.setEmail(event.getEmail());
        mailObject.setStatus(MailStatus.ORDER_RECEIVED);
        mailObject.setFullName(event.getFullName());
        mailObject.setOrderId(event.getOrderId());
        mailObject.setUserId(event.getUserId());


        MailBodyRequest mailBodyRequest=new MailBodyRequest();
        mailBodyRequest.setFullName(event.getFullName());
        mailBodyRequest.setOrderId(event.getOrderId());
        List<ShippingItemDto> shippingItemDtoList=new ArrayList<>();

        for(ShippingItemEvent itemEvent:event.getShippingItemEventList()){
           ShippingItemDto shippingItemDto=new ShippingItemDto();
           shippingItemDto.setProductId(itemEvent.getProductId());
           shippingItemDto.setQuantity(itemEvent.getQuantity());
           shippingItemDto.setProductName(itemEvent.getProductName());
           shippingItemDtoList.add(shippingItemDto);

        }
        mailBodyRequest.setItemDtoList(shippingItemDtoList);



        String body=getBodyFromKi(mailBodyRequest);

        if (body == null || body.trim().isEmpty()) {
            LOGGER.error("KI servisten e-posta gövdesi (body) boş veya null geldi. E-posta gönderilemedi.");
            return; // Veya uygun bir hata fırlatın
        }
        mailObject.setBody(body);
        repository.save(mailObject);

        LOGGER.info(String.format(">>>  KI body is : -> %s",body));
        String to= event.getEmail();
        String subject="Ihre Bestellung wurde angenommen.";
        try {
            mailSender.send(to, subject, body);
            LOGGER.info(String.format(">>> Received Mail sent : -> %s", event.getOrderId()));
        } catch (Exception e) {
            LOGGER.error(String.format(">>> Error by sending email : -> %s", e.getMessage()), e); // <- Full stack trace
            throw new RuntimeException(e);
        }



    }

    public String getBodyFromKi(MailBodyRequest mailBodyRequest) {
        String response = webClient.post()
                .uri("http://localhost:8090/api/ai/shipping-mail")
                .bodyValue(mailBodyRequest)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        LOGGER.info("AI Servis Yanıtı: {}", response); // <- Bu satırı ekle
        return response;
    }
}
