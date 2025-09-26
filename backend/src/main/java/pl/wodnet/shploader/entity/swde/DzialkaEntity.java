package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = Constants.DZIALKI, schema = "swde")
@NoArgsConstructor
public class DzialkaEntity extends SWDEBaseEntity {
    private String obreb;
    private String numer;

    public DzialkaEntity(ShpEntity shpEntity) {
        super(shpEntity);
        this.obreb = shpEntity.getOBREB();
        this.numer = shpEntity.getNUMER();
    }
}
