package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class ObiektyBase extends GesutBaseEntity{
    private Double rz_dna;
    private Double rz_terenu;
    private Double srednic_ww;
    private Double srednic_zw;
    private String funkcja;
    private String funkcja_n;
    public ObiektyBase(ShpEntity shp) {
        super(shp);
        rz_dna = shp.getRZD();
        rz_terenu = shp.getRZG();
        srednic_ww = shp.getSSW();
        srednic_zw = shp.getSSZ();
        funkcja = shp.getFNP_N();
        funkcja_n = shp.getFNP_D();
    }
}
