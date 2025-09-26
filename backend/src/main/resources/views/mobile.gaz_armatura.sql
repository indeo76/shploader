
CREATE OR REPLACE VIEW mobile.gaz_armatura
AS SELECT gaz_armatura.kerg,
    gaz_armatura.rz_dna,
    gaz_armatura.rz_terenu,
    gaz_armatura.g7,
    gaz_armatura.g7_gaz_opi,
    gaz_armatura.the_geom,
    gaz_armatura.gid
   FROM gesut7.gaz_armatura;
