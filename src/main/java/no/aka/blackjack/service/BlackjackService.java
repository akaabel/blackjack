package no.aka.blackjack.service;

import no.aka.blackjack.domain.Dealer;
import no.aka.blackjack.domain.Kortstokk;
import no.aka.blackjack.domain.Spiller;
import no.aka.blackjack.repository.SpillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BlackjackService {
    private enum Status {SPILLER_KAN_TREKKE_KORT, UAVGJORT, DEALER_VANT, SPILLER_VANT, BLACK_JACK}

    private Kortstokk kortstokk = new Kortstokk();
    private Spiller spiller;
    private Dealer dealer;
    private Status status;
    private long spillId;

    public void setKortstokk(Kortstokk kortstokk) {
        this.kortstokk = kortstokk;
    }

    public void setSpiller(Spiller spiller) {
        this.spiller = spiller;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public void setStatus(String status) {
        this.status = Status.valueOf(status);
    }

    public void setSpillId(long spillId) {
        this.spillId = spillId;
    }

    @Autowired
    private SpillRepository spillRepository;

    public BlackjackService() {
        this.spillRepository = spillRepository;
    }

    // Brukes for å vise spillet som JSON
    public Spiller getSpiller() {
        return spiller;
    }

    // Brukes for å vise spillet som JSON
    public Dealer getDealer() {
        return dealer;
    }

    // Brukes for å vise spillet som JSON
    public Status getStatus() {
        return status;
    }

    // Brukes for å vise spillet som JSON
    public long getSpillId() {
        return spillId;
    }

    public Kortstokk getKortstokk() {
        return kortstokk;
    }

    public void start(String spillernavn) {
        this.spiller = new Spiller(spillernavn);
        dealer = new Dealer("Mr. Dealer", new Kortstokk());
        status = Status.SPILLER_KAN_TREKKE_KORT;

        this.spiller.mottaKort(dealer.delUtKort());
        this.spiller.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());
        dealer.mottaKort(dealer.delUtKort());

        if (this.spiller.besteVerdiForHand() == 21) {
            status = Status.BLACK_JACK;
        }

        spillId = spillRepository.lagreNyttSpill(this);
    }

    public void hentSpill(Long id) {
        BlackjackService blackjackServiceTmp = spillRepository.hentSpill(id);
        spillId = blackjackServiceTmp.getSpillId();
        status = blackjackServiceTmp.getStatus();
        spiller = blackjackServiceTmp.getSpiller();
        dealer = blackjackServiceTmp.getDealer();
        kortstokk = blackjackServiceTmp.getKortstokk();
    }

    public void delKortTilSpiller() {
        if (status != Status.SPILLER_KAN_TREKKE_KORT) {
            throw new IllegalStateException("Spilller har ikke lov å trekke flere kort. Tilstanden er " + status);
        }
        spiller.mottaKort(dealer.delUtKort());
        if (spiller.harMerEnn21()) {
            status = Status.DEALER_VANT;
        }
    }

    public void pass() {
        dealer.aktiverVisingAvKort();
        dealer.fullforSpill();
        if (dealer.besteVerdiForHand() > 21) {
            status = Status.SPILLER_VANT;
        } else if (spiller.besteVerdiForHand() > 21) {
            status = Status.DEALER_VANT;
        } else if (dealer.besteVerdiForHand() < spiller.besteVerdiForHand()) {
            status = Status.SPILLER_VANT;
        } else if (dealer.besteVerdiForHand().equals(spiller.besteVerdiForHand())) {
            status = Status.UAVGJORT;
        } else {
            status = Status.DEALER_VANT;
        }
    }
}
