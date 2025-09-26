package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.KanObiektyEntity;
import pl.wodnet.shploader.repository.KanObiektyRepository;

import java.util.List;

@Service
public class KanObiektyService {
    @Autowired
    KanObiektyRepository kanObiektyRepository;

    public KanObiektyEntity save(KanObiektyEntity kanObiektyEntity){
        return kanObiektyRepository.save(kanObiektyEntity);
    }

    public void save(List<KanObiektyEntity> kanObiektyList){
        kanObiektyRepository.saveAll(kanObiektyList);
    }
}
