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
@Table(name = "co_obiekty", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_co_obiekty_idx"),
        @Index(columnList = "g7", name = "g7_co_obiekty_idx")
})
@NoArgsConstructor
public class CoObiektyEntity extends ObiektyBase{
    private String g7_eco_opi;
//    private Double srednic_ww;
//    private Double srednic_zw;
    public CoObiektyEntity(ShpEntity shp) {
        super(shp);
        g7_eco_opi = getG7_opis();
//        srednic_ww = shp.getSSW();
//        srednic_zw = shp.getSSZ();
    }
}
