package no.aka.blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Tester ikke getters: getHand() og getNavn()
class SpillerTest {
    private Spiller spiller = new Spiller("Testnavn");

    @Test
    void mottaKort() {
        assertEquals(0, spiller.getHand().getListeAvKort().size());
        Kort hjerterNi = new Kort(Kort.Rank.NI, Kort.Farge.Hjerter);
        spiller.mottaKort(hjerterNi);
        assertTrue(spiller.getHand().getListeAvKort().stream().anyMatch(k ->
                k.getFarge().equals(hjerterNi.getFarge())
                && k.getRank().equals(hjerterNi.getRank())));
        assertEquals(1, spiller.getHand().getListeAvKort().size());

        Kort sparDame = new Kort(Kort.Rank.DAME, Kort.Farge.Spar);
        spiller.mottaKort(sparDame);
        assertTrue(spiller.getHand().getListeAvKort().stream().anyMatch(k ->
                k.getFarge().equals(sparDame.getFarge())
                        && k.getRank().equals(sparDame.getRank())));
        assertEquals(2, spiller.getHand().getListeAvKort().size());
    }

    @Test
    void besteVerdiForHand_ettKortPaaHaandenMedVerdi3_Svar3() {
        spiller.mottaKort(new Kort(Kort.Rank.TRE, Kort.Farge.Hjerter));
        assertEquals(3, spiller.besteVerdiForHand());
    }

    @Test
    void besteVerdiForHand_toKortPaaHaandenMedSum13_Svar13() {
        spiller.mottaKort(new Kort(Kort.Rank.NI, Kort.Farge.Hjerter));
        spiller.mottaKort(new Kort(Kort.Rank.FIRE, Kort.Farge.Hjerter));
        assertEquals(13, spiller.besteVerdiForHand());
    }

    @Test
    void besteVerdiForHand_toKortPaaHaandenMedBSum21_Svar21() {
        spiller.mottaKort(new Kort(Kort.Rank.ESS, Kort.Farge.Hjerter));
        spiller.mottaKort(new Kort(Kort.Rank.KNEKT, Kort.Farge.Hjerter));
        assertEquals(21, spiller.besteVerdiForHand());
    }

    @Test
    void besteVerdiForHand_verdiMerEnn21_SvarIntegerMaxValue() {
        spiller.mottaKort(new Kort(Kort.Rank.TRE, Kort.Farge.Hjerter));
        spiller.mottaKort(new Kort(Kort.Rank.NI, Kort.Farge.Hjerter));
        spiller.mottaKort(new Kort(Kort.Rank.KNEKT, Kort.Farge.Hjerter));
        assertEquals(Integer.MAX_VALUE, spiller.besteVerdiForHand());
    }

    /*
        Gitt 3 kort på hånda med to summer, 21 og 31
        Når beste verdi skal beregnes
        Så skal verdien 21 returneres.
     */
    @Test
    void besteVerdiForHand_verdiFlereMuligeVerdier() {
        spiller.mottaKort(new Kort(Kort.Rank.ESS, Kort.Farge.Hjerter));
        spiller.mottaKort(new Kort(Kort.Rank.TI, Kort.Farge.Hjerter));
        spiller.mottaKort(new Kort(Kort.Rank.KNEKT, Kort.Farge.Spar));
        assertEquals(21, spiller.besteVerdiForHand());
    }

}