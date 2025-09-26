package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.EngSieciEntity;
import pl.wodnet.shploader.repository.EngSieciRepository;

import java.util.List;

@Service
public class EngSieciService {
    @Autowired
    EngSieciRepository engSieciRepository;

    public EngSieciEntity save(EngSieciEntity engSieciEntity){
        return engSieciRepository.save(engSieciEntity);
    }

    public void save(List<EngSieciEntity> engSieciEntityList){
        engSieciRepository.saveAll(engSieciEntityList);
    }

}
