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
@Table(name = "kan_sieci", schema = "pwik")
@NoArgsConstructor
public class PwikKanSieciEntity extends PwikSieciEntity {
    @Column(columnDefinition = "TEXT")
    private String g7_kan;
    @Column(columnDefinition = "TEXT")
    private String g7_kan_opis;
    @Column(columnDefinition = "TEXT")
    private String k1_kan;
}
