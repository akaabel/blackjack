package no.aka.blackjack.controller;

import no.aka.blackjack.domain.Dealer;
import no.aka.blackjack.domain.Spiller;
import no.aka.blackjack.service.BlackjackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SpillController {

    private class SpillRespons {
        Spiller spiller;
        Dealer dealer;
        String status;
        long spillId;

        SpillRespons(BlackjackService blackjackService) {
            this.spiller = blackjackService.getSpiller();
            this.dealer = blackjackService.getDealer();
            this.status = String.valueOf(blackjackService.getStatus());
            this.spillId = blackjackService.getSpillId();
        }

        public Spiller getSpiller() {
            return spiller;
        }

        public Dealer getDealer() {
            return dealer;
        }

        public String getStatus() {
            return status;
        }

        public long getSpillId() {
            return spillId;
        }
    }

    private final BlackjackService blackjackService;

    public SpillController(@Autowired BlackjackService blackjackService) {
        this.blackjackService = blackjackService;
    }

    /**
     * Lager en SpillRespons som inneholder feltene som blir returnert i APIet
     * @param blackjackService
     * @return SpillRespons
     */
    private SpillRespons lagSpillRespons(BlackjackService blackjackService) {
        return new SpillRespons(blackjackService);
    }

    /**
     * Starter et nytt spill med angitt spillernavn.
     *
     * @param startSpillRequest Json objekt for å angi spilleren navn
     * @return Spillet som JSON
     */
    @PostMapping(value = "/startjson")
    ResponseEntity<SpillRespons> start(@RequestBody StartSpillRequest startSpillRequest) {
        blackjackService.start(startSpillRequest.getSpillernavn());
        return ResponseEntity.ok(new SpillRespons(blackjackService));
    }

    @PostMapping(value = "/start")
    ResponseEntity<SpillRespons> start(@RequestBody String spillernavn) {
        blackjackService.start(spillernavn);
        return ResponseEntity.ok(new SpillRespons(blackjackService));
    }

    @GetMapping("/hent/{id}")
    ResponseEntity<SpillRespons> hent(@PathVariable("id") Long id) {
        blackjackService.hentSpill(id);
        return ResponseEntity.ok(new SpillRespons(blackjackService));
    }

    @GetMapping("/vis")
    ResponseEntity<SpillRespons> vis() {
        return ResponseEntity.ok(new SpillRespons(blackjackService));
    }

    @GetMapping("/trekk")
    ResponseEntity<SpillRespons> trekk() {
        try {
            blackjackService.delKortTilSpiller();
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(new SpillRespons(blackjackService));

    }

    @GetMapping("/pass")
    ResponseEntity<SpillRespons> pass() {
        blackjackService.pass();
        return ResponseEntity.ok(new SpillRespons(blackjackService));
    }
}
