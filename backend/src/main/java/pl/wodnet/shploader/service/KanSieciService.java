package pl.wodnet.shploader.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.KanSieciEntity;
import pl.wodnet.shploader.repository.KanSieciRepository;

import java.util.List;

@Service
public class KanSieciService {
    @Autowired
    KanSieciRepository kanSieciRepository;

    public KanSieciEntity save(KanSieciEntity kanSieci){
        return kanSieciRepository.save(kanSieci);
    }

    public void save(List<KanSieciEntity> kanSieciList){
        kanSieciRepository.saveAll(kanSieciList);
    }

}
