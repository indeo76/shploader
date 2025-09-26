package pl.wodnet.shploader.entity.pwik;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.pwik.base.PwikSieciEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "wod_sieci", schema = "pwik")
@NoArgsConstructor
public class PwikWodSieciEntity extends PwikSieciEntity {
    @Column(columnDefinition = "TEXT")
    private String g7_wod;

    @Column(columnDefinition = "TEXT")
    private String g7_wod_opis;

    @Column(columnDefinition = "TEXT")
    private String k1_wod;
}
