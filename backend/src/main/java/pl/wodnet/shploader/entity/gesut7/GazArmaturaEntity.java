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
@Table(name = "gaz_armatura", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_gaz_armatura_idx"),
        @Index(columnList = "g7", name = "g7_gaz_armatura_idx"),
        @Index(columnList = "status", name = "status_gaz_armatura_idx")
})
@NoArgsConstructor
public class GazArmaturaEntity extends ArmaturaBase {
    private String g7_gaz_opi;
    public GazArmaturaEntity(ShpEntity shp) {
        super(shp);
        g7_gaz_opi = getG7_opis();
    }
}
