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
@Table(name = "tel_obiekty", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_tel_obiekty_idx"),
        @Index(columnList = "g7", name = "g7_tel_obiekty_idx")
})
@NoArgsConstructor
public class TelObiektyEntity extends ObiektyBase{
    private String g7_tel_opi;
    public TelObiektyEntity(ShpEntity shp) {
        super(shp);
        g7_tel_opi = shp.getXCODE_N();
    }
}
