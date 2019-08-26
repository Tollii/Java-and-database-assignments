#Oving 10

# 1. Finne boretslag etablert mellom årene 1975-1985
SELECT etabl_aar FROM borettslag WHERE etabl_aar > 1975 AND etabl_aar < 1985

# 2. Skriv ut liste over andelseiere. foranvn, etternavn og ansienitet. Sortert på ansienitet
SELECT CONCAT(fornavn, " ", etternavn, " Ansiennitet: ", ansiennitet) "tab" FROM andelseier AS tab

# 3. Finn det eldste boretslaget
SELECT * FROM borettslag ORDER BY etabl_aar LIMIT 1

# 4. Finn adressene til alle bygninger med minst tre rom
SELECT DISTINCT bygn_adr FROM bygning LEFT JOIN leilighet l on bygning.bygn_id = l.bygn_id WHERE ant_rom >= 3

# 5. Finn alle bygninger i boretslaget "Tertitten"
SELECT COUNT(*) FROM bygning WHERE bolag_navn = "Tertitten";

# 6. Lag liste som viser antall bygninger i hvert enkelt borettslag. Sortert på boretslagnavn. Inkl. boretslag uten bygninger
SELECT COUNT(bygning.bolag_navn)  AS antall, borettslag.bolag_navn FROM bygning RIGHT JOIN borettslag ON (bygning.bolag_navn = borettslag.bolag_navn) GROUP BY borettslag.bolag_navn ORDER BY antall;

# 7. Finn antall leiligheter i "Tertitten"
SELECT COUNT(*) AS antall, bolag_navn FROM leilighet NATURAL JOIN bygning WHERE bolag_navn = "Tertitten"

# 8. Hvor høyt kan du bo i borettslaget "Tertitten"?
SELECT MAX(etasje), bolag_navn FROM leilighet NATURAL JOIN borettslag WHERE bolag_navn = "Tertitten"

# 9. Finn navn og nummer til andelseiere som ikke har leilighet
SELECT fornavn, etternavn, telefon FROM andelseier WHERE and_eier_nr NOT IN(SELECT and_eier_nr FROM leilighet NATURAL JOIN andelseier);

# 10. Finn antall andelseiere pr borettslag, sortert etter antall. Boretslag uten andelseiere skal være med
SELECT COUNT(andelseier.bolag_navn) AS eier, borettslag.bolag_navn FROM andelseier RIGHT JOIN borettslag on andelseier.bolag_navn = borettslag.bolag_navn GROUP BY borettslag.bolag_navn

# 11. Skriv ut en liste ove all andelseire. For de som har leilighet, skal leilighetsnummeret skrives ut.
SELECT * FROM andelseier
LEFT JOIN
(SELECT leil_nr, and_eier_nr FROM leilighet) AS leilnr ON leilnr.and_eier_nr = andelseier.and_eier_nr)

# 12. Hvilke borettslag har leiligheter med eksakt 4 rom?
SELECT bolag_navn FROM borettslag NATURAL JOIN bygning NATURAL JOIN leilighet WHERE ant_rom = 4 GROUP BY borettslag.bolag_navn

/*
13. Skriv ut en liste over antall andelseiere pr postnr og poststed, begrenset til de som bor i leiligheter tilknyttet et borettslag. 
Husk at postnummeret til disse er postnummeret til bygningen de bor i, og ikke postnummeret til borettslaget. 
Du trenger ikke ta med poststeder med 0 andelseiere. 
(Ekstraoppgave: Hva hvis vi vil ha med poststeder med 0 andelseiere?)
*/
SELECT COUNT(and_eier_nr) AS eiere, poststed.poststed, poststed.postnr FROM andelseier NATURAL JOIN leilighet NATURAL JOIN bygning NATURAL JOIN poststed GROUP BY poststed.postnr;