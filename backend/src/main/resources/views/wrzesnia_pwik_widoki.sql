
--select UpdateGeometrySRID('sytuacja7', 'linie', 'the_geom', 2177);
--select UpdateGeometrySRID('sytuacja7', 'punkty', 'the_geom', 2177);
--select UpdateGeometrySRID('sytuacja7', 'poligony', 'the_geom', 2177);
select UpdateGeometrySRID('sytuacja7', 'sytuacja', 'the_geom', 2177);

--ALTER TABLE sytuacja7.linie ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE sytuacja7.punkty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE sytuacja7.poligony ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
--ALTER TABLE sytuacja7.sytuacja ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));

--delete from sytuacja7.linie where geometrytype(the_geom)='POINT';
--ALTER TABLE sytuacja7.linie ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
--ALTER TABLE sytuacja7.punkty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
--ALTER TABLE sytuacja7.poligony ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);

ALTER TABLE sytuacja7.sytuacja  ADD COLUMN geometria text;
ALTER TABLE sytuacja7.sytuacja ADD COLUMN xcode_n text;

UPDATE sytuacja7.sytuacja SET  geometria='punkt' WHERE geometrytype(sytuacja.the_geom) ~~* '%POINT%'::text;
UPDATE sytuacja7.sytuacja SET  geometria='linie' WHERE geometrytype(sytuacja.the_geom) ~~* '%LINESTRING%'::text;
UPDATE sytuacja7.sytuacja SET  geometria='poligon' WHERE geometrytype(sytuacja.the_geom) ~~* '%POLYGON%'::text;

UPDATE sytuacja7.sytuacja SET xcode_n=g7;

CREATE INDEX sytuacja_geometria_idx  ON sytuacja7.sytuacja  USING btree (geometria COLLATE pg_catalog."default");
CREATE INDEX sytuacja_xcode_n_idx  ON sytuacja7.sytuacja  USING btree (xcode_n COLLATE pg_catalog."default");

DROP VIEW IF EXISTS sytuacja7.linie;
CREATE OR REPLACE VIEW sytuacja7.linie AS SELECT * FROM sytuacja7.sytuacja  WHERE sytuacja.geometria='linie';
DROP VIEW IF EXISTS sytuacja7.punkty;
CREATE OR REPLACE VIEW sytuacja7.punkty AS SELECT * FROM sytuacja7.sytuacja  WHERE sytuacja.geometria='punkt';
DROP VIEW IF EXISTS sytuacja7.poligony;
CREATE OR REPLACE VIEW sytuacja7.poligony AS SELECT * FROM sytuacja7.sytuacja  WHERE sytuacja.geometria='poligon';

select UpdateGeometrySRID('gesut7', 'co_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'co_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'co_sieci', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'eng_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'eng_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'eng_sieci', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'inne_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'inne_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'inne_sieci', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'gaz_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'gaz_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'gaz_sieci', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'spc_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'spc_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'spc_sieci', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'kan_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'kan_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'kan_sieci', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'tel_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'tel_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'tel_sieci', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'wod_armatura', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'wod_obiekty', 'the_geom', 2177);
select UpdateGeometrySRID('gesut7', 'wod_sieci', 'the_geom', 2177);

ALTER TABLE gesut7.co_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.co_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.co_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.eng_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.eng_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.eng_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.gaz_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.gaz_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.gaz_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.inne_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.inne_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.inne_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.spc_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.spc_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.spc_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.kan_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.kan_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.kan_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.tel_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.tel_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.tel_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.wod_armatura ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.wod_obiekty ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));
ALTER TABLE gesut7.wod_sieci ADD CONSTRAINT enforce_srid_the_geom CHECK (st_srid(the_geom) = (2177));

ALTER TABLE gesut7.co_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.co_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.co_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
ALTER TABLE gesut7.eng_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.eng_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.eng_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
ALTER TABLE gesut7.gaz_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.gaz_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.gaz_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
ALTER TABLE gesut7.inne_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.inne_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.inne_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
ALTER TABLE gesut7.spc_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.spc_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.spc_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
ALTER TABLE gesut7.kan_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.kan_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.kan_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
ALTER TABLE gesut7.wod_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.wod_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.wod_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);
ALTER TABLE gesut7.tel_armatura ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POINT'::text OR the_geom IS NULL);
ALTER TABLE gesut7.tel_obiekty ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'POLYGON'::text OR the_geom IS NULL);
ALTER TABLE gesut7.tel_sieci ADD CONSTRAINT enforce_geotype_the_geom CHECK (geometrytype(the_geom) = 'LINESTRING'::text OR the_geom IS NULL);

SET search_path = pwik, pg_catalog;

DROP VIEW IF EXISTS pwik.kan_armatura_new;
CREATE OR REPLACE VIEW pwik.kan_armatura_new AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
     k2.version,
    k2.status,
    'c'::text AS stan,
        CASE
            WHEN k2.g7 ~~ 'GUPKD%'::text THEN 'KD'::text
            WHEN k2.g7 ~~ 'GUPKO%'::text OR k2.g7 ~~ 'GUPKX%'::text THEN 'KO'::text
            WHEN k2.g7 ~~ 'GUPKL%'::text OR k2.g7 ~~ 'GUPKP%'::text OR k2.g7 ~~ 'GUPKS%'::text THEN 'KS'::text
            ELSE 'KAN'::text
        END AS typ,
    k2.kerg,
    k2.zrodlo,
    k2.adres,
    k2.rz_dna,
    k2.rz_terenu,
    k2.g7 AS g7_kan,
    k2.g7_kan_opi,
    k2.ulica AS no_ulica,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.kan_armatura k2 LEFT JOIN pwik.kan_armatura k1 USING (identyfikator) WHERE k1.identyfikator IS NULL
 AND k2.g7 !~~ 'GZPK%' and (k2.g7 IN ( 'GUPKUH','GUPKSH' )) IS NOT TRUE;

ALTER TABLE pwik.kan_armatura_new OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_armatura_new TO postgres;
GRANT SELECT ON TABLE pwik.kan_armatura_new TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_armatura_new TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_armatura_new TO admins;

DROP VIEW IF EXISTS pwik.kan_armatura_update;
CREATE OR REPLACE VIEW pwik.kan_armatura_update AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
     k2.version,
    k2.status,
    'c'::text AS stan,
        CASE
            WHEN k2.g7 ~~ 'GUPKD%'::text THEN 'KD'::text
            WHEN k2.g7 ~~ 'GUPKO%'::text OR k2.g7 ~~ 'GUPKX%'::text THEN 'KO'::text
            WHEN k2.g7 ~~ 'GUPKL%'::text OR k2.g7 ~~ 'GUPKP%'::text OR k2.g7 ~~ 'GUPKS%'::text THEN 'KS'::text
            ELSE 'KAN'::text
        END AS typ,
    k2.kerg,
    k2.zrodlo,
    k2.adres,
    k2.rz_dna,
    k2.rz_terenu,
    k2.g7 AS g7_kan,
    k2.g7_kan_opi,
    k2.ulica AS no_ulica,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.kan_armatura k2 LEFT JOIN pwik.kan_armatura k1 USING (identyfikator)  WHERE k2.modification_date > k1.modification_date ;

ALTER TABLE pwik.kan_armatura_update OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_armatura_update TO postgres;
GRANT SELECT ON TABLE pwik.kan_armatura_update TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_armatura_update TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_armatura_update TO admins;

DROP VIEW IF EXISTS pwik.kan_obiekty_new;
CREATE OR REPLACE VIEW pwik.kan_obiekty_new AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
        k2.version,
    k2.status,
    'KAN'::text AS typ,
        CASE
            WHEN k2.g7 = 'GUSKUO'::text THEN 'KOM'::text
            WHEN k2.g7 = 'GULKUC'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GULKUB'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GUSKOB'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GUSKSD'::text THEN 'STD'::text
            WHEN k2.g7 = 'GUSKZB'::text THEN 'ZBR'::text
            WHEN k2.g7 = 'GUSKPR'::text THEN 'PPP'::text
            WHEN k2.g7 = 'GUSKOL'::text THEN 'OSD'::text
            WHEN k2.g7 = 'GUSKUI'::text THEN 'INN'::text
            WHEN k2.g7 = 'GUSKWZ'::text THEN 'WLZ'::text
            ELSE NULL::text
        END AS obiekt,
    k2.rz_terenu,
    k2.rz_dna,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.adres,
    k2.ulica AS no_ulica,
    k2.g7 AS g7_kan,
    k2.g7_kan_opi,
    k2.nr_modgik,
    k2.obreb,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.the_geom
   FROM gesut7.kan_obiekty k2 LEFT JOIN pwik.kan_obiekty k1 USING (identyfikator) 
 WHERE k1.identyfikator IS NULL AND k2.g7 !~~ 'GZSK%';

ALTER TABLE pwik.kan_obiekty_new  OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_obiekty_new TO postgres;
GRANT SELECT ON TABLE pwik.kan_obiekty_new TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_obiekty_new TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_obiekty_new TO admins;

