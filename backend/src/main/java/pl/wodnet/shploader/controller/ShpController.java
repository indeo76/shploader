package pl.wodnet.shploader.controller;

import org.apache.commons.lang3.NotImplementedException;
import org.opengis.feature.Property;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.wodnet.shploader.*;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;
import pl.wodnet.shploader.dto.ImportResultDTO;
import pl.wodnet.shploader.entity.ShpBaseEntity;
import pl.wodnet.shploader.enums.CharsetEnum;
import pl.wodnet.shploader.enums.ShpImportModeEnum;
import pl.wodnet.shploader.systemstatus.StatusEnum;
import pl.wodnet.shploader.service.*;
import pl.wodnet.shploader.systemstatus.StatusService;

import java.io.IOException;
import java.nio.charset.Charset;
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

    AbstractShpService<?> service;

    @Autowired
    ShpService shpGesutService;

    @Autowired
    ShpSwdeService shpSwdeService;

    @Autowired
    ShpSytuacjaService  shpSytuacjaService;

//    @Autowired
//    ShpSytuacjaService shpSytuacjaService;

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
        statusService.setBuisy(mode);
        service = getService(mode);
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        String filePath = resolveDirectoryPath(mode);
        List<String> tablesList = service.prepareTableListForMode(mode);
        service.truncateTables(tablesList, getSchemaOfMode(mode));
        service.truncateTables(Arrays.asList(new String[]{"shp"}), "shp");
        List<String> fileNameList = service.shpListSorted(filePath); //fileNameListSorted( filePath, "shp");
        List<GeoinfoKodyDTO> geoinfoKodyDTOList = confService.importGeoinfoKody();
        for(String fileName : fileNameList){
            try {
                service.importFile(Paths.get(filePath, fileName).toString(), geoinfoKodyDTOList, charset, splitComplexGeom);
            } catch (IOException e) {
                LOGGER.error(String.format("Wystapil blad: %s", e.getMessage()));
                throw new RuntimeException(e);
            }
        }
        LOGGER.info("Zakonczono wczytywanie plików");
        statusService.setFree();
        return "finish";
    }

    @RequestMapping(value = "/importDeclaredTables", method = RequestMethod.GET)
    public List<ImportResultDTO> importDeclaredTables(@RequestParam ShpImportModeEnum mode, @RequestParam CharsetEnum kodowanie, @RequestParam(defaultValue = "true") boolean splitComplexGeom){
        statusService.setBuisy(mode);
        service = getService(mode);
        List<ImportResultDTO> resultDTOList = new ArrayList<>();
        List<String> tablesList = service.prepareTableListForMode(mode);
        service.truncateTables(tablesList, getSchemaOfMode(mode));
        for(String tableName: tablesList){
            resultDTOList.addAll(importDirectoryByTableName(tableName, mode, kodowanie, splitComplexGeom));
//            LayerDTO layerDTO = new LayerDTO(tableName, count);
//            layerDTOList.add(layerDTO);
        }
//        restartService.restartApp();
        LOGGER.info("Zakonczono wczytywanie plików");
        statusService.setFree();
        return resultDTOList;
    }

    private AbstractShpService<?> getService(ShpImportModeEnum mode) {
        switch (mode){
            case GESUT:
                return shpGesutService;
            case SWDE:
                return shpSwdeService;
            case SYTUACJA:
                return shpSytuacjaService;
        }
        return null;
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
        statusService.setBuisy(mode);
        service = getService(mode);
        List<ImportResultDTO> resultDTOList = new ArrayList<>();
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        String filePath = resolveDirectoryPath(mode);

        List<String> fileNameList = service.shpListSorted(filePath);
        List<GeoinfoKodyDTO> geoinfoKodyDTOList = confService.importGeoinfoKody();
        List<String> kodyMnemoniczneList = confService.getFileListByTableName(tableName, geoinfoKodyDTOList);
        List<String> tablesList = new ArrayList<>();
        tablesList.add(tableName);
        service.truncateTables(tablesList, getSchemaOfMode(mode));
        for(String fileName : fileNameList){
            for(String kodMnemoniczny: kodyMnemoniczneList){
                if(fileName.contains(kodBezGwiazdgki(kodMnemoniczny))){
                    try {
                        resultDTOList.add(service.importFile(Paths.get(filePath, fileName).toString(), geoinfoKodyDTOList, charset, splitComplexGeom));
                    } catch (IOException e) {
                        LOGGER.error(String.format("Wystapil blad: %s", e.getMessage()));
                        throw new RuntimeException(e);
                    }
                }
            }

        }
//        LOGGER.info("Zakonczono wczytywanie plików");
        statusService.setFree();
        LOGGER.info("Zakonczono wczytywanie plików");
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
        service = getService(mode);
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        String filePath = Constants.SHP_GESUT_DIRECTORY; //todo TYLKO gesut DIRECTORY
        List<String> fileNameList = service.shpListSorted(filePath);
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
                nieznanyPlik.add(service.getShpInfo(Paths.get(filePath, fileName).toFile()));
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
