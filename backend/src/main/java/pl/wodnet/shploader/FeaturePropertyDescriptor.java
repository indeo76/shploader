package pl.wodnet.shploader;

import org.geotools.feature.type.PropertyDescriptorImpl;
import org.opengis.feature.type.Name;
import org.opengis.feature.type.PropertyType;

public class FeaturePropertyDescriptor extends PropertyDescriptorImpl {
    public FeaturePropertyDescriptor(PropertyType type, Name name, int min, int max, boolean isNillable) {
        super(type, name, min, max, isNillable);
    }
}
