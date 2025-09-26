create or replace view mobile.co_obiekty as
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
