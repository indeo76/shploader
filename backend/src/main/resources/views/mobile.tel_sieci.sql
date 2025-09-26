CREATE OR REPLACE VIEW mobile.tel_sieci
AS SELECT tel_sieci.kerg,
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