DROP VIEW IF EXISTS pwik.kan_obiekty_update;
CREATE OR REPLACE VIEW pwik.kan_obiekty_update AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
        k2.version,
    k2.status,
    'KAN'::text AS typ,
        CASE
            WHEN k2.g7 = 'GUSKUO'::text THEN 'KOM'::text
            WHEN k2.g7 = 'GULKUC'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GULKUB'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GUSKOB'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GUSKSD'::text THEN 'STD'::text
            WHEN k2.g7 = 'GUSKZB'::text THEN 'ZBR'::text
            WHEN k2.g7 = 'GUSKPR'::text THEN 'PPP'::text
            WHEN k2.g7 = 'GUSKOL'::text THEN 'OSD'::text
            WHEN k2.g7 = 'GUSKUI'::text THEN 'INN'::text
            WHEN k2.g7 = 'GUSKWZ'::text THEN 'WLZ'::text
            ELSE NULL::text
        END AS obiekt,
    k2.rz_terenu,
    k2.rz_dna,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.adres,
    k2.ulica AS no_ulica,
    k2.g7 AS g7_kan,
    k2.g7_kan_opi,
    k2.nr_modgik,
    k2.obreb,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.the_geom
   FROM gesut7.kan_obiekty k2 LEFT JOIN pwik.kan_obiekty k1 USING (identyfikator) WHERE k2.modification_date > k1.modification_date ;

ALTER TABLE pwik.kan_obiekty_update OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_obiekty_update TO postgres;
GRANT SELECT ON TABLE pwik.kan_obiekty_update TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_obiekty_update TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_obiekty_update TO admins;

DROP VIEW IF EXISTS pwik.kan_sieci_new;
CREATE OR REPLACE VIEW pwik.kan_sieci_new AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
        k2.version,
    k2.status,
        CASE
            WHEN k2.g7 ~~ 'GULKD%'::text THEN 'KD'::text
            WHEN k2.g7 ~~ 'GULKO%'::text OR k2.g7 ~~ 'GULKX%'::text THEN 'KO'::text
            WHEN k2.g7 ~~ 'GULKS%'::text OR k2.g7 ~~ 'GULKP%'::text OR k2.g7 ~~ 'GULKL%'::text THEN 'KS'::text
            ELSE 'KAN'::text
        END AS typ,
    k2.funkcja,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.dzialka,
    k2.adres,
    k2.izolacja,
    k2.przebieg,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.ulica AS no_ulica,
    k2.g7 AS g7_kan,
    k2.g7_kan_opi,
    k2.dlugosc,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.kan_sieci k2 LEFT JOIN pwik.kan_sieci k1 USING (identyfikator) 
 WHERE k1.identyfikator IS NULL AND k2.g7 !~~ 'GZLK%';

ALTER TABLE pwik.kan_sieci_new  OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_sieci_new TO postgres;
GRANT SELECT ON TABLE pwik.kan_sieci_new TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_sieci_new TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_sieci_new TO admins;

DROP VIEW IF EXISTS pwik.kan_sieci_update;
CREATE OR REPLACE VIEW pwik.kan_sieci_update AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
        k2.version,
    k2.status,
        CASE
            WHEN k2.g7 ~~ 'GULKD%'::text THEN 'KD'::text
            WHEN k2.g7 ~~ 'GULKO%'::text OR k2.g7 ~~ 'GULKX%'::text THEN 'KO'::text
            WHEN k2.g7 ~~ 'GULKS%'::text OR k2.g7 ~~ 'GULKP%'::text OR k2.g7 ~~ 'GULKL%'::text THEN 'KS'::text
            ELSE 'KAN'::text
        END AS typ,
    k2.funkcja,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.dzialka,
    k2.adres,
    k2.izolacja,
    k2.przebieg,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.ulica,
    k2.g7 AS g7_kan,
    k2.g7_kan_opi,
    k2.dlugosc,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.kan_sieci k2  LEFT JOIN pwik.kan_sieci k1 USING (identyfikator) WHERE k2.modification_date > k1.modification_date ;

ALTER TABLE pwik.kan_sieci_update  OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_sieci_update TO postgres;
GRANT SELECT ON TABLE pwik.kan_sieci_update TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_sieci_update TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_sieci_update TO admins;

DROP VIEW IF EXISTS pwik.wod_armatura_new;
CREATE OR REPLACE VIEW pwik.wod_armatura_new AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
    k2.version,
    k2.status,
    'c'::text AS stan,
    k2.kerg,
    k2.zrodlo,
    k2.adres,
    k2.rz_dna,
    k2.rz_terenu,
    k2.g7 AS g7_wod,
    k2.g7_wod_opi,
    k2.ulica AS no_ulica,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.wod_armatura k2  LEFT JOIN pwik.wod_armatura k1 USING (identyfikator) WHERE k1.identyfikator IS NULL
   AND k2.g7 !~~ 'GZPW%' and (k2.g7 IN ( 'GUPWUH','GUPWSH' )) IS NOT TRUE;

ALTER TABLE pwik.wod_armatura_new  OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_armatura_new TO postgres;
GRANT SELECT ON TABLE pwik.wod_armatura_new TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_armatura_new TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_armatura_new TO admins;

DROP VIEW IF EXISTS pwik.wod_armatura_update;
CREATE OR REPLACE VIEW pwik.wod_armatura_update AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
    k2.version,
    k2.status,
    'c'::text AS stan,
    k2.kerg,
    k2.zrodlo,
    k2.adres,
    k2.rz_dna,
    k2.rz_terenu,
    k2.g7 AS g7_wod,
    k2.g7_wod_opi,
    k2.ulica AS no_ulica,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.wod_armatura k2  LEFT JOIN pwik.wod_armatura k1 USING (identyfikator)  WHERE k2.modification_date > k1.modification_date ;

ALTER TABLE pwik.wod_armatura_update OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_armatura_update TO postgres;
GRANT SELECT ON TABLE pwik.wod_armatura_update TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_armatura_update TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_armatura_update TO admins;

DROP VIEW IF EXISTS pwik.wod_obiekty_new;
CREATE OR REPLACE VIEW pwik.wod_obiekty_new AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
    k2.version,
    k2.status,
        CASE
            WHEN k2.g7 = 'GUSWUO'::text THEN 'KOM'::text
            WHEN k2.g7 = 'GULWUC'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GULWUB'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GUSWSD'::text THEN 'STD'::text
            WHEN k2.g7 = 'GUSWZB'::text THEN 'ZBR'::text
            ELSE NULL::text
        END AS obiekt,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.adres,
    k2.ulica AS no_ulica,
    k2.g7 AS g7_wod,
    k2.g7_wod_opi,
    k2.nr_modgik,
    k2.obreb,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.rz_terenu,
    k2.rz_dna,
    k2.the_geom
   FROM gesut7.wod_obiekty k2  LEFT JOIN pwik.wod_obiekty k1 USING (identyfikator) 
 WHERE k1.identyfikator IS NULL AND k2.g7 !~~ 'GZSW%'::text;

ALTER TABLE pwik.wod_obiekty_new  OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_obiekty_new TO postgres;
GRANT SELECT ON TABLE pwik.wod_obiekty_new TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_obiekty_new TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_obiekty_new TO admins;

DROP VIEW IF EXISTS pwik.wod_obiekty_update;
CREATE OR REPLACE VIEW pwik.wod_obiekty_update AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
    k2.version,
    k2.status,
        CASE
            WHEN k2.g7 = 'GUSWUO'::text THEN 'KOM'::text
            WHEN k2.g7 = 'GULWUC'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GULWUB'::text THEN 'OBU'::text
            WHEN k2.g7 = 'GUSWSD'::text THEN 'STD'::text
            WHEN k2.g7 = 'GUSWZB'::text THEN 'ZBR'::text
            ELSE NULL::text
        END AS obiekt,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.adres,
    k2.ulica AS no_ulica,
    k2.g7 AS g7_wod,
    k2.g7_wod_opi,
    k2.nr_modgik,
    k2.obreb,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.rz_terenu,
    k2.rz_dna,
    k2.the_geom
   FROM gesut7.wod_obiekty k2  LEFT JOIN pwik.wod_obiekty k1 USING (identyfikator)  WHERE k2.modification_date > k1.modification_date;

ALTER TABLE pwik.wod_obiekty_update OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_obiekty_update TO postgres;
GRANT SELECT ON TABLE pwik.wod_obiekty_update TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_obiekty_update TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_obiekty_update TO admins;

DROP VIEW IF EXISTS pwik.wod_sieci_new;
CREATE OR REPLACE VIEW pwik.wod_sieci_new AS 
 SELECT k2.gid AS mid,
    k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
    k2.version,
    k2.status,
    k2.funkcja,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.dzialka,
    k2.adres,
    k2.izolacja,
    k2.przebieg,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.ulica AS no_ulica,
    k2.g7 AS g7_wod,
    k2.g7_wod_opi,
    k2.dlugosc,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.wod_sieci k2
     LEFT JOIN pwik.wod_sieci k1 USING (identyfikator)
  WHERE k1.identyfikator IS NULL AND k2.g7 !~~ 'GZLW%'::text;

ALTER TABLE pwik.wod_sieci_new
  OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_sieci_new TO postgres;
GRANT SELECT ON TABLE pwik.wod_sieci_new TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_sieci_new TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_sieci_new TO admins;


