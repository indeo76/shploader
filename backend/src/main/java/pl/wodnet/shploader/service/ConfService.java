package pl.wodnet.shploader.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;
import pl.wodnet.shploader.geoinfokody.GeoinfoKodyService;
import pl.wodnet.shploader.service.classification.TargetTableProvider;
import pl.wodnet.shploader.service.classification.TargetTableResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ConfService {
    static final Logger LOGGER = LoggerFactory.getLogger(ConfService.class);

    @Value("${GEOINFO_KODY_PROVIDER:file}")
    private String GEOINFO_KODY_PROVIDER;

    private final GeoinfoKodyService geoinfoKodyService;

    public ConfService(GeoinfoKodyService geoinfoKodyService) {
        this.geoinfoKodyService = geoinfoKodyService;
    }

    public List<GeoinfoKodyDTO> importGeoinfoKody(){
        if(GEOINFO_KODY_PROVIDER.equals("database")){
            return getFromDatabase();
        } else {
            return getFromFile();
        }
    }

    private List<GeoinfoKodyDTO> getFromDatabase() {
        return geoinfoKodyService.getFromDatabase();
    }

    private static List<GeoinfoKodyDTO> getFromFile() {
        List<GeoinfoKodyDTO> geoifoKodyDTOList = null;
        String filePath = Constants.GEOINFO_KODY_FILE;

        String confFile = null;
        try {
            confFile = new Scanner(new File(filePath)).useDelimiter("\\Z").next();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            geoifoKodyDTOList = mapper.readValue(confFile, new TypeReference<List<GeoinfoKodyDTO>>(){});
        }catch (IOException e){
            LOGGER.error("Exception: " + e.getMessage());
        }
        return geoifoKodyDTOList;
    }

    @Cacheable(value = "findTargetTable", unless="#result==null")
    public TargetTableResult findTargetTable(String kodMnemoniczny, List<GeoinfoKodyDTO> geoinfoKodyDTOS){
        TargetTableResult result = new TargetTableResult(null, TargetTableProvider.BRAK);
        for(GeoinfoKodyDTO geoinfo:geoinfoKodyDTOS){
            if(kodMnemoniczny.equals(geoinfo.getKod_mnemoniczny())){
                result = new TargetTableResult(geoinfo.getTabela(), TargetTableProvider.GEOINFO_KODY);
                break;
            }
        }
        return result;
    }

    public List<String> getFileListByTableName(String tableName, List<GeoinfoKodyDTO> geoinfoKodyDTOList) {
        List<String> fileListFiltered = new ArrayList<>();
        for(GeoinfoKodyDTO geoinfoKodyDTO: geoinfoKodyDTOList){
            if(geoinfoKodyDTO.getTabela().contains(tableName)){
                fileListFiltered.add(geoinfoKodyDTO.getKod_mnemoniczny());
            }
        }
        return fileListFiltered;
    }
}
