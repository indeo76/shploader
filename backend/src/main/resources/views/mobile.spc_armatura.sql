CREATE OR REPLACE VIEW mobile.spc_armatura
AS SELECT spc_armatura.kerg,
    spc_armatura.rz_dna,
    spc_armatura.rz_terenu,
    spc_armatura.g7,
    spc_armatura.g7_tel_opi,
    spc_armatura.the_geom,
    spc_armatura.gid
   FROM gesut7.spc_armatura;
