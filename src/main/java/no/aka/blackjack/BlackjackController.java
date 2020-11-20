package no.aka.blackjack;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlackjackController {

    @GetMapping("/start")
    String start() {
        return "hello blackjack";
    }
}
