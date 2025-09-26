------------------------------------------------------
drop view if exists swde.budynki_v;
drop view if exists swde.dzialki_v;
drop view if exists swde.gminy_v;
drop view if exists swde.uzytki_v ;
drop view if exists swde.klasy_v  ;
drop view if exists swde.obreby_v  ;
drop view if exists swde.wlasciciele_v ;



select UpdateGeometrySRID('swde', 'budynki', 'the_geom', 2177);
select UpdateGeometrySRID('swde', 'dzialki', 'the_geom', 2177);
select UpdateGeometrySRID('swde', 'jednostki_ewidencyjne', 'the_geom', 2177);
select UpdateGeometrySRID('swde', 'klasy', 'the_geom', 2177);
select UpdateGeometrySRID('swde', 'obreby', 'the_geom', 2177);
select UpdateGeometrySRID('swde', 'punkty', 'the_geom', 2177);
select UpdateGeometrySRID('swde', 'uzytki', 'the_geom', 2177);
select UpdateGeometrySRID('swde', 'wlasciciele', 'the_geom', 2177);

--ALTER TABLE swde.budynki ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE swde.dzialki ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE swde.jednostki_ewidencyjne ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE swde.klasy ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE swde.obreby ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE swde.punkty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE swde.uzytki ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE swde.wlasciciele ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));

--------------------------------------------------------------------------
-- View: swde.wlasciciele_v

-- View: swde.wlasciciele_v

-- DROP VIEW swde.wlasciciele_v;

CREATE OR REPLACE VIEW swde.wlasciciele_v AS 
 SELECT wlasciciele.gid,
    wlasciciele.numer,
    initcap(wlasciciele.obreb) AS obreb,
    wlasciciele.g5idd AS identyfikator,
    wlasciciele.typ,
        CASE
            WHEN "current_user"() = ANY (ARRAY['postgres'::name, 'kasiak'::name, 'jagoda'::name, 'milenag'::name, 'anna'::name, 'karolina'::name, 'emilka'::name, 'andrzej'::name, 'marta'::name, 'kasia'::name, 'robertz'::name]) THEN initcap(wlasciciele.nazwa)
            ELSE NULL::text
        END AS nazwa,
        CASE
            WHEN "current_user"() = ANY (ARRAY['postgres'::name, 'kasiak'::name, 'jagoda'::name, 'marta'::name, 'milenag'::name, 'karolina'::name, 'robertz'::name]) THEN initcap(wlasciciele.adres)
            ELSE NULL::text
        END AS adres,
        CASE
            WHEN "current_user"() = ANY (ARRAY['postgres'::name, 'jagoda'::name, 'karolina'::name, 'marta'::name, 'milenag'::name, 'robertz'::name]) THEN wlasciciele.dokumenty
            ELSE NULL::text
        END AS dokumenty,
    wlasciciele.the_geom
   FROM swde.wlasciciele;

ALTER TABLE swde.wlasciciele_v
  OWNER TO postgres;
GRANT ALL ON TABLE swde.wlasciciele_v TO postgres;
GRANT SELECT ON TABLE swde.wlasciciele_v TO operators;
GRANT SELECT ON TABLE swde.wlasciciele_v TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.wlasciciele_v TO admins;



-- View: swde.uzytki_v
-- DROP VIEW swde.uzytki_v;

CREATE OR REPLACE VIEW swde.uzytki_v AS 
 SELECT uzytki.gid,
    uzytki.g5ofu AS kod,
    uzytki.g5idt AS numer,
    uzytki.the_geom
   FROM swde.uzytki;

ALTER TABLE swde.uzytki_v OWNER TO postgres;
GRANT ALL ON TABLE swde.uzytki_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.uzytki_v TO operators;
GRANT SELECT ON TABLE swde.uzytki_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.uzytki_v TO admins;

-- View: swde.obreby_v
-- DROP VIEW swde.obreby_v;

CREATE OR REPLACE VIEW swde.obreby_v AS 
 SELECT obreby.gid,
    obreby.g5nro AS numer,
    initcap(obreby.g5naz) AS nazwa,
    obreby.the_geom
   FROM swde.obreby;

ALTER TABLE swde.obreby_v OWNER TO postgres;
GRANT ALL ON TABLE swde.obreby_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.obreby_v TO operators;
GRANT SELECT ON TABLE swde.obreby_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.obreby_v TO admins;

-- View: swde.klasy_v
-- DROP VIEW swde.klasy_v;

