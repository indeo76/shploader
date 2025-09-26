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
@Table(name = "spc_armatura", schema = "gesut7", indexes = {
        @Index(columnList = "identyfikator", name = "identyfikator_spc_armatura_idx"),
        @Index(columnList = "g7", name = "g7_spc_armatura_idx"),
        @Index(columnList = "status", name = "status_spc_armatura_idx")
})
@NoArgsConstructor
public class SpcArmaturaEntity extends ArmaturaBase {
    private String g7_tel_opi;
    public SpcArmaturaEntity(ShpEntity shp) {
        super(shp);
        g7_tel_opi = shp.getXCODE_N();// todo ??
    }
}
