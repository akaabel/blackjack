package no.aka.blackjack.domain;

import no.aka.blackjack.service.BlackjackService;

/**
 * Klasse som representer en Dealer i Blackjack.
 * En dealer har en {@link Kortstokk} og deler ut kort.
 * Ellers er en dealer veldig lik en {@link Spiller}, bortsett fra at det er tilfeller hvor bare det første kortet vises.
 *
 * @see BlackjackService
 * @see Spiller
 * @see Kortstokk
 */
public class Dealer extends Spiller {
    private Kortstokk kortstokk = new Kortstokk();
    private boolean skalKortSkjules = true;

    public Dealer(String navn) {
        super(navn);
    }

    @Override
    public Hand getHand() {
        if (skalKortSkjules) {
            Hand tmpHand = new Hand();
            tmpHand.leggTilKort(hand.getListeAvKort().get(0));
            return tmpHand;
        } else {
            return hand;
        }
    }

    public void startNyRunde() {
        kortstokk = new Kortstokk();
    }

    public Kort delUtKort() {
        return kortstokk.trekkKort();
    }

    public void fullforSpill() {
        while (besteVerdiForHand() < 17) {
            mottaKort(kortstokk.trekkKort());
        }
    }

    public void aktiverVisingAvKort() {
        skalKortSkjules = false;
    }
}
