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

    public WlascicielEntity(ShpEntity shpEntity) {
        super(shpEntity);
    }
}
