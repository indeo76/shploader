package pl.wodnet.shploader.entity;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;
import pl.wodnet.shploader.service.classification.SHPBranza;
import pl.wodnet.shploader.service.classification.SHPGeomType;
import pl.wodnet.shploader.service.classification.SHPObjectType;
import pl.wodnet.shploader.service.classification.SHPRoot;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "shp")
public class ShpEntity extends ShpBaseEntity{
    @Column(name = "shp_root")
    @Enumerated(EnumType.STRING)
    private SHPRoot root;

    @Column(name = "shp_geom_type")
    @Enumerated(EnumType.STRING)
    private SHPGeomType geomType;

    @Column(name = "shp_branza")
    @Enumerated(EnumType.STRING)
    private SHPBranza branza;

    @Column(name = "shp_object_type")
    @Enumerated(EnumType.STRING)
    private SHPObjectType obiektType;

    private String typ;
    private String tableName;
    @Column(name = "the_geom", columnDefinition = "GEOMETRY")
    private Geometry geom;

    @Column(name = "kod")
    private String kod;

//    @ElementCollection
//    @CollectionTable(name = "property_map", joinColumns = @JoinColumn(name = "id_shp"))
//    //@MapKeyJoinColumn(name = "TASK_ID")
//    @Column(name = "property")
//    private Map<String, String> properties;

//    @ElementCollection
//    @CollectionTable(name = "property_type_map", joinColumns = @JoinColumn(name = "id_shp"))
//    //@MapKeyJoinColumn(name = "TASK_ID")
//    @Column(name = "property_type")
//    private Map<String, String> propertyTypes;

    public ShpEntity() {
        super();
    }
}
