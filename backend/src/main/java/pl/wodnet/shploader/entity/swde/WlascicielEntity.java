package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = Constants.WLASCICIELE, schema = "swde")
@NoArgsConstructor
public class WlascicielEntity extends SWDEBaseEntity {
    private String numer; //nr dzialki
    private String obreb; //Września
    private String identyfikator; //numer dzialki z katastrem
    private String typ; //właściciel
    private String nazwa; //nazwa
    private String adres; //adres
    private String dokumkenty; //KW 6375

    public WlascicielEntity(ShpEntity shpEntity) {
        super(shpEntity);
        //przypisz to:
//        private String numer;
        obreb = shpEntity.getOBREB();
//        private String identyfikator;
//        private String typ;
//        private String nazwa;
//        private String adres;
//        dokumkenty = shpEntity.get;
    }
}
