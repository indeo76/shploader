package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.WodObiektyEntity;
import pl.wodnet.shploader.repository.WodObiektyRepository;

import java.util.List;

@Service
public class WodObiektyService {
    @Autowired
    WodObiektyRepository wodObiektyRepository;


    public WodObiektyEntity save(WodObiektyEntity wodObiektyEntity){
        return wodObiektyRepository.save(wodObiektyEntity);
    }

    public void save(List<WodObiektyEntity> wodObiektyList){
        wodObiektyRepository.saveAll(wodObiektyList);
    }

}
