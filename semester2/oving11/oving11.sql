#Øving 11

# 1a. List ut all infromasjon (ordrehode, ordredetalj) om leverandøren nr 44
SELECT * FROM ordredetalj LEFT JOIN ordrehode on ordredetalj.ordrenr = ordrehode.ordrenr WHERE ordrehode.levnr = 44;


# 1b. Finn navn og by på leverandøren som leverer del 1
SELECT DISTINCT navn, levby, delnr FROM levinfo NATURAL JOIN ordrehode NATURAL JOIN ordredetalj WHERE ordredetalj.delnr = 1;


# 1c. Finn nummer, navn og pris for den leverandør som kan levere del nummer 201 til billigst pris.
SELECT levinfo.levnr, navn, prisinfo.pris FROM levinfo LEFT JOIN prisinfo ON levinfo.levnr = prisinfo.levnr WHERE prisinfo.delnr = 201
ORDER BY pris LIMIT 1;


# 1d. Lag fullstendig oversikt over ordre nr 16, med ordrenr, dato, delnr, beskrivelse, kvantum, (enhets-)pris og beregnet beløp (=pris*kvantum).
SELECT DISTINCT ordredetalj.ordrenr, ordrehode.dato, ordredetalj.delnr, beskrivelse, kvantum, pris, pris*kvantum "beregnet belop"
FROM ordredetalj
NATURAL JOIN ordrehode NATURAL JOIN prisinfo NATURAL JOIN delinfo NATURAL JOIN levinfo WHERE ordredetalj.ordrenr = 16;


# 1e. Finn delnummer og leverandørnummer for deler som har en pris som er høyere enn prisen for del med katalognr X7770.
SELECT prisinfo.delnr, levnr FROM prisinfo WHERE prisinfo.pris > (SELECT pris FROM prisinfo WHERE katalognr = "X7770");

# 1f. Lag ny tabell samme som levinfo bare uten fylke. Lag en ny tabell med by og fylke
# i.
DROP TABLE IF EXISTS levfylk, fylker;

CREATE TABLE levfylk(
  levnr INT AUTO_INCREMENT,
  navn VARCHAR(30),
  adresse VARCHAR(30),
  levby VARCHAR(30),
  postnr TINYINT,
  PRIMARY KEY(levnr)
);

INSERT INTO levfylk(levnr, navn, adresse, levby, postnr) SELECT levnr, navn, adresse, levby, postnr FROM levinfo;

CREATE TABLE fylker(
  id INT UNSIGNED AUTO_INCREMENT,
  fylke VARCHAR(30),
  bynavn VARCHAR(30),
  PRIMARY KEY (id)
);

INSERT INTO fylker(fylke, bynavn) SELECT fylke, levby FROM levinfo;


#i. Lag view som er det samme som levinfo med levfylk og fylker
DROP VIEW IF EXISTS vr;
CREATE VIEW vr AS SELECT DISTINCT levnr, navn, adresse, levby, postnr, fylke FROM levfylk LEFT JOIN fylker ON levby = bynavn;

# 1g.
# Anta at en vurderer å slette opplysningene om de leverandørene som ikke er representert i Prisinfo-tabellen.
# Finn ut hvilke byer en i tilfelle ikke får leverandør i.
# (Du skal ikke utføre slettingen.)
# (Tips: Svaret skal bli kun én by, "Ål".)
SELECT levby FROM levinfo WHERE levby NOT IN(SELECT levby FROM levinfo NATURAL JOIN prisinfo);


# 1h.

# Lager et view om hvilke ordre det er snakk om
DROP VIEW IF EXISTS ordre;
CREATE VIEW ordre AS SELECT * FROM ordredetalj WHERE ordrenr = 18;

#Lager et view om hvilke leverandører som leverer delnummer 3 og 4
DROP VIEW IF EXISTS ordrelev;
CREATE VIEW ordrelev AS SELECT levnr, navn, delnr FROM levinfo NATURAL JOIN prisinfo WHERE delnr = 3 OR delnr = 4;

#Lager et view som samler ordre, ordrelev og prisinfo
DROP VIEW IF EXISTS ordrepris;
CREATE VIEW ordrepris AS SELECT ordrenr, delnr, pris, kvantum, ordrelev.levnr FROM ordre NATURAL JOIN ordrelev NATURAL JOIN prisinfo WHERE levnr = 6 OR levnr = 82;

#Henter det endelige resultatet fra ordrepris
SELECT levnr, SUM(pris*kvantum) "Samlet pris" FROM ordrepris  WHERE levnr = 6 OR levnr = 82 GROUP BY levnr ORDER BY `Samlet pris` LIMIT 1;

#2a. Sett opp en SELECT-setning som er UNION mellom alle forlag med Oslo-nummer (telefonnummer begynner med 2)
# og alle som ikke er Oslo-nummer. Får du med forlaget med NULL-verdi på telefonnummer? Hvis ikke, utvid unionen med en mengde til.
SELECT * FROM forlag WHERE telefon LIKE '2%'
UNION
SELECT * FROM forlag WHERE telefon NOT LIKE '2%'
UNION
SELECT * FROM forlag WHERE telefon IS NULL;


#2b. Sett opp SQL-setninger som finner gjennomsnittlig alder på forfattere der fødselsåret er oppgitt.
# For forfattere der dødsåret ikke er oppgitt, skal du kun ta med de som er født etter 1900.  Tips for å få ut året i år:
# MySQL: SELECT YEAR(CURRENT_DATE) FROM ... hvilken tabell som helst ...

#Lager et view av forfattere som har fødselsdato som er født etter år 1900
DROP VIEW IF EXISTS alder;
CREATE VIEW alder AS SELECT CAST(CASE WHEN dod_aar IS NULL THEN YEAR(CURRENT_DATE) - fode_aar ELSE dod_aar - fode_aar END AS UNSIGNED )
  AS age FROM forfatter WHERE fode_aar > 1900;
SELECT AVG(age) FROM alder;


#2c. Sett opp SQL-setninger som finner hvor stor andel av forfatterne som ble med i beregningene under b).
DROP VIEW IF EXISTS alder;
CREATE VIEW alder AS SELECT CAST(CASE WHEN dod_aar IS NULL THEN YEAR(CURRENT_DATE) - fode_aar ELSE dod_aar - fode_aar END AS UNSIGNED )
  AS age FROM forfatter WHERE fode_aar > 1900;
