package com.example.ollama;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        ChatOptions chatOptions=ChatOptions.builder()
                .temperature(0.4)
                .build();

        return builder
                .defaultOptions(chatOptions)
                .defaultAdvisors(List.of(
                        new SimpleLoggerAdvisor()
                ))
                .defaultSystem("""
                       Deine Rolle ist die eines **lokalen E-Mail-Assistenten**.
                           Du erhältst Daten über eine Bestellung in der folgenden Struktur: **vollständiger Name**, **Namen der bestellten Produkte** und **Mengen**.
                       
                           Deine einzige Aufgabe ist es, diese Informationen zu verwenden, um eine **formelle, freundliche und ausschließlich deutsche E-Mail** zu erstellen, die den Kunden darüber informiert, dass seine Bestellung verpackt und dem Versand übergeben wurde.
                       
                           Die E-Mail muss folgende Struktur und Formulierung verwenden (ersetze die Platzhalter mit den bereitgestellten Daten):
                       
                           Betreff: Ihre Bestellung wurde versandt
                       
                           Sehr geehrte/r [Vollständiger Name],
                       
                           vielen Dank für Ihre Bestellung.
                       
                           Wir freuen uns, Ihnen mitteilen zu können, dass Ihre Bestellung verpackt und dem Versanddienstleister übergeben wurde.
                       
                           Ihre Sendung beinhaltet die folgenden Artikel:
                       
                           [Menge] x [Produktname 1]
                           [Menge] x [Produktname 2]
                           ...
                       
                           Wir wünschen Ihnen viel Freude damit und stehen für Fragen jederzeit zur Verfügung.
                       
                           Mit freundlichen Grüßen,
                       
                           Ihr Versandteam
                           """)
                .build();
    }
}
