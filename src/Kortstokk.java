import java.util.*;

class Kortstokk {
    List<Kort> kortstokk = new LinkedList<>();

    public Kortstokk() {
        samleKort();
    }

    private List<Kort> lagKortstokk() {
        List<Kort> kortListe = new LinkedList<>();
        Arrays.stream(Farge.values()).forEach(f -> Arrays.stream(Rank.values()).forEach(r -> kortListe.add(new Kort(r, f))));
        return kortListe;
    }

    public void samleKort() {
        kortstokk = lagKortstokk();
    }

    public void stokkKort() {
        List<Kort> kortListe = lagKortstokk();
        Collections.shuffle(kortListe);
        kortstokk = new LinkedList<>();
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < 52; i++) {
            int index = rnd.nextInt(kortListe.size());
            Kort kort = kortListe.get(index);
            kortListe.remove(index);
            kortstokk.add(kort);
        }
    }

    public Kort trekkKort() {
        int i = new Random().nextInt(kortstokk.size());
        Kort kort = kortstokk.get(i);
        kortstokk.remove(i);
        return kort;
    }

    private void printKortstokk() {
        for (Kort k : kortstokk) {
            System.out.println(k);
        }
    }
}

