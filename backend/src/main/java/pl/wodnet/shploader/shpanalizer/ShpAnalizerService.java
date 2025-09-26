package pl.wodnet.shploader.shpanalizer;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.PropertyType;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;
import pl.wodnet.shploader.dto.ImportResultDTO;
import pl.wodnet.shploader.service.ConfService;
import pl.wodnet.shploader.service.ShpService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShpAnalizerService {
    private final ShpService shpService;
    private final ConfService confService;

    protected final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    public ShpAnalizerService(ShpService shpService, ConfService confService) {
        this.shpService = shpService;
        this.confService = confService;
    }

    public List<ShpReportRow> analizuj(String filePath) throws IOException {
        List<ShpReportRow> rows = new ArrayList<>();
        List<GeoinfoKodyDTO> kody = confService.importGeoinfoKody();
        List<String> fileList = shpService.shpListSorted(filePath);
        int i = 0;
        for(String fileName : fileList){
            i++;
            analizujPlik(filePath, fileName, rows, kody);
            if(i > 9999){
                break;
            }
        }
        return rows;
    }

    public void eksportujWgStarychKodow(String sourceFilePath, String targetFilePath){

    }

    private List<ShpReportRow> analizujPlik(String filePath, String fileName, List<ShpReportRow> rows, List<GeoinfoKodyDTO> kody) throws IOException {
        LOGGER.info(String.format("Analizuje plik %s", fileName));
        return getShpDetailedInfo(Paths.get(filePath, fileName).toFile(), rows, kody);
    }

    private List<ShpReportRow> getShpDetailedInfo(File file, List<ShpReportRow> rows, List<GeoinfoKodyDTO> kody) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());
        DataStore dataStore = DataStoreFinder.getDataStore(map);
        SimpleFeatureIterator iterator = null;
        Integer featureCount = 0;
        if (dataStore != null) {
            SimpleFeatureSource featureSource = dataStore.getFeatureSource(dataStore.getTypeNames()[0]);
            featureCount = featureSource.getFeatures().size();
            LOGGER.info(String.format("Liczba obiektów w pliku SHP: %s", featureCount));
            //todo tutaj iterować po obiektach
            SimpleFeatureCollection collection = featureSource.getFeatures();
            iterator = collection.features();
            while (iterator.hasNext()) {
                final SimpleFeature feature = iterator.next();
                rows = analizujFeature(feature, rows, kody, file.getName());
            }

            iterator.close();
            dataStore.dispose();
        } else {
            System.out.println("Nie udało się wczytać pliku SHP.");
        }
        return rows;
    }

    private List<ShpReportRow> analizujFeature(SimpleFeature feature, List<ShpReportRow> rows, List<GeoinfoKodyDTO> kody, String fileName) {
        String kodNowy = (String) feature.getProperty("XCODE_N").getValue();
        String kodMnemoniczny = (String) feature.getProperty("DKP_N").getValue();

        Map<String, String> properties = new HashMap<>();
        Map<String, String> propertyTypes = new HashMap<>();
        List<Property> propertyList = new ArrayList<>(feature.getProperties());
        List<ShpReportRow> rowsFilteredByFilename = rows.stream().filter( item -> item.getFileName().equals(fileName)).collect(Collectors.toList());
        List<ShpReportRow> rowsFilteredByKodNowy = rowsFilteredByFilename.stream().filter( item -> item.getKodNowy().equals(kodNowy)).collect(Collectors.toList());
        List<ShpReportRow> rowsFilteredByKodMnemoniczny = rowsFilteredByKodNowy.stream().filter( item -> item.getKodStary().equals(kodMnemoniczny)).collect(Collectors.toList());
        if(rowsFilteredByKodMnemoniczny.size() > 0){
            rowsFilteredByKodMnemoniczny.get(0).setCount(rowsFilteredByKodMnemoniczny.get(0).getCount() + 1);
        } else {
            GeometryAttribute sourceGeometry = feature.getDefaultGeometryProperty();
            GeometryType geometryType = sourceGeometry.getType();

            ShpReportRow row = new ShpReportRow();
            row.setFileName(fileName);
            row.setKodNowy(kodNowy);
            row.setDKP_D((String) feature.getProperty("DKP_D").getValue());
            row.setGNAME((String) feature.getProperty("GNAME").getValue());
            row.setGVALUE((String) feature.getProperty("GVALUE").getValue());

            row.setKodStary(kodMnemoniczny);
            row.setXCODE_D((String) feature.getProperty("XCODE_D").getValue());
            row.setRodzajGeometrii(geometryType.getName().toString());

            row.setProperties(getFeatureProperties(feature));

            List<GeoinfoKodyDTO> kodyFiltered = kody.stream().filter(kod -> kod.kod_mnemoniczny.equals(kodMnemoniczny)).collect(Collectors.toList());
            if(kodyFiltered.size() > 0){
                row.setKodGeoinfo(kodyFiltered.get(0));
            }

            row.setCount(1);
            rows.add(row);
        }
        return rows;
    }

    private List<String> getFeatureProperties(SimpleFeature feature) {
        Collection<Property> props = feature.getProperties();
        List<String> properties = new ArrayList<>();
        for (Property prop : props) {
            properties.add(prop.getName().toString());
        }
        properties.sort(String.CASE_INSENSITIVE_ORDER);
        return properties;
    }

    public List<String> extractGlobalProperties(List<ShpReportRow> rows) {
        List<String> globalProperties = new ArrayList<>();
        for(ShpReportRow row : rows){
            for(String property : row.getProperties()){
                if(!globalProperties.contains(property)){
                    globalProperties.add(property);
                }
            }
        }
        return globalProperties;
    }
}
