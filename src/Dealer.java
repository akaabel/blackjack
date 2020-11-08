/**
 * Klasse som representer en Dealer i Blackjack.
 * En dealer er veldig lik en spiller, bortsett fra at det er tilfeller hvor bare den første kortet vises.
 *
 * @see Blackjack
 * @see Spiller
 */
public class Dealer extends Spiller {
    public Dealer(String navn) {
        super(navn);
    }

    public void visForsteKort() {
        System.out.println(hand.get(0));
    }
}
