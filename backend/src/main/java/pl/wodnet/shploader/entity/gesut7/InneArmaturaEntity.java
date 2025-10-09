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
@Table(name = "inne_armatura", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_inne_armatura_idx"),
        @Index(columnList = "g7", name = "g7_inne_armatura_idx"),
        @Index(columnList = "status", name = "status_inne_armatura_idx")
})
@NoArgsConstructor
public class InneArmaturaEntity extends ArmaturaBase {
    public InneArmaturaEntity(ShpEntity shp) {
        super(shp);
    }
}
