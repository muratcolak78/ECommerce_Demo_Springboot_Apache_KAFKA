package com.example.ollama;

import com.example.ollama.model.dto.MailBodyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@CrossOrigin(origins = "*")
public class AiTestController {

    private final AiMailComposer composer;

    public AiTestController(AiMailComposer composer) {
        this.composer = composer;
    }

    @PostMapping("/shipping-mail")
    public ResponseEntity<String> test(@RequestBody MailBodyRequest mailBodyRequest) {
        System.out.println(mailBodyRequest);
        return ResponseEntity.ok(composer.composeShippingMail(mailBodyRequest));
    }
}
