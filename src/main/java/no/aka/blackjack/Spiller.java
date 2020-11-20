package no.aka.blackjack;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasse som holder på kortene en spiller har
 * og tilbyr operasjoner som en spiller kan gjøre.
 *
 * @see Kort
 */
class Spiller {
    private final String navn;

    enum Status {KAN_TREKKE_KORT, STOPPET, OVER21}

    private Status status;
    List<Kort> hand;

    public Spiller(String navn) {
        this.navn = navn;
        hand = new LinkedList<>();
        status = Status.KAN_TREKKE_KORT;
    }

    public void stopp() {
        status = Status.STOPPET;
        System.out.println("Stopper på: " + maxVerdiForHand());
    }

    public Status status() {
        return status;
    }

    /**
     * @return verdien en hånd har dersom den har en eller flere verdier lavere enn 22, eller returneres 22
     */
    public Integer maxVerdiForHand() {
        return verdiForHand().stream().filter(v -> v < 22).mapToInt(v -> v).max().orElse(22);
    }

    private void oppdaterStatus() {
        if (status == Status.STOPPET || status == Status.OVER21) {
            return;
        }

        if (verdiForHand().stream().noneMatch(v -> v < 22)) {
            status = Status.OVER21;
        }
    }

    /**
     * Beregner verdien til en hånd. En hånd kan ha flere verdier siden Ess kan ha verdien 1 eller 11.
     * @return En liste av verdier som en hånd har.
     */
    private List<Integer> verdiForHand() {
        // Summer verdiene til kortene i hånden
        int verdi = 0;
        for (Kort kort : hand) {
            verdi += kort.verdi();
        }
        List<Integer> verdier = new LinkedList<>();
        verdier.add(verdi);

        // Håndtering av tilfellene hvor en hånd inneholder Ess
        long antallEssIHand = hand.stream().filter(k -> k.rank() == Kort.Rank.ESS).count();
        if (antallEssIHand == 1) {
            verdier.add(verdi - 11 + 1);
        } else if (antallEssIHand == 2) {
            verdier.add(verdi - 22 + 1 + 1);
            verdier.add(verdi - 22 + 11 + 1);
        } else if (antallEssIHand == 3) {
            verdier.add(verdi - 33 + 1 + 1 + 1);
            verdier.add(verdi - 33 + 11 + 1 + 1);
            verdier.add(verdi - 33 + 11 + 11 + 1);
        } else if (antallEssIHand == 4) {
            verdier.add(verdi - 44 + 1 + 1 + 1 + 1);
            verdier.add(verdi - 44 + 11 + 11 + 1 + 1);
            verdier.add(verdi - 44 + 11 + 11 + 11 + 1);
        }
        return verdier;
    }

    public void mottaKort(Kort kort) {
        hand.add(kort);
        oppdaterStatus();
    }

    /**
     * Skriver ut kortene i en hånden til spilleren og de mulige verdiene en hånd kan ha.
     */
    public void visHand() {
        System.out.println("Viser hand for " + navn);
        for (Kort kort : hand) {
            System.out.println("\t" + kort);
        }
        System.out.println("\tHåndens verdi er en av følgende: ");
        for (Integer i : verdiForHand()) {
            System.out.println("\t* " + i);
        }
        System.out.println("\tStatus: " + status);
    }

}