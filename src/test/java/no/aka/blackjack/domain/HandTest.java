package no.aka.blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    private Hand hand;

    @BeforeEach
    void setup() {
        hand = new Hand();
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Hjerter));
        hand.leggTilKort(new Kort(Kort.Rank.FEM, Kort.Farge.Spar));
    }

    @Test
    void getListeAvKort_forventer2Kort() {
        assertEquals(2, hand.getListeAvKort().size());
    }

    @Test
    void leggTilKort_leggerTilEttKort_forventer3Kort() {
        hand.leggTilKort(new Kort(Kort.Rank.NI, Kort.Farge.Spar));
        assertEquals(3, hand.getListeAvKort().size());
    }

    @Test
    void getMuligeVerdierForHand_handInneholderEttEss_2muligeVerdier() {
        assertEquals(2, hand.getMuligeVerdierForHand().size());

        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 6));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 16));
    }

    @Test
    void getMuligeVerdierForHand_handInneholder2Ess_3muligeVerdier() {
        hand = new Hand();
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Hjerter));
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Spar));
        hand.leggTilKort(new Kort(Kort.Rank.FEM, Kort.Farge.Spar));
        assertEquals(3, hand.getMuligeVerdierForHand().size());

        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 7));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 17));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 27));
    }

    @Test
    void getMuligeVerdierForHand_handInneholder3Ess_4muligeVerdier() {
        hand = new Hand();
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Hjerter));
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Spar));
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Ruter));
        hand.leggTilKort(new Kort(Kort.Rank.FEM, Kort.Farge.Spar));
        assertEquals(4, hand.getMuligeVerdierForHand().size());

        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 8));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 18));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 28));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 38));
    }

    @Test
    void getMuligeVerdierForHand_handInneholder4Ess_4muligeVerdier() {
        hand = new Hand();
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Hjerter));
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Spar));
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Ruter));
        hand.leggTilKort(new Kort(Kort.Rank.ESS, Kort.Farge.Kløver));
        hand.leggTilKort(new Kort(Kort.Rank.FEM, Kort.Farge.Spar));
        assertEquals(5, hand.getMuligeVerdierForHand().size());

        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 9));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 19));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 29));
        assertTrue(hand.getMuligeVerdierForHand().stream().anyMatch(v -> v == 39));
    }
}