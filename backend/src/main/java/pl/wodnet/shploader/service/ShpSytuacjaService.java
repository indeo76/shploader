package pl.wodnet.shploader.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpSwdeEntity;
import pl.wodnet.shploader.entity.ShpSytuacjaEntity;
import pl.wodnet.shploader.entity.gesut7.*;
import pl.wodnet.shploader.entity.sytuacja.SytuacjaEntity;
import pl.wodnet.shploader.provider.ShpSwdeEntityProvider;
import pl.wodnet.shploader.provider.ShpSytuacjaEntityProvider;

import javax.persistence.EntityManager;

@Service
@Transactional
public class ShpSytuacjaService extends AbstractShpService<ShpSytuacjaEntity> {

    public ShpSytuacjaService(ShpSytuacjaEntityProvider entityProvider) {
        super(entityProvider);
    }

    @Transactional
    @Override
    public void processShpEntity(EntityManager em, ShpSytuacjaEntity shpEntity) {
        if(shpEntity.getTableName().contains(Constants.SYTUACJA)){
            SytuacjaEntity sytuacja = new SytuacjaEntity(shpEntity);
            em.persist(sytuacja);
        }
    }

}
