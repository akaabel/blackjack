package no.aka.blackjack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    private Dealer dealer = new Dealer("DealerNavn", new Kortstokk());

    @BeforeEach
    private void setup() {
        new Dealer("DealerNavn", new Kortstokk());
    }

    @Test
    /**
     * Tester at man mottar et kort.
     */
    void delUtKort_kortBlirMottatt() {
        Kort kort = dealer.delUtKort();
        assertNotNull(kort);
    }

    @Test
    void startNyRunde_kortstokkHar52Kort() {
        for (int i = 0; i < 52; i++) {
            dealer.delUtKort();
        }
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            dealer.delUtKort();
        });
    }

    @Test
    void getHand_HarSkjulteKort() {
        dealer.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        assertEquals(1, dealer.getHand().getListeAvKort().size());
    }

    @Test
    void aktiverVisningAvKort_IngenSkjulteKort() {
        dealer.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        dealer.aktiverVisingAvKort();
        assertEquals(3, dealer.getHand().getListeAvKort().size());
    }

    @Test
    /**
     * Gitt at dealer har 2 kort på hånda med verdi mindre enn 17
     * Når spillet skal fullføres
     * Så skal dealer motta flere kort.
     */
    void fullforSpill_2kortMedVerdiLik16_mottarMinstEttKort() {
        dealer.mottaKort(new Kort(Kort.Rank.NI, Kort.Farge.Hjerter));
        dealer.mottaKort(new Kort(Kort.Rank.SYV, Kort.Farge.Hjerter));
        dealer.fullforSpill();
        dealer.aktiverVisingAvKort();
        assertTrue(dealer.getHand().getListeAvKort().size() > 2, "Forventet mer enn 2 kort i hånden til Dealer.");
    }

    @Test
    /**
     * Gitt at dealer har 2 kort på hånda med verdi lik 17
     * Når spillet skal fullføres
     * Så skal dealer ikke motta flere kort.
     */
    void fullforSpill_2kortMedVerdiLik17_mottarIngenKort() {
        dealer.mottaKort(new Kort(Kort.Rank.NI, Kort.Farge.Hjerter));
        dealer.mottaKort(new Kort(Kort.Rank.ÅTTE, Kort.Farge.Hjerter));
        dealer.fullforSpill();
        dealer.aktiverVisingAvKort();
        assertEquals(2, dealer.getHand().getListeAvKort().size());
    }
}