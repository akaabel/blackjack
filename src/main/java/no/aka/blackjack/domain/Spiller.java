package no.aka.blackjack.domain;

/**
 * Klasse som holder på kortene en spiller har
 * og tilbyr operasjoner som en spiller kan gjøre.
 *
 * @see Kort
 */
public class Spiller {
    private final String navn;

    public enum Status {KAN_TREKKE_KORT, STOPPET, OVER21}

//    private Status status;
    protected Hand hand;

    public Spiller(String navn) {
        this.navn = navn;
        hand = new Hand();
//        status = Status.KAN_TREKKE_KORT;
    }

    public void stopp() {
//        status = Status.STOPPET;
//        System.out.println("Stopper på: " + maxVerdiForHand());
    }
//    public Status getStatus() {
//        return status;
//    }

//    // TODO Kan denne slettes?
//    private void oppdaterStatus() {
//        if (status == Status.STOPPET || status == Status.OVER21) {
//            return;
//        }
//
//        if (hand.getMuligeVerdierForHand().stream().noneMatch(v -> v < 22)) {
//            status = Status.OVER21;
//        }
//    }

    public void mottaKort(Kort kort) {
        hand.leggTilKort(kort);
//        oppdaterStatus();
    }

//    /**
//     * Skriver ut kortene i en hånden til spilleren og de mulige verdiene en hånd kan ha.
//     */
//    public void visHand() {
//        System.out.println("Viser hand for " + navn);
//        for (Kort kort : hand) {
//            System.out.println("\t" + kort);
//        }
//        System.out.println("\tHåndens verdi er en av følgende: ");
//        for (Integer i : verdiForHand()) {
//            System.out.println("\t* " + i);
//        }
//        System.out.println("\tStatus: " + status);
//    }

    public String getNavn() {
        return navn;
    }

    public Hand getHand() {
        return hand;
    }

    /**
     * @return verdien den høyeste verdien en hånd har som samtidig er lavere enn 22.
     * Dersom det ikke er noen verdier lavere enn 22, returneres Integer.MAX_VALUE
     */
    public Integer besteVerdiForHand() {
        return hand.getMuligeVerdierForHand().stream().filter(v -> v < 22).mapToInt(v -> v).max().orElse(Integer.MAX_VALUE);
    }
}