CREATE OR REPLACE VIEW swde.klasy_v AS 
 SELECT klasy.gid,
    klasy.g5ozu AS uzytek,
    klasy.g5ozk AS klasa,
    klasy.g5idk AS kod,
    klasy.the_geom
   FROM swde.klasy;

ALTER TABLE swde.klasy_v OWNER TO postgres;
GRANT ALL ON TABLE swde.klasy_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.klasy_v TO operators;
GRANT SELECT ON TABLE swde.klasy_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.klasy_v TO admins;

-- View: swde.gminy_v
-- DROP VIEW swde.gminy_v;

CREATE OR REPLACE VIEW swde.gminy_v AS 
 SELECT jednostki_ewidencyjne.gid,
    jednostki_ewidencyjne.g5naz AS nazwa,
    jednostki_ewidencyjne.the_geom
   FROM swde.jednostki_ewidencyjne;

ALTER TABLE swde.gminy_v  OWNER TO postgres;
GRANT ALL ON TABLE swde.gminy_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.gminy_v TO operators;
GRANT SELECT ON TABLE swde.gminy_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.gminy_v TO admins;

-- View: swde.dzialki_v
-- DROP VIEW swde.dzialki_v;

CREATE OR REPLACE VIEW swde.dzialki_v AS 
 SELECT dzialki.gid,
    dzialki.numer,
    initcap (dzialki.obreb) as obreb,
    dzialki.g5idd AS kod,
    dzialki.the_geom
   FROM swde.dzialki;

ALTER TABLE swde.dzialki_v OWNER TO postgres;
GRANT ALL ON TABLE swde.dzialki_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.dzialki_v TO operators;
GRANT SELECT ON TABLE swde.dzialki_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.dzialki_v TO admins;

-- View: swde.budynki_v
-- DROP VIEW swde.budynki_v;

CREATE OR REPLACE VIEW swde.budynki_v AS 
 SELECT initcap(budynki.g5msc) AS miejscowosc,
    initcap(budynki.g5ulc) AS ulica,
    budynki.g5nra AS numer,
    initcap((budynki.g5ulc::text || ' '::text)) || budynki.g5nra::text AS adres,
    budynki.g5kod AS kod_pocztowy,
    budynki.g5rbb AS rok_budowy,
    budynki.gid,
    budynki.the_geom
   FROM swde.budynki;

ALTER TABLE swde.budynki_v OWNER TO postgres;
GRANT ALL ON TABLE swde.budynki_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.budynki_v TO operators;
GRANT SELECT ON TABLE swde.budynki_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.budynki_v TO admins;

-- View: swde.adresy_v
-- DROP VIEW swde.adresy_v;

CREATE OR REPLACE VIEW swde.adresy_v AS 
 SELECT poligony.gid,
    poligony.g7 AS kod,
    poligony.xcode_d AS nazwa,
    poligony.mjs_n AS miejsciowosc,
    poligony.uli_n AS ulica,
    poligony.npd AS adres,
    public.st_centroid(poligony.the_geom) AS the_geom
   FROM sytuacja.poligony
  WHERE poligony.g7::text = 'GESBZO'::text and poligony.uli_n is not null;

ALTER TABLE swde.adresy_v  OWNER TO postgres;
GRANT ALL ON TABLE swde.adresy_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.adresy_v TO operators;
GRANT SELECT ON TABLE swde.adresy_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.adresy_v TO admins;

-- View: techniczny.zuzycia_v
-- DROP VIEW techniczny.zuzycia_v;

CREATE OR REPLACE VIEW techniczny.zuzycia_v AS 
 SELECT z.symb_klienta,
    z.symb_punktu,
    z.miejscowosc,
    z.ulica,
    z.nr_domu,
    z.nr_lokalu,
    z.nr_wodom,
    z.zuzycie,
    a.the_geom
   FROM techniczny.zuzycia z
     LEFT JOIN swde.adresy_v a ON z.miejscowosc = a.miejsciowosc AND z.ulica = a.ulica AND z.nr_domu = a.adres;

ALTER TABLE techniczny.zuzycia_v OWNER TO postgres;
GRANT ALL ON TABLE techniczny.zuzycia_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE techniczny.zuzycia_v TO operators;
GRANT SELECT, UPDATE, INSERT ON TABLE techniczny.zuzycia_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE techniczny.zuzycia_v TO admins;

GRANT SELECT ON TABLE swde.obreby TO admins;
GRANT SELECT ON TABLE swde.obreby TO operators;
GRANT SELECT ON TABLE swde.obreby TO users;


