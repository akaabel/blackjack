public class Kort {
    private final Rank rank;
    private final Farge farge;

    public Kort(Rank rank, Farge farge) {
        this.rank = rank;
        this.farge = farge;
    }

    public int verdi() {
        return rank.verdi;
    }

    public Rank rank() {
        return rank;
    }

    public String toString() {
        return rank.benevnelse + "-" + farge.name();
    }
}
