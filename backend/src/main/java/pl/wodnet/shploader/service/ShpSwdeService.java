package pl.wodnet.shploader.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpSwdeEntity;
import pl.wodnet.shploader.entity.gesut7.*;
import pl.wodnet.shploader.entity.swde.*;
import pl.wodnet.shploader.provider.ShpSwdeEntityProvider;

import javax.persistence.EntityManager;

@Service
@Transactional
public class ShpSwdeService extends AbstractShpService<ShpSwdeEntity> {

    protected ShpSwdeService(ShpSwdeEntityProvider entityProvider) {
        super(entityProvider);
    }

    @Transactional
    @Override
    public void processShpEntity(EntityManager em, ShpSwdeEntity shpEntity) {
        if(shpEntity.getTableName().contains(Constants.OBREBY)) {
            ObrebEntity obrebEntity = new ObrebEntity(shpEntity);
            em.persist(obrebEntity);
        }else if (shpEntity.getTableName().contains(Constants.DZIALKI)){
            DzialkaEntity dzialkaEntity = new DzialkaEntity(shpEntity);
            em.persist(dzialkaEntity);
        }else if(shpEntity.getTableName().contains(Constants.BUDYNKI)) {
            BudynekEntity budynekEntity = new BudynekEntity(shpEntity);
            em.persist(budynekEntity);
        } else if (shpEntity.getTableName().contains(Constants.WLASCICIELE)) {
            WlascicielEntity wlascicielEntity = new WlascicielEntity(shpEntity);
            em.persist(wlascicielEntity);
        } else if(shpEntity.getTableName().contains(Constants.UZYTKI)){
            UzytekEntity uzytekEntity = new UzytekEntity(shpEntity);
            em.persist(uzytekEntity);
        }
    }
}
