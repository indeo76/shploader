create or replace view mobile.co_armatura as
SELECT co_armatura.kerg,
       co_armatura.rz_dna,
       co_armatura.rz_terenu,
       co_armatura.g7,
       co_armatura.g7_eco_opi,
       co_armatura.the_geom,
       co_armatura.gid
FROM gesut7.co_armatura;
