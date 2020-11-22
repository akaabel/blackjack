package no.aka.blackjack.service;

import no.aka.blackjack.domain.Dealer;
import no.aka.blackjack.domain.Spiller;
import org.springframework.stereotype.Service;

@Service
public class BlackjackService {
    private enum Status {SPILLER_KAN_TREKKE_KORT, UAVGJORT, DEALER_VANT, SPILLER_VANT, BLACK_JACK}

    private Spiller spiller;
    private Dealer dealer;
    private Status status;

    public BlackjackService() {
        start();
    }

    public Spiller getSpiller() {
        return spiller;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Status getStatus() {
        return status;
    }

    public void start() {
        spiller = new Spiller("AKA");
        dealer = new Dealer("Mr. Dealer");
        status = Status.SPILLER_KAN_TREKKE_KORT;

        dealer.samleOgStokkKort();
        spiller.mottaKort(dealer.delUtKort());
        spiller.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());

        if (spiller.besteVerdiForHand() == 21) {
            status = Status.BLACK_JACK;
        }
    }

    public void delKortTilSpiller() {
        // TODO Må sjekke status om spiller har lov til å motta kort eller om spillet er avsluttet
        // Kast exception om det skjer. Denne må returneres via Rest controlleren
        spiller.mottaKort(dealer.delUtKort());
    }

    public void pass() {
        dealer.fullforSpill(spiller);
        if (dealer.besteVerdiForHand() > 21) {
            status = Status.SPILLER_VANT;
        } else if (spiller.besteVerdiForHand() > 21) {
            status = Status.DEALER_VANT;
        } else if (dealer.besteVerdiForHand() < spiller.besteVerdiForHand()) {
            status = Status.SPILLER_VANT;
        } else if (dealer.besteVerdiForHand() == spiller.besteVerdiForHand()) {
            status = Status.UAVGJORT;
        } else {
            status = Status.DEALER_VANT;
        }
    }
}
