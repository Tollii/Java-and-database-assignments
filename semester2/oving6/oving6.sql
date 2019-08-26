
# Oppgave 1a
#Projeksjon
SELECT tittel FROM bok;

#Seleksjon
SELECT tittel FROM bok WHERE tittel like '____';


#Oppgave 1b
# Kartesisk produkt, viser alle mulige kombinasjoner av tabellene forlag og bok
SELECT * FROM forlag, bok;


#Oppgave 1c
#Equijoin, viser alle radene med lik forlag id i tabellene forlag og bok
SELECT * FROM forlag, bok WHERE forlag.forlag_id = bok.forlag_id;

#Naatural join, viser data fra begge tabellene men uten duplikater ved å matche duplikate kolonner.
SELECT * FROM bok NATURAL JOIN forlag;


#Oppgave 1d
#Union, Kombinerer resultatene inn i en tabell, fjerner data som de begge har til felles.
#For at tabellene skal være unionkompatible må de ha de samme tupplene.
SELECT * FROM tabell1 #{1,2,3}
UNION
SELECT * FROM tabell2 #{3,4,5}
#Resultat blir 1,2,3,4,5


#Oppgave 2a
#Finne alle forlagsnavn
SELECT forlag_navn FROM forlag;


#Oppgave 2b
#Forlag som ikke har gitt ut bøker
SELECT forlag_id FROM forlag WHERE NOT EXISTS(SELECT forlag_id FROM bok where bok.forlag_id = forlag.forlag_id);


#Oppgave 2c
#Finne forfatter(e) født i 1948
SELECT * FROM forfatter WHERE forfatter.fode_aar = 1948;


#Oppgave 2d
#Finne forlag navn og adresse til boken 'Generation X'
SELECT forlag_navn, adresse FROM forlag, bok WHERE bok.forlag_id = forlag.forlag_id AND bok.tittel = 'Generation X';

#Oppgave 2e
#Finne bøker som Hamsun har skrevet
SELECT * FROM bok NATURAL JOIN bok_forfatter NATURAL JOIN forfatter WHERE forfatter.etternavn = 'Hamsun';


#Oppgave 2f
#Finne informasjon om alle bøker og forlag
SELECT tittel, utgitt_aar, forlag_navn, adresse, telefon FROM bok RIGHT JOIN forlag ON(bok.forlag_id = forlag.forlag_id);