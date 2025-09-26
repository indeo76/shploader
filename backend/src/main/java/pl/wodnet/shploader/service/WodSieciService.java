package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.WodSieciEntity;
import pl.wodnet.shploader.repository.WodSieciRepository;

import java.util.List;

@Service
public class WodSieciService {
    @Autowired
    WodSieciRepository wodSieciRepository;

    public WodSieciEntity save(WodSieciEntity wodSieciEntity){
        return wodSieciRepository.save(wodSieciEntity);
    }

    public void save(List<WodSieciEntity> wodSieciList){
        wodSieciRepository.saveAll(wodSieciList);
    }

}
