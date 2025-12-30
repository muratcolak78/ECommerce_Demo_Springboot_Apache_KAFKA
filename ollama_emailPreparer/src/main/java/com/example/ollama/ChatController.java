package com.example.ollama;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class ChatController {
    private final ChatClient chatClient;

    public ChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    @GetMapping("/chat")
    private ResponseEntity<String> chat(@RequestParam String message){
        String result=chatClient
                .prompt()
                .user(message)
                .call()
                .content();
        return ResponseEntity.ok(result);
    }
}
