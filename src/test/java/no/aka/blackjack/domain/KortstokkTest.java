package no.aka.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KortstokkTest {
    private Kortstokk kortstokk = new Kortstokk();

    /*
        Gitt at man lager en ny kortstokk
        Når man sjekker kortene
        Så skal antall i stokken være 52
        og alle kortene skal være forskjellige.
     */
    @Test
    void kortstokkHar52UnikeKort() {
        List<Kort> listeAvKort = lagListeAvAlleKortene(kortstokk);
        assertEquals(52, listeAvKort.size());
        for (Kort kort : listeAvKort) {
            assertEquals(1,
                    listeAvKort.stream()
                            .filter(k -> k.getRank().equals(kort.getRank())
                                    && k.getFarge().equals(kort.getFarge()))
                            .count());
        }


    }

    /*
        Gitt at man starter med en ny kortstokk
        Når man trekker 3 kort
        Så skal det være 49 kort igjen i kortstokken.
     */
    @Test
    void trekkKort_trekker3kort_forventer49igjen() {
        assertEquals(52, kortstokk.antallKortIgjenIKortstokk());
        kortstokk.trekkKort();
        kortstokk.trekkKort();
        kortstokk.trekkKort();
        assertEquals(49, kortstokk.antallKortIgjenIKortstokk());
    }

    @Test
    void antallKortIgjenIKortstokk_forventer52kort() {
        assertEquals(52, kortstokk.antallKortIgjenIKortstokk());
    }

    private List<Kort> lagListeAvAlleKortene(Kortstokk kortstokk) {
        List<Kort> alleKort = new LinkedList<>();
        for (int i = 0; i < 52; i++) {
            alleKort.add(kortstokk.trekkKort());
        }
        return alleKort;
    }
}