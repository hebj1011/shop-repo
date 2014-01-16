-- ===============================================================================
-- Jede SQL-Anweisung muss in genau 1 Zeile
-- Kommentare durch -- am Zeilenanfang
-- ===============================================================================

INSERT INTO artikel (art, id, name, einzelpreis, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('S',300,'Tisch ''Oval''',80,0,'01.08.2006 00:00:00','01.08.2006 00:00:00', 'Gross', null, null);
INSERT INTO artikel (art, id, name, einzelpreis, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('F',301,'Stuhl ''Sitz bequem''',10,0,'02.08.2006 00:00:00','02.08.2006 00:00:00', 'Gross', null, null);
INSERT INTO artikel (art, id, name, einzelpreis, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('E',302,'Tür ''Hoch und breit''',300,0,'03.08.2006 00:00:00','03.08.2006 00:00:00', 'Gross', null, null);
INSERT INTO artikel (art, id, name, einzelpreis, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('E',303,'Fenster ''Glasklar''',150,0,'04.08.2006 00:00:00','04.08.2006 00:00:00', 'Gross', null, null);
INSERT INTO artikel (art, id, name, einzelpreis, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('F',304,'Spiegel ''Mach mich schöner''',60,0,'05.08.2006 00:00:00','05.08.2006 00:00:00', 'Gross', null, null);
INSERT INTO artikel (art, id, name, einzelpreis, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('S',305,'Kleiderschrank ''Viel Platz''',500,0,'06.08.2006 00:00:00','06.08.2006 00:00:00', 'Gross', null, null);
INSERT INTO artikel (art, id, name, einzelpreis, ausgesondert, erzeugt, aktualisiert, groesse, tuev, datenblatt) VALUES ('F',306,'Bett ''Mit Holzwurm''',600,0,'07.08.2006 00:00:00','07.08.2006 00:00:00', 'Gross', null, null);

--
-- kunde
--
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (100,'Hans','Treuer','01.01.2001',1,NULL,NULL,'hans@hs-karlsruhe.de','1','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (101,'Lustig','Peter','01.07.1990',1,'0,1','300','lustig@hs-karlsruhe.de','12345678','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (102,'Brady','Tom','01.04.1990',1,'0,15','130','brady@hs-karlsruhe.de','7777','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (103,'Gronkowski','Robert','12.04.1992',1,'0,04','500','gronkwoski@hs-karlsruhe.de','171717','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (104,'Manning','Peyton','09.06.1994',1,null,'1200','manning@hs-karlsruhe.de','237','16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (105,'Kaepernick','Colin','21.10.1996',1,'0,1','230','kaepernick@hs-karlsruhe.de','17','16.01.2014 01:10:00','16.01.2014 01:10:00');

--
-- adresse
--
INSERT INTO adresse (id, plz, land, ort, strasse, hausnr, kunde_fk, erzeugt, aktualisiert) VALUES (200,'76133','Deutschland','Karlsruhe','Moltkestraße','30',100,'16.01.2014 01:10:00','16.01.2014 01:10:00');
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
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (600,'20051005-001','ST','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (601,'20051005-002','SCH','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (602,'20051005-003','L','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO lieferung (id, liefernr, transport_art, erzeugt, aktualisiert) VALUES (603,'20051008-001','W','16.01.2014 00:00:00','16.01.2014 00:00:00');

--
-- bestellung_lieferung
--
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (400,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (401,600);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,601);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (402,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (403,602);
INSERT INTO bestellung_lieferung (bestellung_fk, lieferung_fk) VALUES (404,603);
