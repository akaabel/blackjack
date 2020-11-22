package no.aka.blackjack.domain;

import java.util.LinkedList;
import java.util.List;

public class Hand {
    private List<Kort> listeAvKort = new LinkedList<>();
    private List<Integer> verdiForHand = new LinkedList<>();

    public List<Kort> getListeAvKort() {
        return listeAvKort;
    }

    public void leggTilKort(Kort kort) {
        listeAvKort.add(kort);
    }

    /**
     * Beregner verdien til en hånd. En hånd kan ha flere verdier siden Ess kan ha verdien 1 eller 11.
     * @return En liste av verdier som en hånd har.
     */
    public List<Integer> getMuligeVerdierForHand() {
        // Summer verdiene til kortene i hånden
        int verdi = 0;
        for (Kort kort : listeAvKort) {
            verdi += kort.verdi();
        }
        List<Integer> verdier = new LinkedList<>();
        verdier.add(verdi);

        // Håndtering av tilfellene hvor en hånd inneholder Ess
        long antallEssIHand = listeAvKort.stream().filter(k -> k.getRank() == Kort.Rank.ESS).count();
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
            verdier.add(verdi - 44 + 11 + 1 + 1 + 1);
            verdier.add(verdi - 44 + 11 + 11 + 1 + 1);
            verdier.add(verdi - 44 + 11 + 11 + 11 + 1);
        }
        return verdier;
    }
}
