import java.util.LinkedList;
import java.util.List;

class Spiller {
    private String navn = null;

    enum Status {KAN_TREKKE_KORT, STOPPET, OVER21}

    private Status status = null;
    List<Kort> hand = null;

    public Spiller(String navn) {
        this.navn = navn;
        hand = new LinkedList<>();
        status = Status.KAN_TREKKE_KORT;
    }

    public void stopp() {
        status = Status.STOPPET;
        System.out.println("Stopper på: " + valgtVerdi());
    }

    public Status status() {
        return status;
    }

    public Integer valgtVerdi() {
        return verdiForHand().stream().filter(v -> v < 22).mapToInt(v -> v).max().orElse(22);
    }

    private void oppdaterStatus() {
        if (status == Status.STOPPET || status == Status.OVER21) {
            return;
        }

        if (verdiForHand().stream().filter(v -> v.intValue() < 22).findFirst().isPresent()) {
            return;
        } else {
            status = Status.OVER21;
        }
    }

    private List<Integer> verdiForHand() {
        // Summer verdiene til kortene i hånden
        int verdi = 0;
        for (Kort kort : hand) {
            verdi += kort.verdi();
        }
        List<Integer> verdier = new LinkedList<>();
        verdier.add(verdi);

        // Håndtering av tilfellene hvor en hånd inneholder Ess
        long antallEssIHand = hand.stream().filter(k -> k.rank() == Rank.ESS).count();
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

    public void visForsteKort() {
        System.out.println(hand.get(0));
    }

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