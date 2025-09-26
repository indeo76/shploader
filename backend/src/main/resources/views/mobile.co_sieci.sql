create or replace view mobile.co_sieci as
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
