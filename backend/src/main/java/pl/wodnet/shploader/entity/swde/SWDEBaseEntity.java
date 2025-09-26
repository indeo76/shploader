package pl.wodnet.shploader.entity.swde;

import org.locationtech.jts.geom.Geometry;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class SWDEBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;

    @Column(name = "the_geom", columnDefinition = "GEOMETRY")
    private Geometry geom;
//    private String tableName;

    public SWDEBaseEntity(ShpEntity shpEntity) {
        this.geom = shpEntity.getGeom();
    }
}
