CREATE OR REPLACE VIEW mobile.eng_armatura
AS SELECT eng_armatura.kerg,
          eng_armatura.rz_terenu,
          eng_armatura.g7,
          eng_armatura.g7_pro_opi,
          eng_armatura.the_geom,
          eng_armatura.gid
   FROM gesut7.eng_armatura;
