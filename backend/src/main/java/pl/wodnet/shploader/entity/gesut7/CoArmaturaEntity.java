package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "co_armatura", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_co_armatura_idx"),
        @Index(columnList = "g7", name = "g7_co_armatura_idx"),
        @Index(columnList = "status", name = "status_co_armatura_idx")
})
@NoArgsConstructor
public class CoArmaturaEntity extends ArmaturaBase {
    private String g7_eco_opi;
    public CoArmaturaEntity(ShpEntity shp) {
        super(shp);
        g7_eco_opi = getG7_opis();
    }
}
