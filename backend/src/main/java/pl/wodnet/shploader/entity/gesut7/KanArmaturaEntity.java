package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.entity.gesut7.ArmaturaBase;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "kan_armatura", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_kan_armatura_idx"),
        @Index(columnList = "g7", name = "g7_kan_armatura_idx"),
        @Index(columnList = "status", name = "status_kan_armatura_idx")
})
@NoArgsConstructor
public class KanArmaturaEntity extends ArmaturaBase {
    private String g7_kan_opi;
    public KanArmaturaEntity(ShpEntity shp) {
        super(shp);
        g7_kan_opi = shp.getXCODE_D();
    }
}
