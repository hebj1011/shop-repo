-- ===============================================================================
-- Jede SQL-Anweisung muss in genau 1 Zeile
-- Kommentare durch -- am Zeilenanfang
-- ===============================================================================

INSERT INTO artikel (art, id, name, einzelpreis,bestand, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('S',300,'Helm',80,5,0,'01.08.2006','01.08.2006', 'Gross', 1, null);
INSERT INTO artikel (art, id, name, einzelpreis,bestand, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('F',301,'Mountainbike',10000.12,2,0,'02.08.2006','02.08.2006', null, null, null);
INSERT INTO artikel (art, id, name, einzelpreis,bestand, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('E',302,'Klingel',3,18,0,'03.08.2006','03.08.2006', null, null, 'des nenn ich mal ne klingel');
INSERT INTO artikel (art, id, name, einzelpreis,bestand, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('E',303,'Sattel',150.54,189,0,'04.08.2006','04.08.2006', null, null, 'da sitzt sich gut drauf');
INSERT INTO artikel (art, id, name, einzelpreis,bestand, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('F',304,'Omarad',600.45,8,0,'05.08.2006','05.08.2006', null, null, null);
INSERT INTO artikel (art, id, name, einzelpreis,bestand, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('S',305,'Schuetzer',5.24,0,1,'06.08.2006','06.08.2006', 'klein', 0, null);
INSERT INTO artikel (art, id, name, einzelpreis,bestand, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('F',306,'Rennrad',60000.76,1,0,'07.08.2006','07.08.2006', null, null, null);

--
-- kunde
--
INSERT INTO kunde (id, nachname, vorname, seit, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (1,'Admin','Admin','01.01.2001',NULL,NULL,1,NULL,NULL,'admin@hs-karlsruhe.de','1','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (100,'Hans','Treuer','01.01.2001','VH','M',1,NULL,NULL,'hans@hs-karlsruhe.de','1','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (101,'Lustig','Peter','01.07.1990','VH','M',1,'0,1','300','lustig@hs-karlsruhe.de','12345678','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (102,'Brady','Tom','01.04.1990',NULL,'M',1,'0,15','130','brady@hs-karlsruhe.de','7777','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (103,'Gronkowski','Robert','12.04.1992','G','W',1,'0,04','500','gronkwoski@hs-karlsruhe.de','171717','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (104,'Manning','Peyton','09.06.1994','L','W',1,null,'1200','manning@hs-karlsruhe.de','237','16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO kunde (id, nachname, vorname, seit, familienstand, geschlecht, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (105,'Kaepernick','Colin','21.10.1996','VW','M',1,'0,1','230','kaepernick@hs-karlsruhe.de','17','16.01.2014 01:10:00','16.01.2014 01:10:00');


--
-- adresse
--
INSERT INTO adresse (id, plz, land, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (200,'76133','Deutschland','Karlsruhe','Moltkestraﬂe','30',100,'16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO adresse (id, plz, land, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (201,'76596','Deutschland','Forbach','Im Wiesengrund','15',101,'16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO adresse (id, plz, land, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (202,'76133','USA','Karlsruhe','Patriots','12',102,'16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO adresse (id, plz, land, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (203,'76133','USA','Karlsruhe','Patriots','87',103,'16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO adresse (id, plz, land, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (204,'76133','USA','Denver','Broncos','18',104,'16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO adresse (id, plz, land, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (205,'76133','USA','Karlsruhe','SanFrancisco','7',105,'16.01.2014 01:10:00','16.01.2014 01:10:00');

--
-- bestellung
--
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (400,105,0,'12.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (401,105,1,'13.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (402,102,0,'15.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (403,102,1,'16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO bestellung (id, kunde_fk, idx, erzeugt, aktualisiert) VALUES (404,103,0,'11.01.2014 01:10:00','16.01.2014 01:10:00');

--
-- bestellposition
--
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (500,0,400,300,1,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (501,1,400,301,4,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (502,0,401,302,5,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (503,0,402,303,3,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (504,1,402,304,2,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (505,0,403,305,1,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (506,0,404,300,5,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (507,1,404,301,2,'16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO bestellposition (id, idx, bestellung_fk, artikel_fk, anzahl, erzeugt, aktualisiert) VALUES (508,2,404,302,8,'16.01.2014 00:00:00','16.01.2014 00:00:00');

--
-- lieferung
--
INSERT INTO lieferung (id, liefernr, transport_art) VALUES (600,'20051005-001','ST');
INSERT INTO lieferung (id, liefernr, transport_art) VALUES (601,'20051005-002','SCH');
INSERT INTO lieferung (id, liefernr, transport_art) VALUES (602,'20051005-003','L');
INSERT INTO lieferung (id, liefernr, transport_art) VALUES (603,'20051008-001','W');

--
-- bestellung_lieferung
--
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (400,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (401,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,601);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (403,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (404,603);

--
-- artikel_farbe SCHWARZ=S ROT=R WEISS=W BLAU=B GELB=G
--
INSERT INTO artikel_farbe (artikel_fk, farbe) VALUES (301,'S');
INSERT INTO artikel_farbe (artikel_fk, farbe) VALUES (301,'G');
INSERT INTO artikel_farbe (artikel_fk, farbe) VALUES (304,'B');
INSERT INTO artikel_farbe (artikel_fk, farbe) VALUES (306,'W');
INSERT INTO artikel_farbe (artikel_fk, farbe) VALUES (306,'R');
