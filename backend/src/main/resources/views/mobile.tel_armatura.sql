CREATE OR REPLACE VIEW mobile.tel_armatura
AS SELECT tel_armatura.kerg,
    tel_armatura.rz_terenu,
    tel_armatura.g7,
    tel_armatura.g7_tel_opi,
    tel_armatura.the_geom,
    tel_armatura.gid
   FROM gesut7.tel_armatura;
