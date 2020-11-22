package no.aka.blackjack.domain;

/**
 * Klasse som representer en Dealer i Blackjack.
 * En dealer er veldig lik en spiller, bortsett fra at det er tilfeller hvor bare den første kortet vises.
 *
 * @see Blackjack
 * @see Spiller
 */
public class Dealer extends Spiller {
    private Kortstokk kortstokk = new Kortstokk();

    public Dealer(String navn) {
        super(navn);
    }

    public void visForsteKort() {
        System.out.println(hand.getListeAvKort().get(0));
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
}
