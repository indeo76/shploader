CREATE OR REPLACE VIEW mobile.gaz_obiekty
AS SELECT gaz_obiekty.rz_dna,
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
