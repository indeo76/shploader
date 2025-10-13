ALTER TABLE gesut7.wod_armatura
ALTER COLUMN rz_dna TYPE numeric(10,2) USING ROUND(rz_dna::numeric, 2),
ALTER COLUMN rz_terenu TYPE numeric(10,2) USING ROUND(rz_terenu::numeric, 2);


ALTER TABLE gesut7.kan_armatura
ALTER COLUMN rz_dna TYPE numeric(10,2) USING ROUND(rz_dna::numeric, 2),
ALTER COLUMN rz_terenu TYPE numeric(10,2) USING ROUND(rz_terenu::numeric, 2);
