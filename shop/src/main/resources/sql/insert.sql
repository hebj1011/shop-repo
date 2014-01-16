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

INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (100,'Hans','Treuer','01.01.2001',1,NULL,NULL,'hans@hs-karlsruhe.de','1','01.08.2006 00:00:00','01.08.2006 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (101,'Lustig','Peter','01.07.1990',1,'0,1','300','lustig@hs-karlsruhe.de','12345678','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (102,'Brady','Tom','01.04.1990',1,'0,15','130','brady@hs-karlsruhe.de','7777','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (103,'Gronkowski','Robert','12.04.1992',1,'0,04','500','gronkwoski@hs-karlsruhe.de','171717','16.01.2014 00:00:00','16.01.2014 00:00:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (104,'Manning','Peyton','09.06.1994',1,null,'1200','manning@hs-karlsruhe.de','237','16.01.2014 01:10:00','16.01.2014 01:10:00');
INSERT INTO kunde (id, nachname, vorname, seit, newsletter, rabatt, umsatz, email, password, erzeugt, aktualisiert) VALUES (105,'Kaepernick','Colin','21.10.1996',1,'0,1','230','kaepernick@hs-karlsruhe.de','17','16.01.2014 01:10:00','16.01.2014 01:10:00');