DROP VIEW IF EXISTS pwik.wod_sieci_update;
CREATE OR REPLACE VIEW pwik.wod_sieci_update AS 
 SELECT k2.gid AS mid,
     k2.identyfikator,
    k2.creation_date,
    k2.modification_date,
    k2.version,
    k2.status,
    k2.funkcja,
    k2.kerg,
    k2.material,
    k2.zrodlo,
    k2.dzialka,
    k2.adres,
    k2.izolacja,
    k2.przebieg,
        CASE
            WHEN k2.srednic_ww IS NULL OR k2.srednic_ww = 0::double precision THEN k2.srednic_zw * 1000::double precision
            ELSE k2.srednic_ww * 1000::double precision
        END AS srednic,
    k2.srednic_ww * 1000::double precision AS srednic_ww,
    k2.srednic_zw * 1000::double precision AS srednic_zw,
        CASE
            WHEN k2.stan IS NULL THEN 'c'::text
            ELSE k2.stan
        END AS stan,
    k2.ulica AS no_ulica,
    k2.g7 AS g7_wod,
    k2.g7_wod_opi,
    k2.dlugosc,
    k2.nr_modgik,
    k2.obreb,
    k2.the_geom
   FROM gesut7.wod_sieci k2 LEFT JOIN pwik.wod_sieci k1 USING (identyfikator)  WHERE k2.modification_date > k1.modification_date ;

ALTER TABLE pwik.wod_sieci_update  OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_sieci_update TO postgres;
GRANT SELECT ON TABLE pwik.wod_sieci_update TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_sieci_update TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_sieci_update TO admins;

--
-- TOC entry 5422 (class 0 OID 0)
-- Dependencies: 16
-- Name: pwik; Type: ACL; Schema: -; Owner: postgres
--

--REVOKE ALL ON SCHEMA pwik FROM PUBLIC;
--REVOKE ALL ON SCHEMA pwik FROM postgres;
--GRANT ALL ON SCHEMA pwik TO postgres;
--GRANT ALL ON SCHEMA pwik TO admins;
--GRANT USAGE ON SCHEMA pwik TO operators;
--GRANT USAGE ON SCHEMA pwik TO users;

-- View: swde.adresy_v

--DROP VIEW  IF EXISTS swde.adresy_v;
CREATE OR REPLACE VIEW swde.adresy_v AS 
 SELECT poligony.gid,
    poligony.g7 AS kod,
    poligony.xcode_d AS nazwa,
    poligony.mjs_n AS miejsciowosc,
    poligony.uli_n AS ulica,
    poligony.npd AS adres,
    public.st_centroid(poligony.the_geom) AS the_geom
   FROM sytuacja7.poligony  WHERE poligony.g7::text = 'GESBZO'::text and poligony.uli_n is not null;

ALTER TABLE swde.adresy_v  OWNER TO postgres;
GRANT ALL ON TABLE swde.adresy_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE swde.adresy_v TO operators;
GRANT SELECT ON TABLE swde.adresy_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE swde.adresy_v TO admins;


ALTER TABLE gesut7.co_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.co_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.co_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.co_armatura TO operators;
GRANT SELECT ON TABLE gesut7.co_armatura TO users;

ALTER TABLE gesut7.co_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.co_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.co_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.co_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.co_obiekty TO users;

ALTER TABLE gesut7.co_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.co_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.co_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.co_sieci TO operators;
GRANT SELECT ON TABLE gesut7.co_sieci TO users;

ALTER TABLE gesut7.eng_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.eng_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.eng_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.eng_armatura TO operators;
GRANT SELECT ON TABLE gesut7.eng_armatura TO users;

ALTER TABLE gesut7.eng_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.eng_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.eng_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.eng_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.eng_obiekty TO users;

ALTER TABLE gesut7.eng_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.eng_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.eng_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.eng_sieci TO operators;
GRANT SELECT ON TABLE gesut7.eng_sieci TO users;

ALTER TABLE gesut7.gaz_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.gaz_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.gaz_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.gaz_armatura TO operators;
GRANT SELECT ON TABLE gesut7.gaz_armatura TO users;

ALTER TABLE gesut7.gaz_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.gaz_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.gaz_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.gaz_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.gaz_obiekty TO users;

ALTER TABLE gesut7.gaz_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.gaz_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.gaz_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.gaz_sieci TO operators;
GRANT SELECT ON TABLE gesut7.gaz_sieci TO users;

ALTER TABLE gesut7.inne_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.inne_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.inne_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.inne_armatura TO operators;
GRANT SELECT ON TABLE gesut7.inne_armatura TO users;

ALTER TABLE gesut7.inne_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.inne_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.inne_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.inne_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.inne_obiekty TO users;

ALTER TABLE gesut7.inne_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.inne_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.inne_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.inne_sieci TO operators;
GRANT SELECT ON TABLE gesut7.inne_sieci TO users;

ALTER TABLE gesut7.kan_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.kan_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.kan_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.kan_armatura TO operators;
GRANT SELECT ON TABLE gesut7.kan_armatura TO users;

ALTER TABLE gesut7.kan_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.kan_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.kan_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.kan_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.kan_obiekty TO users;

ALTER TABLE gesut7.kan_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.kan_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.kan_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.kan_sieci TO operators;
GRANT SELECT ON TABLE gesut7.kan_sieci TO users;

ALTER TABLE gesut7.spc_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.spc_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.spc_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.spc_armatura TO operators;
GRANT SELECT ON TABLE gesut7.spc_armatura TO users;

ALTER TABLE gesut7.spc_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.spc_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.spc_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.spc_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.spc_obiekty TO users;

ALTER TABLE gesut7.spc_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.spc_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.spc_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.spc_sieci TO operators;
GRANT SELECT ON TABLE gesut7.spc_sieci TO users;

ALTER TABLE gesut7.tel_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.tel_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.tel_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.tel_armatura TO operators;
GRANT SELECT ON TABLE gesut7.tel_armatura TO users;

ALTER TABLE gesut7.tel_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.tel_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.tel_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.tel_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.tel_obiekty TO users;

ALTER TABLE gesut7.tel_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.tel_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.tel_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.tel_sieci TO operators;
GRANT SELECT ON TABLE gesut7.tel_sieci TO users;

ALTER TABLE gesut7.wod_armatura OWNER TO postgres;
GRANT ALL ON TABLE gesut7.wod_armatura TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.wod_armatura TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.wod_armatura TO operators;
GRANT SELECT ON TABLE gesut7.wod_armatura TO users;

ALTER TABLE gesut7.wod_obiekty OWNER TO postgres;
GRANT ALL ON TABLE gesut7.wod_obiekty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.wod_obiekty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.wod_obiekty TO operators;
GRANT SELECT ON TABLE gesut7.wod_obiekty TO users;

ALTER TABLE gesut7.wod_sieci OWNER TO postgres;
GRANT ALL ON TABLE gesut7.wod_sieci TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE gesut7.wod_sieci TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE gesut7.wod_sieci TO operators;
GRANT SELECT ON TABLE gesut7.wod_sieci TO users;

ALTER TABLE sytuacja7.linie OWNER TO postgres;
GRANT ALL ON TABLE sytuacja7.linie TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE sytuacja7.linie TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE sytuacja7.linie TO operators;
GRANT SELECT ON TABLE sytuacja7.linie TO users;

ALTER TABLE sytuacja7.poligony OWNER TO postgres;
GRANT ALL ON TABLE sytuacja7.poligony TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE sytuacja7.poligony TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE sytuacja7.poligony TO operators;
GRANT SELECT ON TABLE sytuacja7.poligony TO users;

ALTER TABLE sytuacja7.punkty OWNER TO postgres;
GRANT ALL ON TABLE sytuacja7.punkty TO postgres;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE sytuacja7.punkty TO admins;
GRANT SELECT, UPDATE, INSERT ON TABLE sytuacja7.punkty TO operators;
GRANT SELECT ON TABLE sytuacja7.punkty TO users;

DROP VIEW  IF EXISTS mobile.co_armatura;
CREATE OR REPLACE VIEW mobile.co_armatura AS 
 SELECT co_armatura.kerg,
    co_armatura.rz_dna,
    co_armatura.rz_terenu,
    co_armatura.g7,
    co_armatura.g7_eco_opi,
    co_armatura.the_geom,
    co_armatura.gid
   FROM gesut7.co_armatura;

ALTER TABLE mobile.co_armatura  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.co_obiekty;
CREATE OR REPLACE VIEW mobile.co_obiekty AS 
 SELECT co_obiekty.rz_terenu,
    co_obiekty.rz_dna,
    co_obiekty.kerg,
    co_obiekty.material,
    co_obiekty.g7,
    co_obiekty.g7_eco_opi,
    co_obiekty.stan,
    co_obiekty.srednic_ww,
    co_obiekty.srednic_zw,
    co_obiekty.the_geom,
    co_obiekty.gid
   FROM gesut7.co_obiekty;

ALTER TABLE mobile.co_obiekty  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.co_sieci;
CREATE OR REPLACE VIEW mobile.co_sieci AS 
 SELECT co_sieci.kerg,
    co_sieci.material,
    co_sieci.izolacja,
    co_sieci.przebieg,
    co_sieci.srednic_ww,
    co_sieci.srednic_zw,
    co_sieci.stan,
    co_sieci.g7,
    co_sieci.g7_eco_opi,
    co_sieci.the_geom,
    co_sieci.gid
   FROM gesut7.co_sieci;

ALTER TABLE mobile.co_sieci  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.eng_armatura;
CREATE OR REPLACE VIEW mobile.eng_armatura AS 
 SELECT eng_armatura.kerg,
    eng_armatura.rz_terenu,
    eng_armatura.g7,
    eng_armatura.g7_pro_opi,
    eng_armatura.the_geom,
    eng_armatura.gid
   FROM gesut7.eng_armatura;

ALTER TABLE mobile.eng_armatura  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.eng_obiekty;
CREATE OR REPLACE VIEW mobile.eng_obiekty AS 
 SELECT eng_obiekty.rz_terenu,
    eng_obiekty.rz_dna,
    eng_obiekty.kerg,
    eng_obiekty.material,
    eng_obiekty.g7,
    eng_obiekty.g7_pro_opi,
    eng_obiekty.srednic_ww,
    eng_obiekty.srednic_zw,
    eng_obiekty.stan,
    eng_obiekty.the_geom,
    eng_obiekty.gid
   FROM gesut7.eng_obiekty;

