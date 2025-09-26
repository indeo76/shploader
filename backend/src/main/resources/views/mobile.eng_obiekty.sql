CREATE OR REPLACE VIEW mobile.eng_obiekty
AS SELECT eng_obiekty.rz_terenu,
          eng_obiekty.rz_dna,
          eng_obiekty.kerg,
          eng_obiekty.material,
          eng_obiekty.g7,
          eng_obiekty.g7_pro_opi,
          eng_obiekty.srednic_ww,
          eng_obiekty.srednic_zw,
          eng_obiekty.stan,
          eng_obiekty.the_geom,
          eng_obiekty.gid
   FROM gesut7.eng_obiekty;
