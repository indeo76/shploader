package pl.wodnet.shploader.controller;

import org.apache.commons.lang3.NotImplementedException;
import org.opengis.feature.Property;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.wodnet.shploader.*;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;
import pl.wodnet.shploader.dto.ImportErrorDTO;
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
@RequestMapping("/api")
@CrossOrigin(origins = "*")
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
    public ResponseEntity<String> importDirectory(@RequestParam ShpImportModeEnum mode, @RequestParam(required = false) CharsetEnum kodowanie, @RequestParam(defaultValue = "true") boolean splitComplexGeom){
        if(statusService.isBuisy()){
            return ResponseEntity.status(HttpStatus.LOCKED)
                    .header("Komunikat", "Import zablokowany - serwis importu jest aktualnie zajety.").build();
        }
        statusService.setBuisy(mode);
        service = getService(mode);
        if(kodowanie == null){
            kodowanie = service.detectCharset(resolveDirectoryPath(mode));
        }
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        String filePath = resolveDirectoryPath(mode);
        List<String> tablesList = service.prepareTableListForMode(mode);
        truncateTables(tablesList, mode);
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
        return new ResponseEntity<>("finish", HttpStatus.OK);
    }

    private void truncateTables(List<String> tablesList, ShpImportModeEnum mode) {
        service.truncateTables(tablesList, getSchemaOfMode(mode));
        switch(mode){
            case GESUT:
                service.truncateTables(Arrays.asList(new String[]{"shp_gesut"}), "shp");
                break;
            case SYTUACJA:
                service.truncateTables(Arrays.asList(new String[]{"shp_sytuacja"}), "shp");
                break;
            case SWDE:
                service.truncateTables(Arrays.asList(new String[]{"shp_swde"}), "shp");
                break;
        }
    }

    @RequestMapping(value = "/importDeclaredTables", method = RequestMethod.GET)
    public ResponseEntity<List<ImportResultDTO>> importDeclaredTables(@RequestParam ShpImportModeEnum mode, @RequestParam(required = false) CharsetEnum kodowanie, @RequestParam(defaultValue = "true") boolean splitComplexGeom){
        if(statusService.isBuisy()){
            return ResponseEntity.status(HttpStatus.LOCKED)
                    .header("Komunikat", "Import zablokowany - serwis importu jest aktualnie zajety.").build();
        }
        statusService.setBuisy(mode);
        service = getService(mode);
        if(kodowanie == null){
            kodowanie = service.detectCharset(resolveDirectoryPath(mode));
        }
        List<ImportResultDTO> resultDTOList = new ArrayList<>();
        List<String> tablesList = service.prepareTableListForMode(mode);
        truncateTables(tablesList, mode);
        for(String tableName: tablesList){
            updateResultList(resultDTOList, importDirectoryByTableName(tableName, mode, kodowanie, splitComplexGeom));
        }
        LOGGER.info("Zakonczono wczytywanie plików");
        statusService.setFree();
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }

    private void updateResultList(List<ImportResultDTO> resultDTOList, List<ImportResultDTO> importResultDTOS) {
        for (ImportResultDTO newResult : importResultDTOS) {
            Optional<ImportResultDTO> existingOpt = resultDTOList.stream()
                    .filter(r -> r.getFileName().equals(newResult.getFileName()))
                    .findFirst();

            if (existingOpt.isPresent()) {
                ImportResultDTO existing = existingOpt.get();

                // Połącz dane
                int combinedTotal = existing.getTotalCount() + newResult.getTotalCount();
                int combinedSaved = existing.getSavedCount() + newResult.getSavedCount();
                List<ImportErrorDTO> combinedErrors = new ArrayList<>(existing.getErrors());
                combinedErrors.addAll(newResult.getErrors());

                // Utwórz nowy obiekt i zastąp w liście
                ImportResultDTO updated = new ImportResultDTO(
                        existing.getFileName(),
                        combinedTotal,
                        combinedSaved,
                        combinedErrors
                );

                int index = resultDTOList.indexOf(existing);
                resultDTOList.set(index, updated);

            } else {
                // Nie istnieje — dodaj nowy
                resultDTOList.add(newResult);
            }
        }
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
    public List<ImportResultDTO> importDirectoryByTableName(@RequestParam String tableName, @RequestParam ShpImportModeEnum mode, @RequestParam(required = false) CharsetEnum kodowanie, @RequestParam(defaultValue = "true") boolean splitComplexGeom){
        statusService.setBuisy(mode);
        service = getService(mode);
        if(kodowanie == null){
            kodowanie = service.detectCharset(resolveDirectoryPath(mode));
        }
        List<ImportResultDTO> resultDTOList = new ArrayList<>();
        Charset charset = Charset.forName(kodowanie.getCharsetName());
        String filePath = resolveDirectoryPath(mode);

        List<String> fileNameList = service.shpListSorted(filePath);
        List<GeoinfoKodyDTO> geoinfoKodyDTOList = confService.importGeoinfoKody();
        List<String> kodyMnemoniczneList = confService.getFileListByTableName(tableName, geoinfoKodyDTOList);
        List<String> tablesList = new ArrayList<>();
        tablesList.add(tableName);
        service.truncateTables(tablesList, getSchemaOfMode(mode));
        List<String> importedFileNameList = new ArrayList<>();
        for(String fileName : fileNameList){
            for(String kodMnemoniczny: kodyMnemoniczneList){
                //uwaga - trzeba zapamietać i sprawdzić czy dany plik nie został przypadkiem już wczytany przez inną regułę (duble):
                if(fileName.contains(kodBezGwiazdgki(kodMnemoniczny)) && !importedFileNameList.contains(fileName)){
                    try {
                        resultDTOList.add(service.importFile(Paths.get(filePath, fileName).toString(), geoinfoKodyDTOList, charset, splitComplexGeom));
                        importedFileNameList.add(fileName);
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
    public ResponseEntity<List<ImportResultDTO>> checkNotRecognizedFiles(@RequestParam ShpImportModeEnum mode, @RequestParam CharsetEnum kodowanie) throws IOException {
        if(statusService.isBuisy()){
            return ResponseEntity.status(HttpStatus.LOCKED)
                    .header("Komunikat", "Import zablokowany – zasób jest aktualnie zajęty.").build();
        }
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
        return new ResponseEntity<>(nieznanyPlik, HttpStatus.OK);
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