ALTER TABLE mobile.eng_obiekty  OWNER TO postgres;

DROP VIEW  IF EXISTS  mobile.eng_sieci;
CREATE OR REPLACE VIEW mobile.eng_sieci AS 
 SELECT eng_sieci.kerg,
    eng_sieci.material,
    eng_sieci.izolacja,
    eng_sieci.przebieg,
    eng_sieci.srednic_ww,
    eng_sieci.srednic_zw,
    eng_sieci.stan,
    eng_sieci.g7,
    eng_sieci.g7_pro_opi,
    eng_sieci.the_geom,
    eng_sieci.gid
   FROM gesut7.eng_sieci;

ALTER TABLE mobile.eng_sieci  OWNER TO postgres;

DROP VIEW  IF EXISTS  mobile.gaz_armatura;
CREATE OR REPLACE VIEW mobile.gaz_armatura AS 
 SELECT gaz_armatura.kerg,
    gaz_armatura.rz_dna,
    gaz_armatura.rz_terenu,
    gaz_armatura.g7,
    gaz_armatura.g7_gaz_opi,
    gaz_armatura.the_geom,
    gaz_armatura.gid
   FROM gesut7.gaz_armatura;

ALTER TABLE mobile.gaz_armatura  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.gaz_obiekty;
CREATE OR REPLACE VIEW mobile.gaz_obiekty AS 
 SELECT gaz_obiekty.rz_dna,
    gaz_obiekty.rz_terenu,
    gaz_obiekty.kerg,
    gaz_obiekty.material,
    gaz_obiekty.g7,
    gaz_obiekty.g7_gaz_opi,
    gaz_obiekty.srednic_ww,
    gaz_obiekty.srednic_zw,
    gaz_obiekty.stan,
    gaz_obiekty.the_geom,
    gaz_obiekty.gid
   FROM gesut7.gaz_obiekty;

ALTER TABLE mobile.gaz_obiekty  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.gaz_sieci;
CREATE OR REPLACE VIEW mobile.gaz_sieci AS 
 SELECT gaz_sieci.kerg,
    gaz_sieci.material,
    gaz_sieci.izolacja,
    gaz_sieci.przebieg,
    gaz_sieci.srednic_ww,
    gaz_sieci.srednic_zw,
    gaz_sieci.stan,
    gaz_sieci.g7,
    gaz_sieci.g7_gaz_opi,
    gaz_sieci.the_geom,
    gaz_sieci.gid
   FROM gesut7.gaz_sieci;

ALTER TABLE mobile.gaz_sieci  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.kan_armatura_c;
CREATE OR REPLACE VIEW mobile.kan_armatura_c AS 
 SELECT kan_armatura.miejscowos,
    kan_armatura.ulica,
    kan_armatura.no_ulica,
    kan_armatura.dzialka,
    kan_armatura.adres,
    kan_armatura.obreb,
    kan_armatura.wlasciciel,
    kan_armatura.rok,
    kan_armatura.zrodlo,
    kan_armatura.stan,
    kan_armatura.typ,
    kan_armatura.armatura,
    kan_armatura.g7_kan,
    kan_armatura.g7_kan_opis,
    kan_armatura.k1_kan,
    kan_armatura.kerg,
    kan_armatura.rz_terenu,
    kan_armatura.rz_dna,
    kan_armatura.rz_wejscia,
    kan_armatura.rz_wyjscia,
    kan_armatura.posadowien,
    kan_armatura.material,
    kan_armatura.srednic,
    kan_armatura.srednic_wl,
    kan_armatura.srednic_wy,
    kan_armatura.uwagi,
    kan_armatura.szkic,
    kan_armatura.mapa,
    kan_armatura.projekt,
    kan_armatura.film,
    kan_armatura.zdjecie,
    kan_armatura.plik,
    kan_armatura.zdarzenie,
    kan_armatura.zdarzenie_opis,
    kan_armatura.opis,
    kan_armatura.tekst,
    kan_armatura.dodal,
    kan_armatura.dodal_dnia,
    kan_armatura.zmienil,
    kan_armatura.zmienil_dnia,
    kan_armatura.nr_inwentarzowy,
    kan_armatura.nr_sr_trwalego,
    kan_armatura.identyfikator,
    kan_armatura.creation_date,
    kan_armatura.modification_date,
    kan_armatura.version,
    kan_armatura.status,
    kan_armatura.the_geom,
    kan_armatura.id,
    kan_armatura.gid,
    kan_armatura.odbior
   FROM pwik.kan_armatura  WHERE kan_armatura.stan = 'c'::text OR kan_armatura.stan = 'p'::text;

ALTER TABLE mobile.kan_armatura_c  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.kan_armatura_n;
CREATE OR REPLACE VIEW mobile.kan_armatura_n AS 
 SELECT kan_armatura.miejscowos,
    kan_armatura.ulica,
    kan_armatura.no_ulica,
    kan_armatura.dzialka,
    kan_armatura.adres,
    kan_armatura.obreb,
    kan_armatura.wlasciciel,
    kan_armatura.rok,
    kan_armatura.zrodlo,
    kan_armatura.stan,
    kan_armatura.typ,
    kan_armatura.armatura,
    kan_armatura.g7_kan,
    kan_armatura.g7_kan_opis,
    kan_armatura.k1_kan,
    kan_armatura.kerg,
    kan_armatura.rz_terenu,
    kan_armatura.rz_dna,
    kan_armatura.rz_wejscia,
    kan_armatura.rz_wyjscia,
    kan_armatura.posadowien,
    kan_armatura.material,
    kan_armatura.srednic,
    kan_armatura.srednic_wl,
    kan_armatura.srednic_wy,
    kan_armatura.uwagi,
    kan_armatura.szkic,
    kan_armatura.mapa,
    kan_armatura.projekt,
    kan_armatura.film,
    kan_armatura.zdjecie,
    kan_armatura.plik,
    kan_armatura.zdarzenie,
    kan_armatura.zdarzenie_opis,
    kan_armatura.opis,
    kan_armatura.tekst,
    kan_armatura.dodal,
    kan_armatura.dodal_dnia,
    kan_armatura.zmienil,
    kan_armatura.zmienil_dnia,
    kan_armatura.nr_inwentarzowy,
    kan_armatura.nr_sr_trwalego,
    kan_armatura.identyfikator,
    kan_armatura.creation_date,
    kan_armatura.modification_date,
    kan_armatura.version,
    kan_armatura.status,
    kan_armatura.the_geom,
    kan_armatura.id,
    kan_armatura.gid,
    kan_armatura.odbior
   FROM pwik.kan_armatura  WHERE kan_armatura.stan = 'n'::text OR kan_armatura.stan = 'u'::text;

ALTER TABLE mobile.kan_armatura_n  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.kan_obiekty_c ;
CREATE OR REPLACE VIEW mobile.kan_obiekty_c AS 
 SELECT kan_obiekty.miejscowos,
    kan_obiekty.ulica,
    kan_obiekty.no_ulica,
    kan_obiekty.dzialka,
    kan_obiekty.adres,
    kan_obiekty.obreb,
    kan_obiekty.wlasciciel,
    kan_obiekty.rok,
    kan_obiekty.zrodlo,
    kan_obiekty.stan,
    kan_obiekty.typ,
    kan_obiekty.obiekt,
    kan_obiekty.g7_kan,
    kan_obiekty.g7_kan_opis,
    kan_obiekty.k1_kan,
    kan_obiekty.kerg,
    kan_obiekty.przebieg,
    kan_obiekty.nazwa,
    kan_obiekty.rz_terenu,
    kan_obiekty.rz_dna,
    kan_obiekty.posadowien,
    kan_obiekty.material,
    kan_obiekty.srednic,
    kan_obiekty.srednic_ww,
    kan_obiekty.srednic_zw,
    kan_obiekty.dlugosc,
    kan_obiekty.uwagi,
    kan_obiekty.szkic,
    kan_obiekty.mapa,
    kan_obiekty.projekt,
    kan_obiekty.film,
    kan_obiekty.zdjecie,
    kan_obiekty.plik,
    kan_obiekty.zdarzenie,
    kan_obiekty.zdarzenie_opis,
    kan_obiekty.opis,
    kan_obiekty.tekst,
    kan_obiekty.dodal,
    kan_obiekty.dodal_dnia,
    kan_obiekty.zmienil,
    kan_obiekty.zmienil_dnia,
    kan_obiekty.nr_inwentarzowy,
    kan_obiekty.nr_sr_trwalego,
    kan_obiekty.identyfikator,
    kan_obiekty.creation_date,
    kan_obiekty.modification_date,
    kan_obiekty.version,
    kan_obiekty.status,
    kan_obiekty.the_geom,
    kan_obiekty.id,
    kan_obiekty.gid,
    kan_obiekty.odbior
   FROM pwik.kan_obiekty  WHERE kan_obiekty.stan = 'c'::text OR kan_obiekty.stan = 'p'::text;

