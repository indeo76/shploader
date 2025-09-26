package pl.wodnet.shploader.service;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
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
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;
import pl.wodnet.shploader.dto.ImportErrorDTO;
import pl.wodnet.shploader.dto.ImportResultDTO;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.entity.gesut7.*;
import pl.wodnet.shploader.entity.swde.BudynekEntity;
import pl.wodnet.shploader.entity.swde.DzialkaEntity;
import pl.wodnet.shploader.entity.swde.ObrebEntity;
import pl.wodnet.shploader.enums.FeatureError;
import pl.wodnet.shploader.enums.ShpImportModeEnum;
import pl.wodnet.shploader.repository.ShpRepository;
import pl.wodnet.shploader.tools.Tools;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ShpService {
    @PersistenceContext
    private EntityManager em;

    @Autowired
    ShpRepository shpRepository;

    @Autowired
    ConfService confService;

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private String BATCH_SIZE;

    protected final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    public ShpEntity save(ShpEntity shpEntity){
        return shpRepository.save(shpEntity);
    }

    public void save(List<ShpEntity> entityList){
        shpRepository.saveAll(entityList);
    }

    public Integer processFeatures(List<GeoinfoKodyDTO> geoinfoKodyDTOList, SimpleFeatureIterator iterator, File file, String key, int targetSrid, List<ImportErrorDTO> errors, boolean splitComplexGeom) throws TransformException, ParseException, FactoryException {
        int i = 0;
        Integer iGeomObjects = 0;
        while (iterator.hasNext()) {
            i++;
            final SimpleFeature feature = iterator.next();
            iGeomObjects += processFeature(em, file, feature, iGeomObjects, key, targetSrid, geoinfoKodyDTOList, i, BATCH_SIZE, errors, splitComplexGeom).size();
        }
        synchronizeTransaction(em);
        em.close();
        return iGeomObjects;
    }

    public List<ShpEntity> processFeature(EntityManager em, File file, SimpleFeature feature, Integer iGeomObjects, String key, int targetSrid, List<GeoinfoKodyDTO> geoinfoKodyDTOList, int i, String BATCH_SIZE, List<ImportErrorDTO> errors, boolean splitComplexGeom) throws TransformException, ParseException, FactoryException {
        List<ShpEntity> shpEntityList = new ArrayList<>();
        Geometry complexGeometry = (Geometry) feature.getDefaultGeometry();
        if(complexGeometry != null){
            if(splitComplexGeom){

            } else {

            }
            Integer geometriesCount = complexGeometry.getNumGeometries();
            if(geometriesCount > 1){
                LOGGER.warn(String.format("Plik: %s Liczba geometrii > 1: %s, %s: %s", file.getName(),geometriesCount, key, feature.getProperty(key).getValue()));
            }else if(geometriesCount ==0){
                LOGGER.info("Liczba geometrii: " + geometriesCount);
            }
            for(Integer iGeom = 0; iGeom< geometriesCount; iGeom++){
                //todo cała reszta..
//                iGeomObjects ++;
                Geometry g = complexGeometry.getGeometryN(iGeom);
                if(g != null){
                    GeometryAttribute sourceGeometry = feature.getDefaultGeometryProperty();
                    GeometryType geometryType = sourceGeometry.getType();
                    CoordinateReferenceSystem sourceCRS = geometryType.getCoordinateReferenceSystem();
                    if(sourceCRS == null){
                        sourceCRS = CRS.decode("EPSG:" + 2177);
                        //LOGGER.info("Brak ukladu wspolrzednych");
                    }
//                    ReferenceIdentifier sref = sourceCRS.getCoordinateSystem().getName();
//                    String srName = sref.getCode();

                    CoordinateReferenceSystem targetCRS = CRS.decode("EPSG:" + targetSrid);
                    MathTransform transform = null;
                    try{
                        transform = CRS.findMathTransform(sourceCRS, targetCRS, true);
                    }catch (Exception ex){
                        int d = 0;
                    }

                    org.locationtech.jts.geom.Geometry reprojectedGeometry = JTS.transform(g, transform);
                    g = reprojectedGeometry;

                    PrecisionModel pmodel = new PrecisionModel();
                    pmodel.makePrecise(2); // ?????
                    String wkt = g.toString();


                    GeometryFactory factory = new GeometryFactory(pmodel, targetSrid);

                    WKTReader wktRdr = new WKTReader(factory);
                    Geometry geom = wktRdr.read(wkt);
                    Geometry complexGeom = wktRdr.read(complexGeometry.toString());
                    complexGeom.setSRID(2177);
                    complexGeom = JTS.transform(complexGeom, transform);
                    geom.setSRID(targetSrid);
                    //end processFeature

                    Map<String, String> properties = new HashMap<>();
                    Map<String, String> propertyTypes = new HashMap<>();
//                        Collection<Property> z = prepareProperties(feature.getProperties());
                    List<Property> propertyList = new ArrayList<>(feature.getProperties());

                    String kodMnemoniczny = null;
                    ShpEntity shpEntity = new ShpEntity();
                    kodMnemoniczny = findKodMnemoniczny(shpEntity, properties, propertyTypes, propertyList, kodMnemoniczny);
                    if(kodMnemoniczny==null){
                        kodMnemoniczny = Tools.getNameWithoutExtension(file.getName());
                    }
                    String tableName = confService.findTargetTable(kodMnemoniczny, geoinfoKodyDTOList);
                    shpEntity.setTableName(tableName);
                    shpEntity.setGeom(geom);
//                                shpEntity.setGeom(GeometryExtractor.extractGeometry(feature));
                    //shpEntity.setPropertyTypes(propertyTypes);
                    //shpEntity.setProperties(properties);
                    if(shpEntity.getTableName() !=null){
                        processShpEntity(em, shpEntity);
                        shpEntityList.add(shpEntity);
                        iGeomObjects = iGeomObjects + 1;
                    }
                    if(i % Integer.parseInt(BATCH_SIZE) == 0){
                        synchronizeTransaction(em);
//                            LOGGER.info("Czas if(i % " + i + " == 0) start: " + (System.currentTimeMillis() - startTime));
                        LOGGER.info(String.format("Saved: %s (%s): %s features / geoms: %s", tableName, kodMnemoniczny, i, iGeomObjects));
//                            LOGGER.info("Czas if(i % " + i + " == 0) end: " + (System.currentTimeMillis() - startTime));
                    }
                }
            }
        }else{
            String identifier = feature.getProperty(key).getValue().toString();
            LOGGER.error(String.format("Pusta geometria w obiekcie w pliku: %s  %s: %s", file.getName(), key, identifier));
            errors.add(new ImportErrorDTO(key, identifier.toString(), FeatureError.PUSTA_GEOMETRIA));
        }
        return shpEntityList;
    }

    public List<String> prepareTableListForMode(ShpImportModeEnum mode){
        List<String> tablesList = new ArrayList<>();
        if(mode == ShpImportModeEnum.GESUT){
            tablesList.add(Constants.WOD_SIECI);
            tablesList.add(Constants.WOD_ARMATURA);
            tablesList.add(Constants.WOD_OBIEKTY);
            tablesList.add(Constants.KAN_SIECI);
            tablesList.add(Constants.KAN_ARMATURA);
            tablesList.add(Constants.KAN_OBIEKTY);
            tablesList.add(Constants.ENG_SIECI);
            tablesList.add(Constants.ENG_ARMATURA);
            tablesList.add(Constants.ENG_OBIEKTY);
            tablesList.add(Constants.GAZ_SIECI);
            tablesList.add(Constants.GAZ_ARMATURA);
            tablesList.add(Constants.GAZ_OBIEKTY);
            tablesList.add(Constants.TEL_SIECI);
            tablesList.add(Constants.TEL_ARMATURA);
            tablesList.add(Constants.TEL_OBIEKTY);
            tablesList.add(Constants.CO_ARMATURA);
            tablesList.add(Constants.CO_SIECI);
            tablesList.add(Constants.CO_OBIEKTY);
            tablesList.add(Constants.SPC_ARMATURA);
            tablesList.add(Constants.SPC_SIECI);
            tablesList.add(Constants.SPC_OBIEKTY);
        }else if(mode == ShpImportModeEnum.SWDE){
            tablesList.add(Constants.OBREBY);
            tablesList.add(Constants.BUDYNKI);
            tablesList.add(Constants.DZIALKI);
        }else if(mode == ShpImportModeEnum.SYTUACJA){
            tablesList.add(Constants.SYTUACJA);
        }
        return tablesList;
    }

    private String findKodMnemoniczny(ShpEntity shpEntity, Map<String, String> properties, Map<String, String> propertyTypes, List<Property> propertyList, String kodMnemoniczny) {
        for(Property prop : propertyList){
            try{
                String name = prop.getName().toString();
                name = clearDiacriticalChars(name);
                if(prop.getValue() != null){
                    if(name.equals("XCODE_N")){
                        kodMnemoniczny = prop.getValue().toString();
                        shpEntity.setTyp(kodMnemoniczny);
                    }
                    if(!name.equals("the_geom")){
                        PropertyType type = prop.getType();
                        String typeBinding = type.getBinding().getName();
                        propertyTypes.put(name, typeBinding);
                        String value = prop.getValue().toString();

                        //value = new String(value.getBytes("Cp1250"), "UTF-8");

                        byte[] bytes = value.getBytes("Cp1250");
                        String utf8EncodedString = new String(bytes, StandardCharsets.UTF_8);
                        properties.put(name, utf8EncodedString);
                        //todo przypisanie własności
                        //todo znajdź metode do ustawienia
                        if(typeBinding.equals("java.lang.String")){
                            try{
                                Method metoda = ShpEntity.class.getMethod("set" + name, String.class);
                                metoda.invoke(shpEntity, value);
                            }catch (Exception ex){
//                                LOGGER.error(String.format("Nie mozna przypisac property (String): %s, value: %s", name, value));
                            }
                        }else if(typeBinding.equals("java.lang.Integer")){
                            try {
                                Method metoda = ShpEntity.class.getMethod("set" + name, Integer.class);
                                metoda.invoke(shpEntity, Integer.parseInt(value));
                            }catch (Exception ex){
//                                LOGGER.error(String.format("Nie mozna przypisac property (Integer): %s, value: %s", name, value));
                            }
                        }else if(typeBinding.equals("java.lang.Long")){
                            try{
                                Method metoda = ShpEntity.class.getMethod("set" + name, Long.class);
                                metoda.invoke(shpEntity, Long.parseLong(value));
                            }catch (Exception ex){
//                                LOGGER.error(String.format("Nie mozna przypisac property (Long): %s, value: %s", name, value));
                            }
                        }else if(typeBinding.equals("java.lang.Double")){
                            try{
                                Method metoda = ShpEntity.class.getMethod("set" + name, Double.class);
                                metoda.invoke(shpEntity, Double.parseDouble(value));
                            }catch (Exception ex){
//                                LOGGER.error(String.format("Nie mozna przypisac property (Double): %s, value: %s", name, value));
                            }
                        }

                    }
                } else {
                    properties.put(name, null);
                }
            }catch (Exception ex){
                System.out.println(ex);
            }
        }
        return kodMnemoniczny;
    }

    private static String clearDiacriticalChars(String name) {
        name = name.replaceAll("Ę", "E");
        name = name.replaceAll("Ń", "N");
        name = name.replaceAll("Ó", "O");
        return name;
    }

    private void processShpEntity(EntityManager em, ShpEntity shpEntity) {
        em.persist(shpEntity);
        if(shpEntity.getTableName().contains(Constants.WOD_ARMATURA)){
            WodArmaturaEntity wodArmatura = new WodArmaturaEntity(shpEntity);
            em.persist(wodArmatura);
        }else if(shpEntity.getTableName().contains(Constants.WOD_SIECI)){
            WodSieciEntity wodSieci = new WodSieciEntity(shpEntity);
            em.persist(wodSieci);
        }else if(shpEntity.getTableName().contains(Constants.WOD_OBIEKTY)) {
            WodObiektyEntity wodObiekty = new WodObiektyEntity(shpEntity);
            em.persist(wodObiekty);
        }else if(shpEntity.getTableName().contains(Constants.KAN_ARMATURA)){
            KanArmaturaEntity kanArmatura = new KanArmaturaEntity(shpEntity);
            em.persist(kanArmatura);
        }else if(shpEntity.getTableName().contains(Constants.KAN_SIECI)){
            KanSieciEntity kanSieci = new KanSieciEntity(shpEntity);
            em.persist(kanSieci);
        }else if(shpEntity.getTableName().contains(Constants.KAN_OBIEKTY)) {
            KanObiektyEntity kanObiekty = new KanObiektyEntity(shpEntity);
            em.persist(kanObiekty);
        }else if(shpEntity.getTableName().contains(Constants.ENG_ARMATURA)){
            EngArmaturaEntity engArmatura = new EngArmaturaEntity(shpEntity);
            em.persist(engArmatura);
        }else if(shpEntity.getTableName().contains(Constants.ENG_SIECI)) {
            EngSieciEntity engSieci = new EngSieciEntity(shpEntity);
            em.persist(engSieci);
        }else if(shpEntity.getTableName().contains(Constants.ENG_OBIEKTY)){
            EngObiektyEntity engObiekty = new EngObiektyEntity(shpEntity);
            em.persist(engObiekty);
        }else if(shpEntity.getTableName().contains(Constants.GAZ_SIECI)){
            GazSieciEntity gazSieci = new GazSieciEntity(shpEntity);
            em.persist(gazSieci);
        }else if(shpEntity.getTableName().contains(Constants.GAZ_ARMATURA)) {
            GazArmaturaEntity gazArmatura = new GazArmaturaEntity(shpEntity);
            em.persist(gazArmatura);
        }else if(shpEntity.getTableName().contains(Constants.GAZ_OBIEKTY)) {
            GazObiektyEntity gazObiekty = new GazObiektyEntity(shpEntity);
            em.persist(gazObiekty);
        }else if(shpEntity.getTableName().contains(Constants.TEL_SIECI)) {
            TelSieciEntity telSieci = new TelSieciEntity(shpEntity);
            em.persist(telSieci);
        }else if(shpEntity.getTableName().contains(Constants.TEL_ARMATURA)) {
            TelArmaturaEntity telArmatura = new TelArmaturaEntity(shpEntity);
            em.persist(telArmatura);
        }else if(shpEntity.getTableName().contains(Constants.TEL_OBIEKTY)) {
            TelObiektyEntity telObiekty = new TelObiektyEntity(shpEntity);
            em.persist(telObiekty);
        }else if(shpEntity.getTableName().contains(Constants.CO_ARMATURA)) {
            CoArmaturaEntity coArmaturaEntity = new CoArmaturaEntity(shpEntity);
            em.persist(coArmaturaEntity);
        }else if(shpEntity.getTableName().contains(Constants.CO_SIECI)) {
            CoSieciEntity coSieciEntity = new CoSieciEntity(shpEntity);
            em.persist(coSieciEntity);
        }else if(shpEntity.getTableName().contains(Constants.CO_OBIEKTY)) {
            CoObiektyEntity coObiektyEntity = new CoObiektyEntity(shpEntity);
            em.persist(coObiektyEntity);
        }else if(shpEntity.getTableName().contains(Constants.SPC_ARMATURA)) {
            SpcArmaturaEntity spcArmaturaEntity = new SpcArmaturaEntity(shpEntity);
            em.persist(spcArmaturaEntity);
        }else if(shpEntity.getTableName().contains(Constants.SPC_SIECI)) {
            SpcSieciEntity spcSieciEntity = new SpcSieciEntity(shpEntity);
            em.persist(spcSieciEntity);
        }else if(shpEntity.getTableName().contains(Constants.SPC_OBIEKTY)){
            SpcObiektyEntity spcObiektyEntity = new SpcObiektyEntity(shpEntity);
            em.persist(spcObiektyEntity);
        }else if(shpEntity.getTableName().contains(Constants.SYTUACJA)){
            SytuacjaEntity sytuacja = new SytuacjaEntity(shpEntity);
            em.persist(sytuacja);
        }else if(shpEntity.getTableName().contains(Constants.OBREBY)) {
            ObrebEntity obrebEntity = new ObrebEntity(shpEntity);
            em.persist(obrebEntity);
        }else if (shpEntity.getTableName().contains(Constants.DZIALKI)){
            DzialkaEntity dzialkaEntity = new DzialkaEntity(shpEntity);
            em.persist(dzialkaEntity);
        }else if(shpEntity.getTableName().contains(Constants.BUDYNKI)){
            BudynekEntity budynekEntity = new BudynekEntity(shpEntity);
            em.persist(budynekEntity);
        }
    }

    public void synchronizeTransaction(EntityManager em) {
        em.createNativeQuery("COMMIT").executeUpdate();
        em.flush();
        em.clear();
    }
    public void truncateTables(List<String> tablesList, String schema) {
        for(String tableName: tablesList){
            em.createNativeQuery("TRUNCATE TABLE " + schema + "." + tableName).executeUpdate();
        }
        synchronizeTransaction(em);
    }

    public List<String> shpListSorted(String path){
        List<String> result = new ArrayList<>();
        List<String> dbfList = new ArrayList<>();
        try (Stream<Path> walk = Files.walk(Paths.get(path))) {
            dbfList = walk.sorted(Comparator.comparingLong((Path path1) -> path1.toFile().length()))
                    .map(x -> x.getFileName().toString())
                    .filter(f -> f.endsWith(".dbf")).collect(Collectors.toList());

            for(String dbfName: dbfList){
                String shpName = dbfName.replace("dbf", "shp");
                if(Paths.get(path,shpName).toFile().exists()){
                    result.add(shpName);
                }
            }

//            result.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public ImportResultDTO getShpInfo(File file) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("url", file.toURI().toURL());
        DataStore dataStore = DataStoreFinder.getDataStore(map);
        Integer featureCount = 0;
        if (dataStore != null) {
            SimpleFeatureSource featureSource = dataStore.getFeatureSource(dataStore.getTypeNames()[0]);
            featureCount = featureSource.getFeatures().size();
            System.out.println("Liczba obiektów w pliku SHP: " + featureCount);
            dataStore.dispose();
        } else {
            System.out.println("Nie udało się wczytać pliku SHP.");
        }
        ImportResultDTO dto = new ImportResultDTO(file.getName(), featureCount, 0, new ArrayList<>());
        return dto;
    }

}
