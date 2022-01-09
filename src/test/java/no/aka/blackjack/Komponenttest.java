package no.aka.blackjack;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


/**
 * Formålet med denne klassen er å teste komponenten ved å teste HTTP endepunktene.
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class Komponenttest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private ObjectMapper objectMapper = new ObjectMapper();

    /*
        Gitt at spillernavn er lik Aka
        Når nytt spill starter
        Så skal status være at spiller kan trekke kort
        Og spiller skal ha 2 kort på handa
        Og dealer skal ha et kort på handa (som er synlig)
     */
    @Test
    public void start() throws Exception {
        String spillernavn = "Aka";
        String jsonStr = this.restTemplate.postForObject("http://localhost:" + port + "/start",
                spillernavn, String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        assertEquals(spillernavn, jsonNode.get("spiller").get("navn").textValue());
        assertEquals("SPILLER_KAN_TREKKE_KORT", jsonNode.get("status").textValue());
        assertEquals(2, jsonNode.get("spiller").get("hand").get("listeAvKort").size());
        assertEquals(1, jsonNode.get("dealer").get("hand").get("listeAvKort").size());
    }

    /*
        Gitt nytt spill er startet
        Når spiller velger å vis spillet
        Så spillet være likt som da spillet startet
     */
    @Test
    public void vis() throws Exception {
        String spillernavn = "Aka";
        String jsonStrStart = this.restTemplate.postForObject("http://localhost:" + port + "/start",
                spillernavn, String.class);
        String jsonStrVis = this.restTemplate.getForObject("http://localhost:" + port + "/vis",
                String.class);
        assertEquals(jsonStrStart, jsonStrVis);
    }

    /*
        Gitt nytt spill er startet og status er at spiller kan trekke kort
        Når spiller kan trekker kort
        Så spillet være likt som da spillet startet
     */
    @Test
    public void trekk() throws Exception {
        String spillernavn = "Aka";
        String jsonStrStart = this.restTemplate.postForObject("http://localhost:" + port + "/start",
                spillernavn, String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonStrStart);

        // Fortsetter å starte spill inntil spiller kan trekke et kort
        while (!jsonNode.get("status").textValue().equals("SPILLER_KAN_TREKKE_KORT")) {
            jsonStrStart = this.restTemplate.postForObject("http://localhost:" + port + "/start",
                    spillernavn, String.class);
            jsonNode = objectMapper.readTree(jsonStrStart);
        }

        String jsonStrTrekk = this.restTemplate.getForObject("http://localhost:" + port + "/trekk",
                String.class);
        JsonNode jsonNodeTrekk = objectMapper.readTree(jsonStrTrekk);
        assertEquals(3, jsonNodeTrekk.get("spiller").get("hand").get("listeAvKort").size());
    }

    /*
        Gitt nytt spill er startet
        Når spiller velger å passe
        Så skal status på spillet være ulikt SPILLER_KAN_TREKKE_KORT
     */
    @Test
    public void pass() throws Exception {
        String spillernavn = "Aka";
        this.restTemplate.postForObject("http://localhost:" + port + "/start",
                spillernavn, String.class);
        String jsonStrPass = this.restTemplate.getForObject("http://localhost:" + port + "/pass",
                String.class);
        JsonNode jsonNode = objectMapper.readTree(jsonStrPass);
        assertNotEquals("SPILLER_KAN_TREKKE_KORT", jsonNode.get("status").textValue());
    }
}