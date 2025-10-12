package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpSwdeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = Constants.ADRESY, schema = "swde")
@NoArgsConstructor
public class AdresEntity extends SWDEBaseEntity {

    private String miejscowosc;
    private String ulica;
    private String numer;
    private String adres;

    public AdresEntity(ShpSwdeEntity shpEntity) {
        super(shpEntity);
        miejscowosc = shpEntity.getNAZWAMIEJS();
        ulica = shpEntity.getNAZWAULICY();
        numer = shpEntity.getNUMERPORZA();
        adres = ulica + " " + numer;
    }
}