ALTER TABLE mobile.kan_obiekty_c  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.kan_obiekty_n;
CREATE OR REPLACE VIEW mobile.kan_obiekty_n AS 
 SELECT kan_obiekty.miejscowos,
    kan_obiekty.ulica,
    kan_obiekty.no_ulica,
    kan_obiekty.dzialka,
    kan_obiekty.adres,
    kan_obiekty.obreb,
    kan_obiekty.wlasciciel,
    kan_obiekty.rok,
    kan_obiekty.zrodlo,
    kan_obiekty.stan,
    kan_obiekty.typ,
    kan_obiekty.obiekt,
    kan_obiekty.g7_kan,
    kan_obiekty.g7_kan_opis,
    kan_obiekty.k1_kan,
    kan_obiekty.kerg,
    kan_obiekty.przebieg,
    kan_obiekty.nazwa,
    kan_obiekty.rz_terenu,
    kan_obiekty.rz_dna,
    kan_obiekty.posadowien,
    kan_obiekty.material,
    kan_obiekty.srednic,
    kan_obiekty.srednic_ww,
    kan_obiekty.srednic_zw,
    kan_obiekty.dlugosc,
    kan_obiekty.uwagi,
    kan_obiekty.szkic,
    kan_obiekty.mapa,
    kan_obiekty.projekt,
    kan_obiekty.film,
    kan_obiekty.zdjecie,
    kan_obiekty.plik,
    kan_obiekty.zdarzenie,
    kan_obiekty.zdarzenie_opis,
    kan_obiekty.opis,
    kan_obiekty.tekst,
    kan_obiekty.dodal,
    kan_obiekty.dodal_dnia,
    kan_obiekty.zmienil,
    kan_obiekty.zmienil_dnia,
    kan_obiekty.nr_inwentarzowy,
    kan_obiekty.nr_sr_trwalego,
    kan_obiekty.identyfikator,
    kan_obiekty.creation_date,
    kan_obiekty.modification_date,
    kan_obiekty.version,
    kan_obiekty.status,
    kan_obiekty.the_geom,
    kan_obiekty.id,
    kan_obiekty.gid,
    kan_obiekty.odbior
   FROM pwik.kan_obiekty  WHERE kan_obiekty.stan = 'n'::text OR kan_obiekty.stan = 'u'::text;

ALTER TABLE mobile.kan_obiekty_n  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.kan_sieci_c ;
CREATE OR REPLACE VIEW mobile.kan_sieci_c AS 
 SELECT kan_sieci.miejscowos,
    kan_sieci.ulica,
    kan_sieci.no_ulica,
    kan_sieci.dzialka,
    kan_sieci.adres,
    kan_sieci.obreb,
    kan_sieci.wlasciciel,
    kan_sieci.rok,
    kan_sieci.zrodlo,
    kan_sieci.stan,
    kan_sieci.typ,
    kan_sieci.funkcja,
    kan_sieci.g7_kan,
    kan_sieci.g7_kan_opis,
    kan_sieci.k1_kan,
    kan_sieci.kerg,
    kan_sieci.izolacja,
    kan_sieci.przebieg,
    kan_sieci.posadowien,
    kan_sieci.material,
    kan_sieci.srednic,
    kan_sieci.srednic_ww,
    kan_sieci.srednic_zw,
    kan_sieci.cisnienie,
    kan_sieci.dlugosc,
    kan_sieci.uwagi,
    kan_sieci.szkic,
    kan_sieci.mapa,
    kan_sieci.projekt,
    kan_sieci.film,
    kan_sieci.zdjecie,
    kan_sieci.plik,
    kan_sieci.zdarzenie,
    kan_sieci.zdarzenie_opis,
    kan_sieci.opis,
    kan_sieci.tekst,
    kan_sieci.dodal,
    kan_sieci.dodal_dnia,
    kan_sieci.zmienil,
    kan_sieci.zmienil_dnia,
    kan_sieci.nr_inwentarzowy,
    kan_sieci.nr_sr_trwalego,
    kan_sieci.identyfikator,
    kan_sieci.creation_date,
    kan_sieci.modification_date,
    kan_sieci.version,
    kan_sieci.status,
    kan_sieci.the_geom,
    kan_sieci.id,
    kan_sieci.gid,
    kan_sieci.odbior
   FROM pwik.kan_sieci  WHERE kan_sieci.stan = 'c'::text OR kan_sieci.stan = 'p'::text;

ALTER TABLE mobile.kan_sieci_c  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.kan_sieci_n;
CREATE OR REPLACE VIEW mobile.kan_sieci_n AS 
 SELECT kan_sieci.miejscowos,
    kan_sieci.ulica,
    kan_sieci.no_ulica,
    kan_sieci.dzialka,
    kan_sieci.adres,
    kan_sieci.obreb,
    kan_sieci.wlasciciel,
    kan_sieci.rok,
    kan_sieci.zrodlo,
    kan_sieci.stan,
    kan_sieci.typ,
    kan_sieci.funkcja,
    kan_sieci.g7_kan,
    kan_sieci.g7_kan_opis,
    kan_sieci.k1_kan,
    kan_sieci.kerg,
    kan_sieci.izolacja,
    kan_sieci.przebieg,
    kan_sieci.posadowien,
    kan_sieci.material,
    kan_sieci.srednic,
    kan_sieci.srednic_ww,
    kan_sieci.srednic_zw,
    kan_sieci.cisnienie,
    kan_sieci.dlugosc,
    kan_sieci.uwagi,
    kan_sieci.szkic,
    kan_sieci.mapa,
    kan_sieci.projekt,
    kan_sieci.film,
    kan_sieci.zdjecie,
    kan_sieci.plik,
    kan_sieci.zdarzenie,
    kan_sieci.zdarzenie_opis,
    kan_sieci.opis,
    kan_sieci.tekst,
    kan_sieci.dodal,
    kan_sieci.dodal_dnia,
    kan_sieci.zmienil,
    kan_sieci.zmienil_dnia,
    kan_sieci.nr_inwentarzowy,
    kan_sieci.nr_sr_trwalego,
    kan_sieci.identyfikator,
    kan_sieci.creation_date,
    kan_sieci.modification_date,
    kan_sieci.version,
    kan_sieci.status,
    kan_sieci.the_geom,
    kan_sieci.id,
    kan_sieci.gid,
    kan_sieci.odbior
   FROM pwik.kan_sieci  WHERE kan_sieci.stan = 'n'::text OR kan_sieci.stan = 'u'::text;

ALTER TABLE mobile.kan_sieci_n  OWNER TO postgres;

drop view  IF EXISTS  mobile.linie;
CREATE OR REPLACE VIEW mobile.linie AS 
 SELECT linie.xcode_d,
    linie.g7,
    linie.the_geom,
    linie.gid
   FROM sytuacja7.linie;

ALTER TABLE mobile.linie  OWNER TO postgres;

drop view  IF EXISTS  mobile.poligony;
CREATE OR REPLACE VIEW mobile.poligony AS 
 SELECT poligony.xcode_d,
    poligony.g7,
    poligony.the_geom,
    poligony.gid
   FROM sytuacja7.poligony;

ALTER TABLE mobile.poligony  OWNER TO postgres;

drop view  IF EXISTS  mobile.punkty;
CREATE OR REPLACE VIEW mobile.punkty AS 
 SELECT punkty.xcode_d,
    punkty.g7,
    punkty.the_geom,
    punkty.gid
   FROM sytuacja7.punkty;

ALTER TABLE mobile.punkty  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.spc_armatura;
CREATE OR REPLACE VIEW mobile.spc_armatura AS 
 SELECT spc_armatura.kerg,
    spc_armatura.rz_dna,
    spc_armatura.rz_terenu,
    spc_armatura.g7,
    spc_armatura.g7_tel_opi,
    spc_armatura.the_geom,
    spc_armatura.gid
   FROM gesut7.spc_armatura;

ALTER TABLE mobile.spc_armatura  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.spc_obiekty;
CREATE OR REPLACE VIEW mobile.spc_obiekty AS 
 SELECT spc_obiekty.rz_terenu,
    spc_obiekty.rz_dna,
    spc_obiekty.kerg,
    spc_obiekty.material,
    spc_obiekty.g7,
    spc_obiekty.g7_tel_opi,
    spc_obiekty.srednic_ww,
    spc_obiekty.srednic_zw,
    spc_obiekty.stan,
    spc_obiekty.the_geom,
    spc_obiekty.gid
   FROM gesut7.spc_obiekty;

ALTER TABLE mobile.spc_obiekty  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.spc_sieci;
CREATE OR REPLACE VIEW mobile.spc_sieci AS 
 SELECT spc_sieci.kerg,
    spc_sieci.material,
    spc_sieci.izolacja,
    spc_sieci.przebieg,
    spc_sieci.srednic_ww,
    spc_sieci.srednic_zw,
    spc_sieci.stan,
    spc_sieci.g7,
    spc_sieci.g7_tel_opi,
    spc_sieci.the_geom,
    spc_sieci.gid
   FROM gesut7.spc_sieci;

ALTER TABLE mobile.spc_sieci  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.tel_armatura;
CREATE OR REPLACE VIEW mobile.tel_armatura AS 
 SELECT tel_armatura.kerg,
    tel_armatura.rz_terenu,
    tel_armatura.g7,
    tel_armatura.g7_tel_opi,
    tel_armatura.the_geom,
    tel_armatura.gid
   FROM gesut7.tel_armatura;

ALTER TABLE mobile.tel_armatura  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.tel_obiekty;
CREATE OR REPLACE VIEW mobile.tel_obiekty AS 
 SELECT tel_obiekty.rz_terenu,
    tel_obiekty.rz_dna,
    tel_obiekty.kerg,
    tel_obiekty.material,
    tel_obiekty.g7,
    tel_obiekty.g7_tel_opi,
    tel_obiekty.srednic_ww,
    tel_obiekty.srednic_zw,
    tel_obiekty.stan,
    tel_obiekty.the_geom,
    tel_obiekty.gid
   FROM gesut7.tel_obiekty;

