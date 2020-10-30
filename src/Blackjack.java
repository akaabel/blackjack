import java.util.Scanner;

public class Blackjack {
    Spiller spiller = new Spiller("Spiller");
    Spiller dealer = new Spiller("Dealer");
    Kortstokk kortstokk = new Kortstokk();


    enum Menyvalg {
        NYTT_KORT, AVSLUTT, STÅ, NY_RUNDE, VIS_HAND
    }

    public static void main(String[] args) {
        System.out.println("Velkommen til BlackJack");
        System.out.println("-----------------------");

        Blackjack blackjack = new Blackjack();
        blackjack.start();
    }

    public void start() {
        startNyRunde();
        printMeny();
        Menyvalg valg;
        do {
            valg = lesValg();
            switch (valg) {
                case NYTT_KORT:
                    trekkKort(spiller);
                    break;
                case STÅ:
                    stoppTrekkAvKort();
                    continue;
                case NY_RUNDE:
                    startNyRunde();
                    break;
                case VIS_HAND:
                    visHand();
                    break;
            }

            if (spiller.status() == Spiller.Status.OVER21) {
                System.out.println("Over 21, du tapte.");
            } else if (spiller.status() == Spiller.Status.STOPPET) {
                System.out.println("Spiller står på: " + spiller.valgtVerdi() + ", dealer trekker kort...");
            }
        } while (valg != Menyvalg.AVSLUTT);

        System.out.println("Spillet er avsluttet. Velkommen tilbake en annen gang.");
        System.out.println("-----------------------");
    }

    private void visHand() {
        spiller.visHand();
        System.out.println("Viser første kort for dealer:");
        dealer.visForsteKort();
    }

    private void startNyRunde() {
        System.out.println("Ny runde");
        kortstokk = new Kortstokk();
        spiller = new Spiller("Spiller");
        dealer = new Spiller("Dealer");
        trekkKort(spiller);
        trekkKort(spiller);
        trekkKort(dealer);
        trekkKort(dealer);
    }

    private void stoppTrekkAvKort() {
        System.out.println("Står");
        spiller.stopp();

        dealer.visHand();
        while (dealer.valgtVerdi() < 17) {
            dealer.mottaKort(kortstokk.trekkKort());
            dealer.visHand();
        }
        if (dealer.valgtVerdi() > 21) {
            System.out.println("Dealer røk over 21. DU VANT!!!");
        } else if (dealer.valgtVerdi() == spiller.valgtVerdi()) {
            System.out.println("Uavgjort!!");
        } else if (dealer.valgtVerdi() > spiller.valgtVerdi()) {
            System.out.println("Dealer vant!!");
        } else {
            System.out.println("DU VANT");
        }
    }

    private void trekkKort(Spiller spiller) {
        Kort kort = kortstokk.trekkKort();
        spiller.mottaKort(kort);
    }

    private Menyvalg lesValg() {
        Scanner scanner = new Scanner(System.in);
        String valg = scanner.nextLine();
        while (!lovligValg(valg)) {
            System.out.println("Valg må være en av A, N, T, V, S");
            valg = scanner.nextLine();
        }
        if (valg.equalsIgnoreCase("A")) {
            return Menyvalg.AVSLUTT;
        } else if (valg.equalsIgnoreCase("N")) {
            return Menyvalg.NY_RUNDE;
        } else if (valg.equalsIgnoreCase("T")) {
            return Menyvalg.NYTT_KORT;
        } else if (valg.equalsIgnoreCase("V")) {
            return Menyvalg.VIS_HAND;
        } else if (valg.equalsIgnoreCase("S")) {
            return Menyvalg.STÅ;
        } else {
            throw new IllegalArgumentException("Feil menyvalg");
        }
    }

    private boolean lovligValg(String valg) {
        return valg.matches("a|A|n|N|t|T|v|V|s|S");
    }

    private void printMeny() {
        System.out.println("Velg hva du ønsker å gjøre");
        System.out.println("(A)vslutt spillet.");
        System.out.println("(N)y runde.");
        System.out.println("(T)rekk kort.");
        System.out.println("(V)is hånd.");
        System.out.println("(S)tå.");
    }
}

