package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.EngArmaturaEntity;
import pl.wodnet.shploader.repository.EngArmaturaRepository;

import java.util.List;

@Service
public class EngArmaturaService {
    @Autowired
    EngArmaturaRepository engArmaturaRepository;

    public EngArmaturaEntity save(EngArmaturaEntity engArmaturaEntity){
        return this.engArmaturaRepository.save(engArmaturaEntity);
    }

    public void save(List<EngArmaturaEntity> engArmaturaEntityList){
        this.engArmaturaRepository.saveAll(engArmaturaEntityList);
    }

}
