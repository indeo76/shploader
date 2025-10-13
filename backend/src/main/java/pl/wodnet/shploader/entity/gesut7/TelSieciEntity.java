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
@Table(name = "tel_sieci", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_tel_sieci_idx"),
        @Index(columnList = "g7", name = "g7_tel_sieci_idx"),
        @Index(columnList = "przebieg", name = "przebieg_tel_sieci_idx"),
        @Index(columnList = "funkcja", name = "funkcja_tel_sieci_idx"),
        @Index(columnList = "status", name = "status_tel_sieci_idx")
})
@NoArgsConstructor
public class TelSieciEntity extends SieciBase{
    private String g7_tel_opi;
    public TelSieciEntity(ShpEntity shp) {
        super(shp);
        g7_tel_opi = getG7_opis();
    }
}
