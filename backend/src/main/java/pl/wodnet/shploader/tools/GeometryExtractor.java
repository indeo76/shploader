package pl.wodnet.shploader.tools;

import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;

public class GeometryExtractor {

    public static Geometry extractGeometry(Feature feature) {
        GeometryAttribute geometryAttribute = feature.getDefaultGeometryProperty();
        if (geometryAttribute != null) {
            return (Geometry) geometryAttribute.getValue();
        } else {
            return null;
        }
    }
}
