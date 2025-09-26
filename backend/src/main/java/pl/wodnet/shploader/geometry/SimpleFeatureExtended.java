package pl.wodnet.shploader.geometry;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.IllegalAttributeException;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.feature.type.AttributeDescriptor;
import org.opengis.feature.type.Name;
import org.opengis.filter.identity.FeatureId;
import org.opengis.geometry.BoundingBox;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SimpleFeatureExtended implements SimpleFeature {
    private Geometry geom = null;

    @Override
    public String getID() {
        return null;
    }

    @Override
    public AttributeDescriptor getDescriptor() {
        return null;
    }

    @Override
    public Name getName() {
        return null;
    }

    @Override
    public boolean isNillable() {
        return false;
    }

    @Override
    public Map<Object, Object> getUserData() {
        return null;
    }

    @Override
    public SimpleFeatureType getType() {
        return null;
    }

    @Override
    public void setValue(Collection<Property> collection) {

    }

    @Override
    public Collection<? extends Property> getValue() {
        return null;
    }

    @Override
    public void setValue(Object o) {

    }

    @Override
    public Collection<Property> getProperties(Name name) {
        return null;
    }

    @Override
    public Property getProperty(Name name) {
        return null;
    }

    @Override
    public Collection<Property> getProperties(String s) {
        return null;
    }

    @Override
    public Collection<Property> getProperties() {
        return null;
    }

    @Override
    public Property getProperty(String s) {
        return null;
    }

    @Override
    public void validate() throws IllegalAttributeException {

    }

    @Override
    public FeatureId getIdentifier() {
        return null;
    }

    @Override
    public BoundingBox getBounds() {
        return null;
    }

    @Override
    public GeometryAttribute getDefaultGeometryProperty() {
        return null;
    }

    @Override
    public void setDefaultGeometryProperty(GeometryAttribute geometryAttribute) {

    }

    @Override
    public SimpleFeatureType getFeatureType() {
        return null;
    }

    @Override
    public List<Object> getAttributes() {
        return null;
    }

    @Override
    public void setAttributes(List<Object> list) {

    }

    @Override
    public void setAttributes(Object[] objects) {

    }

    @Override
    public Object getAttribute(String s) {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {

    }

    @Override
    public Object getAttribute(Name name) {
        return null;
    }

    @Override
    public void setAttribute(Name name, Object o) {

    }

    @Override
    public Object getAttribute(int i) throws IndexOutOfBoundsException {
        return null;
    }

    @Override
    public void setAttribute(int i, Object o) throws IndexOutOfBoundsException {

    }

    @Override
    public int getAttributeCount() {
        return 0;
    }

    @Override
    public Object getDefaultGeometry() {
        return null;
    }

    @Override
    public void setDefaultGeometry(Object o) {

    }
}
