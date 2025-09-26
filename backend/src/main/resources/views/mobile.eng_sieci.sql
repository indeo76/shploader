CREATE OR REPLACE VIEW mobile.eng_sieci
AS SELECT eng_sieci.kerg,
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
