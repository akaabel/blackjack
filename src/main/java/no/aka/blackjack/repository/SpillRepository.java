package no.aka.blackjack.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import no.aka.blackjack.domain.Dealer;
import no.aka.blackjack.domain.Kortstokk;
import no.aka.blackjack.domain.Spiller;
import no.aka.blackjack.service.BlackjackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.Map;

@Component
public class SpillRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Kan ikke serialisere data: " + obj.toString(), e);
        }
    }

    public long lagreNyttSpill(BlackjackService blackjackService) {
        final String INSERT_SPILL_SQL = "INSERT INTO spill (status, spiller, dealer, kortstokk) VALUES (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(INSERT_SPILL_SQL, new String[] {"id"});
            ps.setString(1, String.valueOf(blackjackService.getStatus()));
            ps.setString(2, toJson(blackjackService.getSpiller()));
            ps.setString(3, toJson(blackjackService.getDealer()));
            ps.setString(4, toJson(blackjackService.getKortstokk()));
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public BlackjackService hentSpill(Long id) {
        Map<String, Object> resultMap = jdbcTemplate.queryForMap("SELECT * FROM spill WHERE id = ?", id);
        return toBlackjackService((Long) resultMap.get("id"),
                (String) resultMap.get("spiller"),
                (String) resultMap.get("dealer"),
                (String) resultMap.get("kortstokk"),
                (String) resultMap.get("status"));
    }

    private BlackjackService toBlackjackService(
            long spillId, String spiller, String dealer, String kortstokk, String status) {
        BlackjackService blackjackService = new BlackjackService();
        blackjackService.setSpillId(spillId);
        blackjackService.setStatus(status);
        try {
            blackjackService.setSpiller(objectMapper.readValue(spiller, Spiller.class));
            blackjackService.setDealer(objectMapper.readValue(dealer, Dealer.class));
            blackjackService.setKortstokk(objectMapper.readValue(kortstokk, Kortstokk.class));
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Kan ikke transformere persistert spill.", e);
        }
        return blackjackService;
    }

    public String hentSpiller(Long spillId) {
        return null;
    }

    public String hentDealer(Long spillId) {
        return null;
    }
}
