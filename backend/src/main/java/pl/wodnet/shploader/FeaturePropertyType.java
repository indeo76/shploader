package pl.wodnet.shploader;

import org.geotools.feature.type.PropertyTypeImpl;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyType;
import org.opengis.filter.Filter;
import org.opengis.util.InternationalString;

import java.util.List;

public class FeaturePropertyType extends PropertyTypeImpl {
    public FeaturePropertyType(Name name, Class<?> binding, boolean isAbstract, List<Filter> restrictions, PropertyType superType, InternationalString description) {
        super(name, binding, isAbstract, restrictions, superType, description);
    }
}
