CREATE OR REPLACE VIEW mobile.spc_obiekty
AS SELECT spc_obiekty.rz_terenu,
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
