package no.aka.blackjack.controller;

import no.aka.blackjack.service.BlackjackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpillController {

    @Autowired
    private BlackjackService blackjackService;

    /**
     * Starter et nytt spill med angitt spillernavn.
     *
     * @param startSpillRequest Json objekt for å angi spilleren navn
     * @return Spillet som JSON
     */
    @PostMapping(value = "/startjson")
    ResponseEntity<BlackjackService> start(@RequestBody StartSpillRequest startSpillRequest) {
        blackjackService.start(startSpillRequest.getSpillernavn());
        return ResponseEntity.ok(blackjackService);
    }

    @PostMapping(value = "/start")
    ResponseEntity<BlackjackService> start(@RequestBody String spillernavn) {
        blackjackService.start(spillernavn);
        return ResponseEntity.ok(blackjackService);
    }

    @GetMapping("/vis")
    ResponseEntity<BlackjackService> vis() {
        return ResponseEntity.ok(blackjackService);
    }

    @GetMapping("/trekk")
    ResponseEntity<BlackjackService> trekk() {
        try {
            blackjackService.delKortTilSpiller();
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(blackjackService);
    }

    @GetMapping("/pass")
    ResponseEntity<BlackjackService> pass() {
        blackjackService.pass();
        return ResponseEntity.ok(blackjackService);
    }
}
