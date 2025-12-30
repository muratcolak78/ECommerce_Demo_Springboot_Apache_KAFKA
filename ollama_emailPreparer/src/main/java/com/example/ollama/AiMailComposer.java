package com.example.ollama;

import com.example.ollama.model.dto.MailBodyRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiMailComposer {
    private final ChatClient chatClient;

    public AiMailComposer(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String composeShippingMail(MailBodyRequest mailBodyRequest) {
        String prompt = buildPrompt(mailBodyRequest);

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }
    private String buildPrompt(MailBodyRequest s) {
        String items = (s.getItemDtoList() == null ? "" :
                s.getItemDtoList().stream()
                        .map(i -> i.getQuantity() + " x " + i.getProductName())
                        .reduce((a, b) -> a + "\n" + b)
                        .orElse("")
        );

        return """
                Vollständiger Name: %s

                Bestellte Artikel:
                %s
                """.formatted(s.getFullName(), items);
    }
}

