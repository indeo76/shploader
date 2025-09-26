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
@Table(name = "gaz_sieci", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_gaz_sieci_idx"),
        @Index(columnList = "g7", name = "g7_gaz_sieci_idx"),
        @Index(columnList = "przebieg", name = "przebieg_gaz_sieci_idx"),
        @Index(columnList = "funkcja", name = "funkcja_gaz_sieci_idx"),
        @Index(columnList = "status", name = "status_gaz_sieci_idx")
})
@NoArgsConstructor
public class GazSieciEntity extends SieciBase{
    private String g7_gaz_opi;
    public GazSieciEntity(ShpEntity shp) {
        super(shp);
        g7_gaz_opi = shp.getXCODE_D();
    }
}
