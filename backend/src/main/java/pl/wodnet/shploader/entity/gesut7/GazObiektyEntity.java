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
@Table(name = "gaz_obiekty", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_gaz_obiekty_idx"),
        @Index(columnList = "g7", name = "g7_gaz_obiekty_idx")
})
@NoArgsConstructor
public class GazObiektyEntity extends ObiektyBase {
    private String g7_gaz_opi;
    public GazObiektyEntity(ShpEntity shp) {
        super(shp);
        g7_gaz_opi = getG7_opis();
    }
}