ALTER TABLE mobile.tel_obiekty  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.tel_sieci;
CREATE OR REPLACE VIEW mobile.tel_sieci AS 
 SELECT tel_sieci.kerg,
    tel_sieci.material,
    tel_sieci.izolacja,
    tel_sieci.przebieg,
    tel_sieci.srednic_ww,
    tel_sieci.srednic_zw,
    tel_sieci.stan,
    tel_sieci.g7,
    tel_sieci.g7_tel_opi,
    tel_sieci.the_geom,
    tel_sieci.gid
   FROM gesut7.tel_sieci;

ALTER TABLE mobile.tel_sieci  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.wod_armatura_c ;
CREATE OR REPLACE VIEW mobile.wod_armatura_c AS 
 SELECT wod_armatura.miejscowos,
    wod_armatura.ulica,
    wod_armatura.no_ulica,
    wod_armatura.dzialka,
    wod_armatura.adres,
    wod_armatura.obreb,
    wod_armatura.wlasciciel,
    wod_armatura.rok,
    wod_armatura.zrodlo,
    wod_armatura.stan,
    wod_armatura.typ,
    wod_armatura.armatura,
    wod_armatura.g7_wod,
    wod_armatura.g7_wod_opis,
    wod_armatura.k1_wod,
    wod_armatura.kerg,
    wod_armatura.rz_terenu,
    wod_armatura.rz_dna,
    wod_armatura.posadowien,
    wod_armatura.material,
    wod_armatura.srednic,
    wod_armatura.srednic_wl,
    wod_armatura.srednic_wy,
    wod_armatura.uwagi,
    wod_armatura.szkic,
    wod_armatura.mapa,
    wod_armatura.projekt,
    wod_armatura.film,
    wod_armatura.zdjecie,
    wod_armatura.plik,
    wod_armatura.zdarzenie,
    wod_armatura.zdarzenie_opis,
    wod_armatura.opis,
    wod_armatura.tekst,
    wod_armatura.dodal,
    wod_armatura.dodal_dnia,
    wod_armatura.zmienil,
    wod_armatura.zmienil_dnia,
    wod_armatura.nr_inwentarzowy,
    wod_armatura.nr_sr_trwalego,
    wod_armatura.identyfikator,
    wod_armatura.creation_date,
    wod_armatura.modification_date,
    wod_armatura.version,
    wod_armatura.status,
    wod_armatura.the_geom,
    wod_armatura.id,
    wod_armatura.gid,
    wod_armatura.odbior
   FROM pwik.wod_armatura  WHERE wod_armatura.stan = 'c'::text OR wod_armatura.stan = 'p'::text;

ALTER TABLE mobile.wod_armatura_c  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.wod_armatura_n;
CREATE OR REPLACE VIEW mobile.wod_armatura_n AS 
 SELECT wod_armatura.miejscowos,
    wod_armatura.ulica,
    wod_armatura.no_ulica,
    wod_armatura.dzialka,
    wod_armatura.adres,
    wod_armatura.obreb,
    wod_armatura.wlasciciel,
    wod_armatura.rok,
    wod_armatura.zrodlo,
    wod_armatura.stan,
    wod_armatura.typ,
    wod_armatura.armatura,
    wod_armatura.g7_wod,
    wod_armatura.g7_wod_opis,
    wod_armatura.k1_wod,
    wod_armatura.kerg,
    wod_armatura.rz_terenu,
    wod_armatura.rz_dna,
    wod_armatura.posadowien,
    wod_armatura.material,
    wod_armatura.srednic,
    wod_armatura.srednic_wl,
    wod_armatura.srednic_wy,
    wod_armatura.uwagi,
    wod_armatura.szkic,
    wod_armatura.mapa,
    wod_armatura.projekt,
    wod_armatura.film,
    wod_armatura.zdjecie,
    wod_armatura.plik,
    wod_armatura.zdarzenie,
    wod_armatura.zdarzenie_opis,
    wod_armatura.opis,
    wod_armatura.tekst,
    wod_armatura.dodal,
    wod_armatura.dodal_dnia,
    wod_armatura.zmienil,
    wod_armatura.zmienil_dnia,
    wod_armatura.nr_inwentarzowy,
    wod_armatura.nr_sr_trwalego,
    wod_armatura.identyfikator,
    wod_armatura.creation_date,
    wod_armatura.modification_date,
    wod_armatura.version,
    wod_armatura.status,
    wod_armatura.the_geom,
    wod_armatura.id,
    wod_armatura.gid,
    wod_armatura.odbior
   FROM pwik.wod_armatura  WHERE wod_armatura.stan = 'n'::text OR wod_armatura.stan = 'u'::text;

ALTER TABLE mobile.wod_armatura_n  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.wod_obiekty_c ;
CREATE OR REPLACE VIEW mobile.wod_obiekty_c AS 
 SELECT wod_obiekty.miejscowos,
    wod_obiekty.ulica,
    wod_obiekty.no_ulica,
    wod_obiekty.dzialka,
    wod_obiekty.adres,
    wod_obiekty.obreb,
    wod_obiekty.wlasciciel,
    wod_obiekty.rok,
    wod_obiekty.zrodlo,
    wod_obiekty.stan,
    wod_obiekty.typ,
    wod_obiekty.obiekt,
    wod_obiekty.g7_wod,
    wod_obiekty.g7_wod_opis,
    wod_obiekty.k1_wod,
    wod_obiekty.kerg,
    wod_obiekty.przebieg,
    wod_obiekty.nazwa,
    wod_obiekty.rz_terenu,
    wod_obiekty.rz_dna,
    wod_obiekty.posadowien,
    wod_obiekty.material,
    wod_obiekty.srednic,
    wod_obiekty.srednic_ww,
    wod_obiekty.srednic_zw,
    wod_obiekty.dlugosc,
    wod_obiekty.uwagi,
    wod_obiekty.szkic,
    wod_obiekty.mapa,
    wod_obiekty.projekt,
    wod_obiekty.film,
    wod_obiekty.zdjecie,
    wod_obiekty.plik,
    wod_obiekty.zdarzenie,
    wod_obiekty.zdarzenie_opis,
    wod_obiekty.opis,
    wod_obiekty.tekst,
    wod_obiekty.dodal,
    wod_obiekty.dodal_dnia,
    wod_obiekty.zmienil,
    wod_obiekty.zmienil_dnia,
    wod_obiekty.nr_inwentarzowy,
    wod_obiekty.nr_sr_trwalego,
    wod_obiekty.identyfikator,
    wod_obiekty.creation_date,
    wod_obiekty.modification_date,
    wod_obiekty.version,
    wod_obiekty.status,
    wod_obiekty.the_geom,
    wod_obiekty.id,
    wod_obiekty.gid,
    wod_obiekty.odbior
   FROM pwik.wod_obiekty  WHERE wod_obiekty.stan = 'c'::text OR wod_obiekty.stan = 'p'::text;

ALTER TABLE mobile.wod_obiekty_c  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.wod_obiekty_n;
CREATE OR REPLACE VIEW mobile.wod_obiekty_n AS 
 SELECT wod_obiekty.miejscowos,
    wod_obiekty.ulica,
    wod_obiekty.no_ulica,
    wod_obiekty.dzialka,
    wod_obiekty.adres,
    wod_obiekty.obreb,
    wod_obiekty.wlasciciel,
    wod_obiekty.rok,
    wod_obiekty.zrodlo,
    wod_obiekty.stan,
    wod_obiekty.typ,
    wod_obiekty.obiekt,
    wod_obiekty.g7_wod,
    wod_obiekty.g7_wod_opis,
    wod_obiekty.k1_wod,
    wod_obiekty.kerg,
    wod_obiekty.przebieg,
    wod_obiekty.nazwa,
    wod_obiekty.rz_terenu,
    wod_obiekty.rz_dna,
    wod_obiekty.posadowien,
    wod_obiekty.material,
    wod_obiekty.srednic,
    wod_obiekty.srednic_ww,
    wod_obiekty.srednic_zw,
    wod_obiekty.dlugosc,
    wod_obiekty.uwagi,
    wod_obiekty.szkic,
    wod_obiekty.mapa,
    wod_obiekty.projekt,
    wod_obiekty.film,
    wod_obiekty.zdjecie,
    wod_obiekty.plik,
    wod_obiekty.zdarzenie,
    wod_obiekty.zdarzenie_opis,
    wod_obiekty.opis,
    wod_obiekty.tekst,
    wod_obiekty.dodal,
    wod_obiekty.dodal_dnia,
    wod_obiekty.zmienil,
    wod_obiekty.zmienil_dnia,
    wod_obiekty.nr_inwentarzowy,
    wod_obiekty.nr_sr_trwalego,
    wod_obiekty.identyfikator,
    wod_obiekty.creation_date,
    wod_obiekty.modification_date,
    wod_obiekty.version,
    wod_obiekty.status,
    wod_obiekty.the_geom,
    wod_obiekty.id,
    wod_obiekty.gid,
    wod_obiekty.odbior
   FROM pwik.wod_obiekty  WHERE wod_obiekty.stan = 'n'::text OR wod_obiekty.stan = 'u'::text;

ALTER TABLE mobile.wod_obiekty_n  OWNER TO postgres;

