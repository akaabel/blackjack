package no.aka.blackjack;

import java.util.Scanner;

/**
 * Klasse som organiserer hele {@code Blackjack} spillet.
 * Spillet har en {@code Spiller} og en {@code Dealer}.
 * Spilleren styrer spillet gjennom menyvalg.
 *
 * @see Spiller
 * @see Dealer
 */
public class Blackjack {
    Spiller spiller = new Spiller("Spiller");
    Dealer dealer = new Dealer("Dealer");

    // Mulige valg en spiller kan gjøre.
    enum Menyvalg {
        NYTT_KORT, AVSLUTT, STÅ, NY_RUNDE, VIS_HAND
    }

    public static void main(String[] args) {
        System.out.println("Velkommen til BlackJack");
        System.out.println("-----------------------");

        Blackjack blackjack = new Blackjack();
        blackjack.start();
    }

    /**
     * Funksjonen tar imot hendelser i form av valg gjort av spilleren.
     * Avhengig av valg utføres tilhørende operasjoner ved kall til underliggende funksjoner.
     * Etter hvert valg sjekkes status på spilleren.
     */
    public void start() {
        startNyRunde();
        printMeny();
        Menyvalg valg;
        do {
            valg = lesValg();
            switch (valg) {
                case NYTT_KORT:
                    spiller.mottaKort(dealer.delUtKort());
                    break;
                case STÅ:
                    System.out.println("Står");
                    spiller.stopp();
                    dealer.fullforSpill(spiller);
                    continue;
                case NY_RUNDE:
                    startNyRunde();
                    break;
                case VIS_HAND:
                    visHand();
                    break;
            }

            if (spiller.status() == Spiller.Status.OVER21) {
                System.out.println("Over 21, du tapte.\n\n");
                startNyRunde();
            } else if (spiller.status() == Spiller.Status.STOPPET) {
                System.out.println("Spiller står på: " + spiller.maxVerdiForHand() + ", dealer trekker kort...");
            }
        } while (valg != Menyvalg.AVSLUTT);

        System.out.println("Spillet er avsluttet. Velkommen tilbake en annen gang.");
        System.out.println("-----------------------");
    }

    private void visHand() {
        spiller.visHand();
        System.out.println("Viser første kort for dealer:");
        dealer.visForsteKort();
        System.out.println("Antall kort igjen i kortstokken: " + dealer.antallKortIStokken());
    }

    private void startNyRunde() {
        System.out.println("Ny runde");

        dealer.samleOgStokkKort();
        spiller = new Spiller("Spiller");
        dealer = new Dealer("Dealer");
        spiller.mottaKort(dealer.delUtKort());
        spiller.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        visHand();
        if (spiller.maxVerdiForHand().equals(21)) {
            System.out.println("Gratulerer!! Du har BlackJack og har vunnet!!!");
        }
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
        return valg.matches("[aAnNtTvVsS]");
    }

    private void printMeny() {
        System.out.println("\nVelg hva du ønsker å gjøre");
        System.out.println("(A)vslutt spillet.");
        System.out.println("(N)y runde.");
        System.out.println("(T)rekk kort.");
        System.out.println("(V)is hånd.");
        System.out.println("(S)tå.");
    }
}

