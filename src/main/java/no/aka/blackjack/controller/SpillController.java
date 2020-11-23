package no.aka.blackjack.controller;

import no.aka.blackjack.domain.Spiller;
import no.aka.blackjack.service.BlackjackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// TODO Legg inn mulighet for å registrere navn på spiller

@RestController
public class SpillController {

    @Autowired
    private BlackjackService blackjackService;

    /**
     * Starter et nytt spill med angitt spillernavn.
     *
     * Eksempel på bruk:<br/>
     *   <b>curl -X POST localhost:8080/start -H 'Content-type:application/json' -d '{"navn": "Alf-Kenneth Aabel"}'</b>
     *
     * @param spiller
     * @return Spillet som JSON
     */
    @PostMapping(value = "/start")
    ResponseEntity<BlackjackService> start(@RequestBody Spiller spiller) {
        blackjackService.start(spiller);
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
