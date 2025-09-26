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
@Table(name = "eng_obiekty", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_eng_obiekty_idx"),
        @Index(columnList = "g7", name = "g7_eng_obiekty_idx")
})
@NoArgsConstructor
public class EngObiektyEntity extends ObiektyBase {
    private String g7_pro_opi;
    public EngObiektyEntity(ShpEntity shp) {
        super(shp);
        g7_pro_opi = shp.getXCODE_D();
    }
}
