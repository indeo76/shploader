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
@Table(name = "obreby", schema = "swde")
@NoArgsConstructor
public class ObrebEntity extends SWDEBaseEntity{
    private String g5naz;
    public ObrebEntity(ShpEntity shpEntity) {
        super(shpEntity);
//        this.setTableName(Constants.OBREBY);
        this.g5naz = shpEntity.getG5NAZ();
    }
}
