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
public abstract class ArmaturaBase extends GesutBaseEntity {
    private Double rz_dna;
    private Double rz_terenu;

    public ArmaturaBase(ShpEntity shp) {
        super(shp);
        rz_dna = shp.getRZD();
        rz_terenu = shp.getRZG();
    }
}
