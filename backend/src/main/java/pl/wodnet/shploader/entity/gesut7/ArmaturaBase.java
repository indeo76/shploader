package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.tools.Tools;

import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class ArmaturaBase extends GesutBaseEntity {
    private BigDecimal rz_dna;
    private BigDecimal rz_terenu;

    public ArmaturaBase(ShpEntity shp) {
        super(shp);
        rz_dna = Tools.toBigDecimal(shp.getRZD(), Constants.RZEDNA_SCALE);
        rz_terenu = Tools.toBigDecimal(shp.getRZG(), Constants.RZEDNA_SCALE);
    }
}
