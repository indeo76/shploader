CREATE OR REPLACE VIEW mobile.gaz_sieci
AS SELECT gaz_sieci.kerg,
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
