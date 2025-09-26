package pl.wodnet.shploader;

import org.geotools.feature.PropertyImpl;
import org.opengis.feature.Property;
import org.opengis.feature.type.PropertyDescriptor;

public class FeatureProperty extends PropertyImpl {
    public FeatureProperty(Object value, PropertyDescriptor descriptor) {
        super(value, descriptor);
    }
}
