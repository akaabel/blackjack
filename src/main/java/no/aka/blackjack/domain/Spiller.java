package no.aka.blackjack.domain;

/**
 * Klasse som holder på kortene en spiller har
 * og tilbyr operasjoner som en spiller kan gjøre.
 *
 * @see Kort
 */
public class Spiller {
    private String navn;
    protected Hand hand;

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public Spiller(String navn) {
        this.navn = navn;
        hand = new Hand();
    }

    public Spiller(String navn, Hand hand) {
        this.navn = navn;
        this.hand = hand;
    }

    public String getNavn() {
        return navn;
    }

    public boolean harMerEnn21() {
        return besteVerdiForHand() > 21;
    }

    public void mottaKort(Kort kort) {
        hand.leggTilKort(kort);
    }

    // Brukes for å vise status som JSON
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