DROP TABLE IF EXISTS andelseier, leilighet, bygning, borettslag;

CREATE TABLE borettslag(
  borettslag_id INT UNSIGNED AUTO_INCREMENT,
  adresse VARCHAR(30) NOT NULL,
  antHus SMALLINT UNSIGNED NOT NULL,
  PRIMARY KEY (borettslag_id)
);

CREATE TABLE bygning(
  bygg_id INT UNSIGNED AUTO_INCREMENT,
  antLeiligheter TINYINT UNSIGNED NOT NULL,
  antEtasjer TINYINT UNSIGNED NOT NULL,
  borettslag_id INT UNSIGNED NOT NULL,
  PRIMARY KEY (bygg_id),
  FOREIGN KEY (borettslag_id) REFERENCES borettslag(borettslag_id)
);

CREATE TABLE leilighet(
  leilighet_id INT UNSIGNED AUTO_INCREMENT,
  rom TINYINT UNSIGNED NOT NULL,
  m2 SMALLINT UNSIGNED NOT NULL,
  etasje TINYINT NOT NULL,
  leilighetsNr SMALLINT UNSIGNED NOT NULL,
  bygg_id INT UNSIGNED,
  PRIMARY KEY (leilighet_id),
  FOREIGN KEY (bygg_id) REFERENCES bygning(bygg_id)
);

CREATE TABLE andelseier(
  eier_id INT UNSIGNED AUTO_INCREMENT,
  navn VARCHAR(30) NOT NULL,
  leilighet_id INT UNSIGNED,
  PRIMARY KEY (eier_id),
  FOREIGN KEY (leilighet_id) REFERENCES leilighet(leilighet_id)
);

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE borettslag;
TRUNCATE TABLE bygning;
TRUNCATE TABLE leilighet;
TRUNCATE TABLE andelseier;
SET FOREIGN_KEY_CHECKS = 1;



INSERT INTO borettslag VALUE (DEFAULT, 'Test string', 1);
INSERT INTO borettslag VALUE (DEFAULT, 'Test string1', 1);

INSERT INTO bygning VALUE (DEFAULT, 2, 2, 1);
INSERT INTO bygning VALUE (DEFAULT, 2, 2, 2);

INSERT INTO leilighet VALUE (DEFAULT, 2, 25, 1, 1, 1);
INSERT INTO leilighet VALUE (DEFAULT, 2, 50, 2, 1, 2);

INSERT INTO andelseier VALUE (DEFAULT, 'Test person', 1);
INSERT INTO andelseier VALUE (DEFAULT, 'Test person1', 2);

INSERT INTO andelseier VALUE (DEFAULT, 'Error person', 9);
INSERT INTO bygning VALUE (DEFAULT, 2, 2, 5);

