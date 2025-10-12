package pl.wodnet.shploader.entity.sytuacja;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;
import pl.wodnet.shploader.entity.ShpBaseEntity;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public class SytuacjaBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;

    @Column(name = "the_geom", columnDefinition = "GEOMETRY")
    private Geometry geom;

    public SytuacjaBaseEntity(ShpBaseEntity shpEntity) {
        this.geom = shpEntity.getGeom();
    }
}
