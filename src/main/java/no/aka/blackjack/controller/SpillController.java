package no.aka.blackjack.controller;

import no.aka.blackjack.domain.Dealer;
import no.aka.blackjack.domain.Spiller;
import no.aka.blackjack.service.BlackjackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpillController {

    @Autowired
    private BlackjackService blackjackService;

    @GetMapping(value = "/start")
    ResponseEntity<BlackjackService> start() {
        blackjackService.start();
        return ResponseEntity.ok(blackjackService);
    }

    @GetMapping("/vis")
    ResponseEntity<BlackjackService> vis() {
        return ResponseEntity.ok(blackjackService);
    }

    @GetMapping("/trekk")
    ResponseEntity<BlackjackService> trekk() {
        blackjackService.delKortTilSpiller();
        return ResponseEntity.ok(blackjackService);
    }

    @GetMapping("/pass")
    ResponseEntity<BlackjackService> pass() {
        blackjackService.pass();
        return ResponseEntity.ok(blackjackService);
    }
}
