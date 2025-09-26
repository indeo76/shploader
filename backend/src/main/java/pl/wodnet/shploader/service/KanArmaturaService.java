package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.KanArmaturaEntity;
import pl.wodnet.shploader.repository.KanArmaturaRepository;

import java.util.List;

@Service
public class KanArmaturaService {
    @Autowired
    KanArmaturaRepository kanArmaturaRepository;

    public KanArmaturaEntity save(KanArmaturaEntity kanArmaturaEntity){
        return kanArmaturaRepository.save(kanArmaturaEntity);
    }

    public void save(List<KanArmaturaEntity> kanArmaturaEntityList){
        kanArmaturaRepository.saveAll(kanArmaturaEntityList);
    }
}
