package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.entity.gesut7.ArmaturaBase;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "wod_armatura", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_wod_armatura_idx"),
        @Index(columnList = "g7", name = "g7_wod_armatura_idx"),
        @Index(columnList = "status", name = "status_wod_armatura_idx")
})
@NoArgsConstructor
public class WodArmaturaEntity extends ArmaturaBase {

    private String g7_wod_opi;

    public WodArmaturaEntity(ShpEntity shp) {
        super(shp);
        g7_wod_opi = shp.getXCODE_D();
    }

}
