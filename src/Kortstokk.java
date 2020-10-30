import java.util.*;

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

    public Kort trekkKort() {
        Kort kort = kortstokk.get(0);
        kortstokk.remove(0);
        return kort;
    }
}

