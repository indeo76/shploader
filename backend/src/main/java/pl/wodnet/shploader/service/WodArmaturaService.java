package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.WodArmaturaEntity;
import pl.wodnet.shploader.repository.WodArmaturaRepository;

import java.util.List;

@Service
public class WodArmaturaService {
    @Autowired
    WodArmaturaRepository wodArmaturaRepository;

    public WodArmaturaEntity save(WodArmaturaEntity wodArmaturaEntity){
        return wodArmaturaRepository.save(wodArmaturaEntity);
    }

    public void save(List<WodArmaturaEntity> wodArmaturaEntityList){
        wodArmaturaRepository.saveAll(wodArmaturaEntityList);
    }

}
