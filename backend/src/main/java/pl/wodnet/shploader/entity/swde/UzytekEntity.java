package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpBaseEntity;
import pl.wodnet.shploader.entity.ShpSwdeEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = Constants.UZYTKI, schema = "swde")
@NoArgsConstructor
public class UzytekEntity extends SWDEBaseEntity {

    private String kod;

    public UzytekEntity(ShpSwdeEntity shpEntity) {
        super(shpEntity);
        kod = shpEntity.getOFU();
    }
}
