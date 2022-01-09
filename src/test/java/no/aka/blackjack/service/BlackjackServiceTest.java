package no.aka.blackjack.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Todo: Denne klassen er sannsynligvis overflødig, da dette testes i Komponenttest
class BlackjackServiceTest {
    private BlackjackService blackjackService = new BlackjackService();

    @Test
    void getSpiller() {
        blackjackService.start("Testnavn");
        assertEquals(2, blackjackService.getSpiller().getHand().getListeAvKort().size());
    }

    @Test
    void getDealer() {
        blackjackService.start("Testnavn");
        blackjackService.getDealer().aktiverVisingAvKort();
        assertEquals(2, blackjackService.getDealer().getHand().getListeAvKort().size());
    }

    @Test
    void delKortTilSpiller() {
        blackjackService.start("Testnavn");
        blackjackService.delKortTilSpiller();
        assertEquals(3, blackjackService.getSpiller().getHand().getListeAvKort().size());
    }

    @Test
    void pass() {
        blackjackService.start("Testnavn");
        blackjackService.pass();
        assertEquals(2, blackjackService.getSpiller().getHand().getListeAvKort().size());
    }
}