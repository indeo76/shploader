package pl.wodnet.shploader.gml;

import org.geotools.gml3.GMLConfiguration;
import org.geotools.xsd.Parser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.annotation.PostConstruct;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GmlReader {

//    @PostConstruct
    private void read() throws IOException, ParserConfigurationException, SAXException {
        File gmlFile = new File("C:\\Modul-mapy\\shploader\\1_1_GML_APP_plan_wojewodztwa.gml");

        // Parsowanie GML-a ręcznie przez GeoTools GML parser
        Parser parser = new Parser(new GMLConfiguration());
        Object parsed = parser.parse(new FileInputStream(gmlFile));

        if (parsed instanceof List<?>) {
            List<?> features = (List<?>) parsed;
            for (Object obj : features) {
//                if (obj instanceof SimpleFeature feature) {
//                    SimpleFeatureType type = feature.getFeatureType();
//                    System.out.println("Typ warstwy: " + type.getTypeName());
//                    System.out.println("  Geometria: " + feature.getDefaultGeometry().getClass().getSimpleName());
//                    break; // tylko pierwszy element na próbę
//                }
            }
        } else {
            System.out.println("Nie rozpoznano obiektów GML.");
        }

    }

}
