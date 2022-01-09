package no.aka.blackjack.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KortTest {

    @Test
    void testRankBenevnelser() {
        List<Kort.Farge> farger = Arrays.asList(Kort.Farge.values());
        for (Kort.Farge farge: farger) {
            assertEquals("2", new Kort(Kort.Rank.TO, farge).getRank().benevnelse);
            assertEquals("3", new Kort(Kort.Rank.TRE, farge).getRank().benevnelse);
            assertEquals("4", new Kort(Kort.Rank.FIRE, farge).getRank().benevnelse);
            assertEquals("5", new Kort(Kort.Rank.FEM, farge).getRank().benevnelse);
            assertEquals("6", new Kort(Kort.Rank.SEKS, farge).getRank().benevnelse);
            assertEquals("7", new Kort(Kort.Rank.SYV, farge).getRank().benevnelse);
            assertEquals("8", new Kort(Kort.Rank.ÅTTE, farge).getRank().benevnelse);
            assertEquals("9", new Kort(Kort.Rank.NI, farge).getRank().benevnelse);
            assertEquals("10", new Kort(Kort.Rank.TI, farge).getRank().benevnelse);
            assertEquals("Kn", new Kort(Kort.Rank.KNEKT, farge).getRank().benevnelse);
            assertEquals("D", new Kort(Kort.Rank.DAME, farge).getRank().benevnelse);
            assertEquals("K", new Kort(Kort.Rank.KONGE, farge).getRank().benevnelse);
            assertEquals("E", new Kort(Kort.Rank.ESS, farge).getRank().benevnelse);
        }
    }

    @Test
    void testRankVerdier() {
        List<Kort.Farge> farger = Arrays.asList(Kort.Farge.values());
        for (Kort.Farge farge: farger) {
            assertEquals(2, new Kort(Kort.Rank.TO, farge).getRank().verdi);
            assertEquals(3, new Kort(Kort.Rank.TRE, farge).getRank().verdi);
            assertEquals(4, new Kort(Kort.Rank.FIRE, farge).getRank().verdi);
            assertEquals(5, new Kort(Kort.Rank.FEM, farge).getRank().verdi);
            assertEquals(6, new Kort(Kort.Rank.SEKS, farge).getRank().verdi);
            assertEquals(7, new Kort(Kort.Rank.SYV, farge).getRank().verdi);
            assertEquals(8, new Kort(Kort.Rank.ÅTTE, farge).getRank().verdi);
            assertEquals(9, new Kort(Kort.Rank.NI, farge).getRank().verdi);
            assertEquals(10, new Kort(Kort.Rank.TI, farge).getRank().verdi);
            assertEquals(10, new Kort(Kort.Rank.KNEKT, farge).getRank().verdi);
            assertEquals(10, new Kort(Kort.Rank.DAME, farge).getRank().verdi);
            assertEquals(10, new Kort(Kort.Rank.KONGE, farge).getRank().verdi);
            assertEquals(11, new Kort(Kort.Rank.ESS, farge).getRank().verdi);
        }
    }
}