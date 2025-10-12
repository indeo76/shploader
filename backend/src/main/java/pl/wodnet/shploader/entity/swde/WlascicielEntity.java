package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.entity.ShpSwdeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = Constants.WLASCICIELE, schema = "swde")
@NoArgsConstructor
public class WlascicielEntity extends SWDEBaseEntity {
    private String numer; //nr dzialki
    private String obreb; //Września
    private String typ; //właściciel
    private String nazwa; //nazwa
    private String adres; //adres
    private String dokumkenty; //KW 6375
    private String udzial;
    private String gmina;

    public WlascicielEntity(ShpSwdeEntity shpEntity) {
        super(shpEntity);
        numer = shpEntity.getNR_DZ();
        obreb = shpEntity.getOBREB();
        typ = shpEntity.getTYP();
        nazwa = shpEntity.getNAZWA();
        adres = shpEntity.getADRES();
        dokumkenty = shpEntity.getKW();
        udzial = shpEntity.getUDZIAL();
        gmina = shpEntity.getGMINA();
    }
}
