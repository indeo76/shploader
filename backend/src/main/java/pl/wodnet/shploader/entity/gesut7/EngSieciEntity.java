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
@Table(name = "eng_sieci", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_eng_sieci_idx"),
        @Index(columnList = "g7", name = "g7_eng_sieci_idx"),
        @Index(columnList = "przebieg", name = "przebieg_eng_sieci_idx"),
        @Index(columnList = "funkcja", name = "funkcja_eng_sieci_idx"),
        @Index(columnList = "status", name = "status_eng_sieci_idx")
})
@NoArgsConstructor
public class EngSieciEntity extends SieciBase {
    private String g7_pro_opi;
    public EngSieciEntity(ShpEntity shp) {
        super(shp);
        g7_pro_opi = shp.getXCODE_D();
    }
}
