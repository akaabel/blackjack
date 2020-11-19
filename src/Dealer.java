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
        System.out.println(hand.get(0));
    }

    public void samleOgStokkKort() {
        kortstokk = new Kortstokk();
    }

    public Kort delUtKort() {
        return kortstokk.trekkKort();
    }

    public int antallKortIStokken() {
        return kortstokk.kortstokk.size();
    }

    public void fullforSpill(Spiller spiller) {
        visHand();
        while (maxVerdiForHand() < 17) {
            Kort kort = delUtKort();
            System.out.println("Dealer trekker kortet: " + kort);
            mottaKort(kort);
            visHand();
        }
        if (maxVerdiForHand() > 21) {
            System.out.println("Dealer røk over 21. DU VANT!!!");
        } else if (maxVerdiForHand().equals(spiller.maxVerdiForHand())) {
            System.out.println("Uavgjort!!");
        } else if (maxVerdiForHand() > spiller.maxVerdiForHand()) {
            System.out.println("Dealer vant!!");
        } else {
            System.out.println("DU VANT");
        }
    }
}
