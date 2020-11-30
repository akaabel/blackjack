# BlackJack Spring Boot versjon
Dette er besvarelsen til øvingsoppgave nivå2 i utviklingsløpet til Decisive.

## Hvordan spille
Følgende endepunkter benyttes for å spilles spillet.

### `/start` og `/startjson` 
Starter et nytt spill. Man angir spillerens navn ved en POST operasjon.

Dette kan gjøres med JSON:
`curl -X POST localhost:8080/startjson -H 'Content-type:application/json' -d '{"spillernavn": "Alf-Kenneth Aabel"}' | jq`

Eller man kan bare POSTe navnet, slik:
`curl -X POST localhost:8080/start -H 'Content-type:application/json' -d "Alf-Kenneth Aabel" | jq`

### `/vis`
Viser spillets status.

### `/trekk`
Trekk et nytt kort. Spilleren kan trekke så lenge verdien av hånden ikke overstiger 21.
Dersom håndens verdi er mer enn 21, kastes en Exception. Da må man starte spillet på nytt.

### `/pass`
Stå og ikke trekk flere kort. Dealer spiller da sitt spill og resultatet vises.
