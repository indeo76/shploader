package pl.wodnet.shploader.entity;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;
import pl.wodnet.shploader.service.classification.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "shp_gesut", schema = "shp")
public class ShpEntity extends ShpBaseEntity{

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
