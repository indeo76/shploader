package pl.wodnet.shploader.controller;


import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.geotools.geometry.jts.coordinatesequence.CoordinateSequences;
import org.geotools.gml3.GMLConfiguration;
import org.geotools.wfs.GML;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.Feature;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.GeometryDescriptor;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.Name;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.geotools.xsd.StreamingParser;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import org.geotools.xsd.Parser;
import java.net.URL;
import java.util.logging.Logger;

@RestController
public class GmlController {
    private final static Logger LOGGER = Logger.getLogger(GmlController.class.getName());


    @GetMapping("/gml/test")
    public String test() throws IOException, ParserConfigurationException, SAXException {
        String path = "https://www.gov.pl/attachment/51f5d2fd-4966-4a06-a33e-05466a46b66c";
//        File file = new File(path);
        URL url = new URL(path);
        InputStream in = url.openStream();

        GML gml = new GML(org.geotools.wfs.GML.Version.GML3);
//        SimpleFeatureCollection featureCollection = gml.decodeFeatureCollection(in);
        SimpleFeatureCollection features = gml.decodeFeatureCollection(url.openStream());
        System.out.println("Koniec");
        return "Zakonczono";
    }

    @GetMapping("/gml/test1")
    public String test1() throws IOException, ParserConfigurationException, SAXException {
        File initialFile = new File("C:\\Modul-mapy\\shploader\\gml\\lakes.gml");
        InputStream in = new FileInputStream(initialFile);
        GMLConfiguration gml = new GMLConfiguration();
        Parser parser = new Parser(gml);
        parser.setStrict(false);
        SimpleFeatureCollection featureCollection = (SimpleFeatureCollection) parser.parse(in);
        SimpleFeatureIterator iterator = featureCollection.features();
        while (iterator.hasNext()){
            final SimpleFeature feature = iterator.next();
            org.locationtech.jts.geom.Geometry complexGeometry = (org.locationtech.jts.geom.Geometry) feature.getDefaultGeometry();
            if(complexGeometry != null) {
                Integer geometriesCount = complexGeometry.getNumGeometries();
                LOGGER.info("count: " + String.valueOf(geometriesCount));
                for(Integer iGeom = 0; iGeom< geometriesCount; iGeom++){
                    org.locationtech.jts.geom.Geometry g = complexGeometry.getGeometryN(iGeom);
                    LOGGER.info(g.toString());
                }
            }
        }
        System.out.println("Koniec");
        return "Zakonczono";
    }

    @GetMapping("/gml/test2")
    public String test2() throws IOException, ParserConfigurationException, SAXException {
        File initialFile = new File("C:\\Modul-mapy\\shploader\\gml\\lakes.gml");
        InputStream in = new FileInputStream(initialFile);
        GMLConfiguration gml = new GMLConfiguration();
        Parser parser = new Parser(gml);
        parser.setStrict(false);
        FeatureCollection featureCollection = (FeatureCollection) parser.parse(in);
        FeatureIterator iterator = featureCollection.features();
        while (iterator.hasNext()){
            final Feature feature = iterator.next();
            GeometryAttribute complexGeometry = feature.getDefaultGeometryProperty();
            if(complexGeometry != null) {
//                Integer geometriesCount = complexGeometry.getNumGeometries();
//                LOGGER.info("count: " + String.valueOf(geometriesCount));
//                for(Integer iGeom = 0; iGeom< geometriesCount; iGeom++){
////                    org.locationtech.jts.geom.Geometry g = complexGeometry.getGeometryN(iGeom);
//                    LOGGER.info(g.toString());
//                }
            }
        }
        System.out.println("Koniec");
        return "Zakonczono";
    }
}
