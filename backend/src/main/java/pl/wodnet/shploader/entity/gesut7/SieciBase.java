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
public abstract class SieciBase extends GesutBaseEntity{
    private String funkcja;
    private String funkcja_n;
    private String material;
    //private String dzialka;
    private Double srednic_ww;
    private Double srednic_zw;
    private Double dlugosc;
    private String izolacja;
    private String przebieg;
    //private String stan;

    public SieciBase(ShpEntity shp) {
        super(shp);
        funkcja = shp.getFNP_N();
        funkcja_n = shp.getFNP_D();
        material = shp.getMAT_N();
        srednic_ww = shp.getSSW();
        srednic_zw = shp.getSSZ();
        dlugosc = shp.getXLENGTH();
        izolacja = shp.getOBD_N();
        przebieg = shp.getPRZ_N();
    }
}
