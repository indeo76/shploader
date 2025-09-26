package pl.wodnet.shploader.entity;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.util.Map;

@Entity
@Getter
@Setter
@Table(name = "shp")
public class ShpEntity extends ShpBaseEntity{

    private String typ;
    private String tableName;
    @Column(name = "the_geom", columnDefinition = "GEOMETRY")
    private Geometry geom;

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
