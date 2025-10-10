package pl.wodnet.shploader.controller;

import org.apache.commons.lang3.NotImplementedException;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.JTS;
import org.geotools.referencing.CRS;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.locationtech.jts.io.ParseException;
import org.locationtech.jts.io.WKTReader;
import org.opengis.feature.GeometryAttribute;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.type.GeometryType;
import org.opengis.feature.type.PropertyType;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import pl.wodnet.shploader.*;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;
import pl.wodnet.shploader.dto.ImportErrorDTO;
import pl.wodnet.shploader.dto.ImportResultDTO;
import pl.wodnet.shploader.entity.*;
import pl.wodnet.shploader.entity.gesut7.*;
import pl.wodnet.shploader.entity.swde.BudynekEntity;
import pl.wodnet.shploader.entity.swde.DzialkaEntity;
import pl.wodnet.shploader.entity.swde.ObrebEntity;
import pl.wodnet.shploader.enums.CharsetEnum;
import pl.wodnet.shploader.enums.ShpImportModeEnum;
import pl.wodnet.shploader.enums.StatusEnum;
import pl.wodnet.shploader.geometry.SimpleFeatureExtended;
import pl.wodnet.shploader.service.*;
import pl.wodnet.shploader.tools.GeometryExtractor;
import pl.wodnet.shploader.tools.Tools;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class ShpController {

    @Autowired
    RestartService restartService;

    @Autowired
    ShpService shpService;

    @Autowired
    WodArmaturaService wodArmaturaService;

    @Autowired
    WodSieciService wodSieciService;

    @Autowired
    WodObiektyService wodObiektyService;

    @Autowired
    KanArmaturaService kanArmaturaService;

    @Autowired
    KanSieciService kanSieciService;

    @Autowired
    KanObiektyService kanObiektyService;

    @Autowired
    EngArmaturaService engArmaturaService;

    @Autowired
    EngSieciService engSieciService;

    @Autowired
    ConfService confService;

    @Autowired
    StatusService statusService;

    protected final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = "/importDirectory", method = RequestMethod.GET)
    public String importDirectory(@RequestParam ShpImportModeEnum mode, @RequestParam CharsetEnum kodowanie, @RequestParam(defaultValue = "true") boolean splitComplexGeom){
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        String filePath = resolveDirectoryPath(mode);
        List<String> tablesList = shpService.prepareTableListForMode(mode);
        shpService.truncateTables(tablesList, getSchemaOfMode(mode));
        shpService.truncateTables(Arrays.asList(new String[]{"shp"}), "shp");
        List<String> fileNameList = shpService.shpListSorted(filePath); //fileNameListSorted( filePath, "shp");
        List<GeoinfoKodyDTO> geoinfoKodyDTOList = confService.importGeoinfoKody();
        for(String fileName : fileNameList){
            try {
                shpService.importFile(Paths.get(filePath, fileName).toString(), geoinfoKodyDTOList, charset, splitComplexGeom);
            } catch (IOException e) {
                LOGGER.error(String.format("Wystapil blad: %s", e.getMessage()));
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Zakonczono wczytywanie plików");
        return "finish";
    }

    @RequestMapping(value = "/importDeclaredTables", method = RequestMethod.GET)
    public List<ImportResultDTO> importDeclaredTables(@RequestParam ShpImportModeEnum mode, @RequestParam CharsetEnum kodowanie, @RequestParam(defaultValue = "true") boolean splitComplexGeom){
        statusService.setStatus(StatusEnum.BUSY);
        List<ImportResultDTO> resultDTOList = new ArrayList<>();
        List<String> tablesList = shpService.prepareTableListForMode(mode);
        shpService.truncateTables(tablesList, getSchemaOfMode(mode));
        for(String tableName: tablesList){
            resultDTOList.addAll(importDirectoryByTableName(tableName, mode, kodowanie, splitComplexGeom));
//            LayerDTO layerDTO = new LayerDTO(tableName, count);
//            layerDTOList.add(layerDTO);
        }
        statusService.setStatus(StatusEnum.FREE);
//        restartService.restartApp();
        LOGGER.info("Zakonczono wczytywanie plików");
        return resultDTOList;
    }

    private String getSchemaOfMode(ShpImportModeEnum mode) {
        String schema = null;
        switch (mode){
            case SWDE:
                schema = "swde";
                break;

            case GESUT:
                schema = "gesut7";
                break;

            case SYTUACJA:
                schema = "sytuacja7";
                break;
        }
        return schema;
    }

    @RequestMapping(value = "importDirectoryByTableName", method = RequestMethod.GET)
    public List<ImportResultDTO> importDirectoryByTableName(@RequestParam String tableName, @RequestParam ShpImportModeEnum mode, @RequestParam CharsetEnum kodowanie, @RequestParam(defaultValue = "true") boolean splitComplexGeom){
        List<ImportResultDTO> resultDTOList = new ArrayList<>();
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        statusService.setStatus(StatusEnum.BUSY);
        String filePath = resolveDirectoryPath(mode);

        List<String> fileNameList = shpService.shpListSorted(filePath);
        List<GeoinfoKodyDTO> geoinfoKodyDTOList = confService.importGeoinfoKody();
        List<String> kodyMnemoniczneList = confService.getFileListByTableName(tableName, geoinfoKodyDTOList);
        List<String> tablesList = new ArrayList<>();
        tablesList.add(tableName);
        shpService.truncateTables(tablesList, getSchemaOfMode(mode));
        for(String fileName : fileNameList){
            for(String kodMnemoniczny: kodyMnemoniczneList){
                if(fileName.contains(kodBezGwiazdgki(kodMnemoniczny))){
                    try {
                        resultDTOList.add(shpService.importFile(Paths.get(filePath, fileName).toString(), geoinfoKodyDTOList, charset, splitComplexGeom));
                    } catch (IOException e) {
                        LOGGER.error(String.format("Wystapil blad: %s", e.getMessage()));
                        throw new RuntimeException(e);
                    }
                }
            }

        }
//        LOGGER.info("Zakonczono wczytywanie plików");
        statusService.setStatus(StatusEnum.FREE);
        return resultDTOList;
    }

    private static String resolveDirectoryPath(ShpImportModeEnum mode) {
        String filePath = Constants.SHP_GESUT_DIRECTORY;
        if(mode == ShpImportModeEnum.GESUT){
            filePath = Constants.SHP_GESUT_DIRECTORY;
        }else if(mode == ShpImportModeEnum.SWDE){
            filePath = Constants.SHP_SWDE_DIRECTORY;
        } else if (mode == ShpImportModeEnum.SYTUACJA) {
            filePath = Constants.SHP_SYTUACJA_DIRECTORY;
        }
        return filePath;
    }

    @RequestMapping(value = "checkNotRecognizedFiles", method = RequestMethod.GET)
    public List<ImportResultDTO> checkNotRecognizedFiles(@RequestParam ShpImportModeEnum mode, @RequestParam CharsetEnum kodowanie) throws IOException {
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        String filePath = Constants.SHP_GESUT_DIRECTORY; //todo TYLKO gesut DIRECTORY
        List<String> fileNameList = shpService.shpListSorted(filePath);
        List<GeoinfoKodyDTO> geoinfoKodyDTOList = confService.importGeoinfoKody();
        List<ImportResultDTO> nieznanyPlik = new ArrayList<>();
        for(String fileName : fileNameList){
            boolean contains = false;
            for(GeoinfoKodyDTO geoinfoKodyDTO: geoinfoKodyDTOList){
                if(fileName.contains(geoinfoKodyDTO.getKod_mnemoniczny())){
                    contains = true;
                }
            }
            if(!contains){
                nieznanyPlik.add(shpService.getShpInfo(Paths.get(filePath, fileName).toFile()));
            }
        }
        return nieznanyPlik;
    }

    @RequestMapping(value = "extractValidObjects", method = RequestMethod.GET)
    public List<String> extractValidObjects(@RequestParam String inputPath) {
        throw new NotImplementedException("Not implemented yet");
    }


    private Collection<Property> prepareProperties(Collection<Property> properties) {
        boolean hasXCODE_N = false;
        for(Property prop : properties){
            if(prop.getName().equals("XCODE_N")){
                hasXCODE_N = true;
            }
        }
        if(!hasXCODE_N){
            FeaturePropertyName fName = new FeaturePropertyName("XCODE_N");
            FeaturePropertyType fpt = new FeaturePropertyType(
                    fName, String.class, false, null, null, null);

            FeaturePropertyDescriptor fd = new FeaturePropertyDescriptor(fpt, fName, 0, 0, false );
            FeatureProperty featureProperty = new FeatureProperty("obreby", fd);
            properties.add(featureProperty);
        }
        return properties;
    }

    public List<String> fileNameList(String path, String extension) {
        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            result = walk.map(x -> x.getFileName().toString())
                    .filter(f -> f.endsWith("." + extension)).collect(Collectors.toList());
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<String> fileNameListSorted(String path, String extension) {
        List<String> result = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {

            result = walk.sorted(Comparator.comparingLong((Path path1) -> path1.toFile().length())
                    .reversed()).map(x -> x.getFileName().toString())
                    .filter(f -> f.endsWith("." + extension)).collect(Collectors.toList());
            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @GetMapping("/restart")
    public void restart() {
//        ShploaderApplication.restart();
        restartService.restartApp();
    }

    private String kodBezGwiazdgki(String kod){
        return kod.substring(0, 6);
    }

}
