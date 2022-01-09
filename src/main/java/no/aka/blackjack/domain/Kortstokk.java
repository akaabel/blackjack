package no.aka.blackjack.domain;

import java.util.*;

/**
 * Klasse som representerer en kortstokk med 52 ulike kort.
 * Kortstokken representerer ved en list av {@code Kort}.
 *
 * Når man lager en ny kortstokk blir den stokket.
 *
 * Det er mulig å trekke et {@code Kort} fra kortstokken.
 */
public class Kortstokk {
    private List<Kort> listeAvKort;

    public Kortstokk() {
        listeAvKort = opprettStokketKortstokk();
    }

    public int antallKortIgjenIKortstokk() {
        return listeAvKort.size();
    }

    /**
     * Returnerer det øverste (første) kortet i kortstokken og
     * fjerner det fra kortstokken.
     *
     * @return {@link Kort} Det øverste (første) kortet i kortstokken
     */
    public Kort trekkKort() {
        Kort kort = listeAvKort.get(0);
        listeAvKort.remove(0);
        return kort;
    }

    private List<Kort> opprettStokketKortstokk() {
        List<Kort> kortListe = new LinkedList<>();
        Arrays.stream(Kort.Farge.values()).forEach(f -> Arrays.stream(Kort.Rank.values()).forEach(r -> kortListe.add(new Kort(r, f))));
        Collections.shuffle(kortListe);
        return kortListe;
    }
}

