package no.aka.blackjack.domain;

/**
 * Klasse som holder på kortene en spiller har
 * og tilbyr operasjoner som en spiller kan gjøre.
 *
 * @see Kort
 */
public class Spiller {
    private final String navn;

    protected Hand hand;

    public Spiller(String navn) {
        this.navn = navn;
        hand = new Hand();
    }

    public boolean harMerEnn21() {
        return besteVerdiForHand() > 21;
    }

    public void mottaKort(Kort kort) {
        hand.leggTilKort(kort);
    }

    public String getNavn() {
        return navn;
    }

    public Hand getHand() {
        return hand;
    }

    /**
     * @return den høyeste verdien en hånd som er lavere enn 22.
     * Dersom det ikke er noen verdier lavere enn 22, returneres Integer.MAX_VALUE
     */
    public Integer besteVerdiForHand() {
        return hand.getMuligeVerdierForHand().stream().filter(v -> v < 22).mapToInt(v -> v).max().orElse(Integer.MAX_VALUE);
    }
}