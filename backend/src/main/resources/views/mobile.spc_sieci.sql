CREATE OR REPLACE VIEW mobile.spc_sieci
AS SELECT spc_sieci.kerg,
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
