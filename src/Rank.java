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
