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

    private String geometria;

    public SytuacjaBaseEntity(ShpBaseEntity shpEntity) {
        this.geom = shpEntity.getGeom();
        this.geometria = resolveGeometria(shpEntity.getFeatureGeomType());
    }

    private String resolveGeometria(String featureGeomType) {
        if(featureGeomType.toLowerCase().contains("point")) {
            return "punkt";
        } else if(featureGeomType.toLowerCase().contains("line")) {
            return "linie";
        } else if(featureGeomType.toLowerCase().contains("polygon")) {
            return "poligon";
        }
        return null;
    }
}
