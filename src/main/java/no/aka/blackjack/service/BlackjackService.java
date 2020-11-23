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
        start(new Spiller("DefaultNavn"));
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

    public void start(Spiller spiller) {
        this.spiller = new Spiller(spiller.getNavn());
        dealer = new Dealer("Mr. Dealer");
        status = Status.SPILLER_KAN_TREKKE_KORT;

        dealer.samleOgStokkKort();
        this.spiller.mottaKort(dealer.delUtKort());
        this.spiller.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());

        if (this.spiller.besteVerdiForHand() == 21) {
            status = Status.BLACK_JACK;
        }
    }

    public void delKortTilSpiller() {
        if (status != Status.SPILLER_KAN_TREKKE_KORT) {
            throw new IllegalStateException("Spilller har ikke lov å trekke flere kort. Tilstanden er " + status);
        }
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
