import java.util.*;

/**
 * Klasse som representerer en kortstokk med 52 ulike kort.
 * Kortstokken representerer ved en list av {@code Kort}.
 *
 * Når man lager en ny kortstokk blir den stokket.
 *
 * Det er mulig å trekke et {@code Kort} fra kortstokken.
 */
class Kortstokk {
    List<Kort> kortstokk;

    public Kortstokk() {
        kortstokk = opprettStokketKortstokk();
    }

    private List<Kort> opprettStokketKortstokk() {
        List<Kort> kortListe = new LinkedList<>();
        Arrays.stream(Farge.values()).forEach(f -> Arrays.stream(Rank.values()).forEach(r -> kortListe.add(new Kort(r, f))));
        Collections.shuffle(kortListe);
        return kortListe;
    }

    /**
     * Returnerer det øverste (første) kortet i kortstokken og
     * fjerner det fra kortstokken.
     *
     * @return {@link Kort} Det øverste (første) kortet i kortstokken
     */
    public Kort trekkKort() {
        Kort kort = kortstokk.get(0);
        kortstokk.remove(0);
        return kort;
    }
}

