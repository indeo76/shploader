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
@Table(name = "tel_armatura", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_tel_armatura_idx"),
        @Index(columnList = "g7", name = "g7_tel_armatura_idx"),
        @Index(columnList = "status", name = "status_tel_armatura_idx")
})
@NoArgsConstructor
public class TelArmaturaEntity extends ArmaturaBase {
    private String g7_tel_opi;
    public TelArmaturaEntity(ShpEntity shp) {
        super(shp);
        g7_tel_opi = getG7_opis();
    }
}
