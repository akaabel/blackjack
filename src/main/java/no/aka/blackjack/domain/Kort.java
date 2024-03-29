package no.aka.blackjack.domain;

public class Kort {

    enum Farge {
        Spar("\u2660"),
        Hjerter("\u2665"),
        Ruter("\u2666"),
        Kløver("\u2663");

        public final String unicodeSymbol;

        Farge(String unicodeSymbol) {
            this.unicodeSymbol = unicodeSymbol;
        }
    }

    enum Rank {
        TO(2, "2"),
        TRE(3, "3"),
        FIRE(4, "4"),
        FEM(5, "5"),
        SEKS(6, "6"),
        SYV(7, "7"),
        ÅTTE(8, "8"),
        NI(9, "9"),
        TI(10, "10"),
        KNEKT(10, "Kn"),
        DAME(10, "D"),
        KONGE(10, "K"),
        ESS(11, "E");
        public final int verdi;
        public final String benevnelse;

        Rank(int verdi, String benevnelse) {
            this.verdi = verdi;
            this.benevnelse = benevnelse;
        }
    }

    private final Farge farge;
    private final Rank rank;

    public Kort(Rank rank, Farge farge) {
        this.rank = rank;
        this.farge = farge;
    }

    public String getFarge() {
        return farge.unicodeSymbol + " " + farge.name();
    }

    public Rank getRank() {
        return rank;
    }

    public int verdi() {
        return rank.verdi;
    }
}