DROP VIEW  IF EXISTS  mobile.wod_sieci_c;
CREATE OR REPLACE VIEW mobile.wod_sieci_c AS 
 SELECT wod_sieci.miejscowos,
    wod_sieci.ulica,
    wod_sieci.no_ulica,
    wod_sieci.dzialka,
    wod_sieci.adres,
    wod_sieci.obreb,
    wod_sieci.wlasciciel,
    wod_sieci.rok,
    wod_sieci.zrodlo,
    wod_sieci.stan,
    wod_sieci.typ,
    wod_sieci.funkcja,
    wod_sieci.g7_wod,
    wod_sieci.g7_wod_opis,
    wod_sieci.k1_wod,
    wod_sieci.kerg,
    wod_sieci.izolacja,
    wod_sieci.przebieg,
    wod_sieci.posadowien,
    wod_sieci.material,
    wod_sieci.srednic,
    wod_sieci.srednic_ww,
    wod_sieci.srednic_zw,
    wod_sieci.cisnienie,
    wod_sieci.dlugosc,
    wod_sieci.uwagi,
    wod_sieci.szkic,
    wod_sieci.mapa,
    wod_sieci.projekt,
    wod_sieci.film,
    wod_sieci.zdjecie,
    wod_sieci.plik,
    wod_sieci.zdarzenie,
    wod_sieci.zdarzenie_opis,
    wod_sieci.opis,
    wod_sieci.tekst,
    wod_sieci.dodal,
    wod_sieci.dodal_dnia,
    wod_sieci.zmienil,
    wod_sieci.zmienil_dnia,
    wod_sieci.nr_inwentarzowy,
    wod_sieci.nr_sr_trwalego,
    wod_sieci.identyfikator,
    wod_sieci.creation_date,
    wod_sieci.modification_date,
    wod_sieci.version,
    wod_sieci.status,
    wod_sieci.the_geom,
    wod_sieci.id,
    wod_sieci.gid,
    wod_sieci.odbior
   FROM pwik.wod_sieci  WHERE wod_sieci.stan = 'c'::text OR wod_sieci.stan = 'p'::text;

ALTER TABLE mobile.wod_sieci_c  OWNER TO postgres;

DROP VIEW  IF EXISTS mobile.wod_sieci_n;
CREATE OR REPLACE VIEW mobile.wod_sieci_n AS 
 SELECT wod_sieci.miejscowos,
    wod_sieci.ulica,
    wod_sieci.no_ulica,
    wod_sieci.dzialka,
    wod_sieci.adres,
    wod_sieci.obreb,
    wod_sieci.wlasciciel,
    wod_sieci.rok,
    wod_sieci.zrodlo,
    wod_sieci.stan,
    wod_sieci.typ,
    wod_sieci.funkcja,
    wod_sieci.g7_wod,
    wod_sieci.g7_wod_opis,
    wod_sieci.k1_wod,
    wod_sieci.kerg,
    wod_sieci.izolacja,
    wod_sieci.przebieg,
    wod_sieci.posadowien,
    wod_sieci.material,
    wod_sieci.srednic,
    wod_sieci.srednic_ww,
    wod_sieci.srednic_zw,
    wod_sieci.cisnienie,
    wod_sieci.dlugosc,
    wod_sieci.uwagi,
    wod_sieci.szkic,
    wod_sieci.mapa,
    wod_sieci.projekt,
    wod_sieci.film,
    wod_sieci.zdjecie,
    wod_sieci.plik,
    wod_sieci.zdarzenie,
    wod_sieci.zdarzenie_opis,
    wod_sieci.opis,
    wod_sieci.tekst,
    wod_sieci.dodal,
    wod_sieci.dodal_dnia,
    wod_sieci.zmienil,
    wod_sieci.zmienil_dnia,
    wod_sieci.nr_inwentarzowy,
    wod_sieci.nr_sr_trwalego,
    wod_sieci.identyfikator,
    wod_sieci.creation_date,
    wod_sieci.modification_date,
    wod_sieci.version,
    wod_sieci.status,
    wod_sieci.the_geom,
    wod_sieci.id,
    wod_sieci.gid,
    wod_sieci.odbior
   FROM pwik.wod_sieci  WHERE wod_sieci.stan = 'n'::text OR wod_sieci.stan = 'u'::text;

ALTER TABLE mobile.wod_sieci_n  OWNER TO postgres;

DROP VIEW  IF EXISTS pwik.wod_sieci_del ;
CREATE OR REPLACE VIEW pwik.wod_sieci_del AS 
 SELECT k1.gid as mid, k1.identyfikator, k1.creation_date, k1.modification_date, k1.version, k1.status, 
 k1.id, k1.miejscowos, k1.ulica, k1.no_ulica, k1.dzialka, k1.adres, k1.obreb, k1.wlasciciel, 
       k1.rok, k1.zrodlo, k1.stan, k1.typ, k1.funkcja, k1.g7_wod, k1.g7_wod_opis, k1.k1_wod, 
       k1.kerg, k1.izolacja, k1.przebieg, k1.posadowien, k1.material, k1.srednic, k1.srednic_ww, 
       k1.srednic_zw, k1.cisnienie, k1.dlugosc, k1.uwagi, k1.szkic, k1.mapa, k1.projekt, 
       k1.film, k1.zdjecie, k1.plik, k1.zdarzenie, k1.zdarzenie_opis, k1.opis, k1.tekst, 
       k1.dodal, k1.dodal_dnia, k1.zmienil, k1.zmienil_dnia, k1.nr_inwentarzowy, k1.nr_sr_trwalego, 
       k1.the_geom, k1.odbior
  FROM pwik.wod_sieci k1 LEFT JOIN gesut7.wod_sieci k2 USING (identyfikator)  WHERE k2.identyfikator IS NULL and k1.identyfikator is not null and k1.stan<>'u';

ALTER TABLE pwik.wod_sieci_del OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_sieci_del TO postgres;
GRANT SELECT ON TABLE pwik.wod_sieci_del TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_sieci_del TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_sieci_del TO admins;

DROP VIEW  IF EXISTS pwik.wod_obiekty_del;
CREATE OR REPLACE VIEW pwik.wod_obiekty_del AS 
 SELECT k1.gid as mid, k1.id, k1.identyfikator, k1.creation_date, k1.modification_date, 
       k1.version, k1.status, k1.miejscowos, k1.ulica, k1.no_ulica, k1.dzialka, k1.adres, k1.obreb, k1.wlasciciel, 
       k1.rok, k1.zrodlo, k1.stan, k1.typ, k1.obiekt, k1.g7_wod, k1.g7_wod_opis, k1.k1_wod, 
       k1.kerg, k1.przebieg, k1.nazwa, k1.rz_terenu, k1.rz_dna, k1.posadowien, k1.material, 
       k1.srednic, k1.srednic_ww, k1.srednic_zw, k1.dlugosc, k1.uwagi, k1.szkic, k1.mapa, 
       k1.projekt, k1.film, k1.zdjecie, k1.plik, k1.zdarzenie, k1.zdarzenie_opis, k1.opis, 
       k1.tekst, k1.dodal, k1.dodal_dnia, k1.zmienil, k1.zmienil_dnia, k1.nr_inwentarzowy, 
       k1.nr_sr_trwalego, k1.the_geom, k1.odbior
   FROM pwik.wod_obiekty k1  LEFT JOIN gesut7.wod_obiekty k2 USING (identyfikator)  WHERE k2.identyfikator IS NULL and k1.identyfikator is not null and k1.stan<>'u';

ALTER TABLE pwik.wod_obiekty_del OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_obiekty_del TO postgres;
GRANT SELECT ON TABLE pwik.wod_obiekty_del TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_obiekty_del TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_obiekty_del TO admins;

DROP VIEW  IF EXISTS pwik.wod_armatura_del;
CREATE OR REPLACE VIEW pwik.wod_armatura_del AS 
 SELECT k1.gid as mid, k1.id, k1.identyfikator, 
       k1.creation_date, k1.modification_date, k1.version, k1.status, k1.miejscowos, k1.ulica, k1.no_ulica, k1.dzialka, k1.adres, k1.obreb, k1.wlasciciel, 
       k1.rok, k1.zrodlo, k1.stan, k1.typ, k1.armatura, k1.g7_wod, k1.g7_wod_opis, k1.k1_wod, 
       k1.kerg, k1.rz_terenu, k1.rz_dna, k1.posadowien, k1.material, k1.srednic, k1.srednic_wl, 
       k1.srednic_wy, k1.uwagi, k1.szkic, k1.mapa, k1.projekt, k1.film, k1.zdjecie, k1.plik, 
       k1.zdarzenie, k1.zdarzenie_opis, k1.opis, k1.tekst, k1.dodal, k1.dodal_dnia, k1.zmienil, 
       k1.zmienil_dnia, k1.nr_inwentarzowy, k1.nr_sr_trwalego, k1.the_geom, 
       k1.odbior
   FROM pwik.wod_armatura k1 LEFT JOIN gesut7.wod_armatura k2 USING (identyfikator)  WHERE k2.identyfikator IS NULL and k1.identyfikator is not null and k1.stan<>'u';

ALTER TABLE pwik.wod_armatura_del OWNER TO postgres;
GRANT ALL ON TABLE pwik.wod_armatura_del TO postgres;
GRANT SELECT ON TABLE pwik.wod_armatura_del TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.wod_armatura_del TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.wod_armatura_del TO admins;

