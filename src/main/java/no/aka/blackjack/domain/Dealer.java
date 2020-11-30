package no.aka.blackjack.domain;

import no.aka.blackjack.service.BlackjackService;

/**
 * Klasse som representer en Dealer i Blackjack.
 * En dealer er veldig lik en spiller, bortsett fra at det er tilfeller hvor bare den første kortet vises.
 *
 * @see BlackjackService
 * @see Spiller
 */
public class Dealer extends Spiller {
    private Kortstokk kortstokk = new Kortstokk();
    private boolean skalKortSkjules = true;

    public Dealer(String navn) {
        super(navn);
    }

    public Hand getHand() {
        if (skalKortSkjules) {
            Hand tmpHand = new Hand();
            tmpHand.leggTilKort(hand.getListeAvKort().get(0));
            return tmpHand;
        } else {
            return hand;
        }
    }

    public void samleOgStokkKort() {
        kortstokk = new Kortstokk();
    }

    public Kort delUtKort() {
        return kortstokk.trekkKort();
    }

    public int getAntallKortIgjenIStokken() {
        return kortstokk.getAntallKortIgjenIKortstokk();
    }

    public void fullforSpill(Spiller spiller) {
        while (besteVerdiForHand() < 17) {
            mottaKort(kortstokk.trekkKort());
        }
    }

    public void aktiverVisingAvKort() {
        skalKortSkjules = false;
    }
}
