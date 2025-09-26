CREATE OR REPLACE VIEW mobile.tel_obiekty
AS SELECT tel_obiekty.rz_terenu,
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