DROP VIEW  IF EXISTS pwik.kan_sieci_del ;
CREATE OR REPLACE VIEW pwik.kan_sieci_del AS 
 SELECT k1.gid as mid, k1.id, k1.identyfikator, k1.creation_date, k1.modification_date, k1.version, k1.status, 
 k1.miejscowos, k1.ulica, k1.no_ulica, k1.dzialka, k1.adres, k1.obreb, k1.wlasciciel, 
       k1.rok, k1.zrodlo, k1.stan, k1.typ, k1.funkcja, k1.g7_kan, k1.g7_kan_opis, k1.k1_kan, 
       k1.kerg, k1.izolacja, k1.przebieg, k1.posadowien, k1.material, k1.srednic, k1.srednic_ww, 
       k1.srednic_zw, k1.cisnienie, k1.dlugosc, k1.uwagi, k1.szkic, k1.mapa, k1.projekt, 
       k1.film, k1.zdjecie, k1.plik, k1.zdarzenie, k1.zdarzenie_opis, k1.opis, k1.tekst, 
       k1.dodal, k1.dodal_dnia, k1.zmienil, k1.zmienil_dnia, k1.nr_inwentarzowy, k1.nr_sr_trwalego, 
       k1.the_geom, k1.odbior
   FROM pwik.kan_sieci k1 LEFT JOIN gesut7.kan_sieci k2 USING (identyfikator)  WHERE k2.identyfikator IS NULL and k1.identyfikator is not null and k1.stan<>'u';

ALTER TABLE pwik.kan_sieci_del OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_sieci_del TO postgres;
GRANT SELECT ON TABLE pwik.kan_sieci_del TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_sieci_del TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_sieci_del TO admins;

DROP VIEW  IF EXISTS pwik.kan_obiekty_del;
CREATE OR REPLACE VIEW pwik.kan_obiekty_del AS 
 SELECT  k1.gid as mid, k1.id, k1.identyfikator, k1.creation_date, k1.modification_date, 
       k1.version, k1.status, k1.miejscowos, k1.ulica, k1.no_ulica, k1.dzialka, k1.adres, k1.obreb, k1.wlasciciel, 
       k1.rok, k1.zrodlo, k1.stan, k1.typ, k1.obiekt, k1.g7_kan, k1.g7_kan_opis, k1.k1_kan, 
       k1.kerg, k1.przebieg, k1.nazwa, k1.rz_terenu, k1.rz_dna, k1.posadowien, k1.material, 
       k1.srednic, k1.srednic_ww, k1.srednic_zw, k1.dlugosc, k1.uwagi, k1.szkic, k1.mapa, 
       k1.projekt, k1.film, k1.zdjecie, k1.plik, k1.zdarzenie, k1.zdarzenie_opis, k1.opis, 
       k1.tekst, k1.dodal, k1.dodal_dnia, k1.zmienil, k1.zmienil_dnia, k1.nr_inwentarzowy, 
       k1.nr_sr_trwalego, k1.the_geom, k1.odbior
   FROM pwik.kan_obiekty k1 LEFT JOIN gesut7.kan_obiekty k2 USING (identyfikator)  WHERE k2.identyfikator IS NULL and k1.identyfikator is not null and k1.stan<>'u';

ALTER TABLE pwik.kan_obiekty_del OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_obiekty_del TO postgres;
GRANT SELECT ON TABLE pwik.kan_obiekty_del TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_obiekty_del TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_obiekty_del TO admins;

DROP VIEW  IF EXISTS pwik.kan_armatura_del;
CREATE OR REPLACE VIEW pwik.kan_armatura_del AS 
 SELECT k1.gid as mid, k1.id, k1.identyfikator, k1.creation_date, k1.modification_date, 
       k1.version, k1.status, k1.miejscowos, k1.ulica, k1.no_ulica, k1.dzialka, k1.adres, k1.obreb, k1.wlasciciel, 
       k1.rok, k1.zrodlo, k1.stan, k1.typ, k1.armatura, k1.g7_kan, k1.g7_kan_opis, k1.k1_kan, 
       k1.kerg, k1.rz_terenu, k1.rz_dna, k1.rz_wejscia, k1.rz_wyjscia, k1.posadowien, 
       k1.material, k1.srednic, k1.srednic_wl, k1.srednic_wy, k1.uwagi, k1.szkic, k1.mapa, 
       k1.projekt, k1.film, k1.zdjecie, k1.plik, k1.zdarzenie, k1.zdarzenie_opis, k1.opis, 
       k1.tekst, k1.dodal, k1.dodal_dnia, k1.zmienil, k1.zmienil_dnia, k1.nr_inwentarzowy, 
       k1.nr_sr_trwalego, k1.the_geom, k1.odbior
   FROM pwik.kan_armatura k1  LEFT JOIN gesut7.kan_armatura k2 USING (identyfikator)  WHERE k2.identyfikator IS NULL and k1.identyfikator is not null and k1.stan<>'u';

ALTER TABLE pwik.kan_armatura_del OWNER TO postgres;
GRANT ALL ON TABLE pwik.kan_armatura_del TO postgres;
GRANT SELECT ON TABLE pwik.kan_armatura_del TO users;
GRANT SELECT, UPDATE, INSERT ON TABLE pwik.kan_armatura_del TO operators;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE pwik.kan_armatura_del TO admins;

-- View: techniczny.zuzycia_v

DROP VIEW  IF EXISTS  techniczny.zuzycia_v;
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
   FROM techniczny.zuzycia z LEFT JOIN swde.adresy_v a ON z.miejscowosc = a.miejsciowosc AND z.ulica = a.ulica AND z.nr_domu = a.adres;

ALTER TABLE techniczny.zuzycia_v  OWNER TO postgres;
GRANT ALL ON TABLE techniczny.zuzycia_v TO postgres;
GRANT SELECT, UPDATE, INSERT ON TABLE techniczny.zuzycia_v TO operators;
GRANT SELECT, UPDATE, INSERT ON TABLE techniczny.zuzycia_v TO users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE techniczny.zuzycia_v TO admins;




-- Index: gesut7.kan_armatura_identyfikator_idx

-- DROP INDEX gesut7.kan_armatura_identyfikator_idx;

CREATE INDEX kan_armatura_identyfikator_idx
  ON gesut7.kan_armatura
  USING btree
  (identyfikator COLLATE pg_catalog."default");

-- Index: gesut7.kan_armatura_modification_date_idx

-- DROP INDEX gesut7.kan_armatura_modification_date_idx;

CREATE INDEX kan_armatura_modification_date_idx
  ON gesut7.kan_armatura
  USING btree
  (modification_date COLLATE pg_catalog."default");


-- Index: gesut7.kan_obiekty_identyfikator_idx

-- DROP INDEX gesut7.kan_obiekty_identyfikator_idx;

CREATE INDEX kan_obiekty_identyfikator_idx
  ON gesut7.kan_obiekty
  USING btree
  (identyfikator COLLATE pg_catalog."default");

-- Index: gesut7.kan_obiekty_modification_date_idx

-- DROP INDEX gesut7.kan_obiekty_modification_date_idx;

CREATE INDEX kan_obiekty_modification_date_idx
  ON gesut7.kan_obiekty
  USING btree
  (modification_date COLLATE pg_catalog."default");


-- Index: gesut7.kan_sieci_identyfikator_idx

-- DROP INDEX gesut7.kan_sieci_identyfikator_idx;

CREATE INDEX kan_sieci_identyfikator_idx
  ON gesut7.kan_sieci
  USING btree
  (identyfikator COLLATE pg_catalog."default");

-- Index: gesut7.kan_sieci_modification_date_idx

-- DROP INDEX gesut7.kan_sieci_modification_date_idx;

CREATE INDEX kan_sieci_modification_date_idx
  ON gesut7.kan_sieci
  USING btree
  (modification_date COLLATE pg_catalog."default");


-- Index: gesut7.wod_armatura_identyfikator_idx

-- DROP INDEX gesut7.wod_armatura_identyfikator_idx;

CREATE INDEX wod_armatura_identyfikator_idx
  ON gesut7.wod_armatura
  USING btree
  (identyfikator COLLATE pg_catalog."default");

-- Index: gesut7.wod_armatura_modification_date_idx

-- DROP INDEX gesut7.wod_armatura_modification_date_idx;

CREATE INDEX wod_armatura_modification_date_idx
  ON gesut7.wod_armatura
  USING btree
  (modification_date COLLATE pg_catalog."default");


-- Index: gesut7.wod_obiekty_identyfikator_idx

-- DROP INDEX gesut7.wod_obiekty_identyfikator_idx;

CREATE INDEX wod_obiekty_identyfikator_idx
  ON gesut7.wod_obiekty
  USING btree
  (identyfikator COLLATE pg_catalog."default");

-- Index: gesut7.wod_obiekty_modification_date_idx

-- DROP INDEX gesut7.wod_obiekty_modification_date_idx;

CREATE INDEX wod_obiekty_modification_date_idx
  ON gesut7.wod_obiekty
  USING btree
  (modification_date COLLATE pg_catalog."default");


-- Index: gesut7.wod_sieci_identyfikator_idx

-- DROP INDEX gesut7.wod_sieci_identyfikator_idx;

CREATE INDEX wod_sieci_identyfikator_idx
  ON gesut7.wod_sieci
  USING btree
  (identyfikator COLLATE pg_catalog."default");

-- Index: gesut7.wod_sieci_modification_date_idx

-- DROP INDEX gesut7.wod_sieci_modification_date_idx;

CREATE INDEX wod_sieci_modification_date_idx
  ON gesut7.wod_sieci
  USING btree
  (modification_date COLLATE pg_catalog."default");


-- end
