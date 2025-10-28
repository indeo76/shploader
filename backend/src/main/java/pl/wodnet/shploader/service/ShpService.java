package pl.wodnet.shploader.service;

import org.geolatte.geom.GeometryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.entity.gesut7.*;
import pl.wodnet.shploader.entity.sytuacja.SytuacjaEntity;
import pl.wodnet.shploader.exception.GeometryMismatchException;
import pl.wodnet.shploader.provider.ShpEntityProvider;
import pl.wodnet.shploader.repository.ShpRepository;
import javax.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShpService extends AbstractShpService<ShpEntity> {

    @Autowired
    ShpRepository shpRepository;

    protected ShpService(ShpEntityProvider entityProvider) {
        super(entityProvider);
    }

    @Transactional(noRollbackFor = GeometryMismatchException.class)
    @Override
    public void processShpEntity(EntityManager em, ShpEntity shpEntity) throws GeometryMismatchException {
        if(shpEntity.getTableName().contains(Constants.WOD_ARMATURA)){
            WodArmaturaEntity wodArmatura = new WodArmaturaEntity(shpEntity);
            em.persist(wodArmatura);
        }else if(shpEntity.getTableName().contains(Constants.WOD_SIECI)){
            WodSieciEntity wodSieci = new WodSieciEntity(shpEntity);
            em.persist(wodSieci);
        }else if(shpEntity.getTableName().contains(Constants.WOD_OBIEKTY)) {
            WodObiektyEntity wodObiekty = new WodObiektyEntity(shpEntity);
            em.persist(wodObiekty);
        }else if(shpEntity.getTableName().contains(Constants.KAN_ARMATURA)){
            KanArmaturaEntity kanArmatura = new KanArmaturaEntity(shpEntity);
            if(shpEntity.getFeatureGeomType().contains("Point")){
                em.persist(kanArmatura);
            } else {
                String message = String.format("Niezgodnosc geometrii obiektu plik: %s, kod: %s %s %s", shpEntity.getPlik(), shpEntity.getKod(), shpEntity.getTableName(), shpEntity.getGeom().getGeometryType());
                LOGGER.warn(message);
                throw new GeometryMismatchException(message);
            }
        }else if(shpEntity.getTableName().contains(Constants.KAN_SIECI)){
            KanSieciEntity kanSieci = new KanSieciEntity(shpEntity);
            em.persist(kanSieci);
        }else if(shpEntity.getTableName().contains(Constants.KAN_OBIEKTY)) {
            KanObiektyEntity kanObiekty = new KanObiektyEntity(shpEntity);
            em.persist(kanObiekty);
        }else if(shpEntity.getTableName().contains(Constants.ENG_ARMATURA)){
            EngArmaturaEntity engArmatura = new EngArmaturaEntity(shpEntity);
            em.persist(engArmatura);
        }else if(shpEntity.getTableName().contains(Constants.ENG_SIECI)) {
            EngSieciEntity engSieci = new EngSieciEntity(shpEntity);
            em.persist(engSieci);
        }else if(shpEntity.getTableName().contains(Constants.ENG_OBIEKTY)){
            EngObiektyEntity engObiekty = new EngObiektyEntity(shpEntity);
            em.persist(engObiekty);
        }else if(shpEntity.getTableName().contains(Constants.GAZ_SIECI)){
            GazSieciEntity gazSieci = new GazSieciEntity(shpEntity);
            em.persist(gazSieci);
        }else if(shpEntity.getTableName().contains(Constants.GAZ_ARMATURA)) {
            GazArmaturaEntity gazArmatura = new GazArmaturaEntity(shpEntity);
            em.persist(gazArmatura);
        }else if(shpEntity.getTableName().contains(Constants.GAZ_OBIEKTY)) {
            GazObiektyEntity gazObiekty = new GazObiektyEntity(shpEntity);
            em.persist(gazObiekty);
        }else if(shpEntity.getTableName().contains(Constants.TEL_SIECI)) {
            TelSieciEntity telSieci = new TelSieciEntity(shpEntity);
            em.persist(telSieci);
        }else if(shpEntity.getTableName().contains(Constants.TEL_ARMATURA)) {
            TelArmaturaEntity telArmatura = new TelArmaturaEntity(shpEntity);
            if(shpEntity.getFeatureGeomType().contains("Point")){
                em.persist(telArmatura);
            } else {
                String message = String.format("Niezgodnosc geometrii obiektu plik: %s, kod: %s %s %s", shpEntity.getPlik(), shpEntity.getKod(), shpEntity.getTableName(), shpEntity.getGeom().getGeometryType());
                LOGGER.warn(message);
                throw new GeometryMismatchException(message);
            }
        }else if(shpEntity.getTableName().contains(Constants.TEL_OBIEKTY)) {
            TelObiektyEntity telObiekty = new TelObiektyEntity(shpEntity);
            em.persist(telObiekty);
        }else if(shpEntity.getTableName().contains(Constants.CO_ARMATURA)) {
            CoArmaturaEntity coArmaturaEntity = new CoArmaturaEntity(shpEntity);
            em.persist(coArmaturaEntity);
        }else if(shpEntity.getTableName().contains(Constants.CO_SIECI)) {
            CoSieciEntity coSieciEntity = new CoSieciEntity(shpEntity);
            em.persist(coSieciEntity);
        }else if(shpEntity.getTableName().contains(Constants.CO_OBIEKTY)) {
            CoObiektyEntity coObiektyEntity = new CoObiektyEntity(shpEntity);
            em.persist(coObiektyEntity);
        }else if(shpEntity.getTableName().contains(Constants.SPC_ARMATURA)) {
            SpcArmaturaEntity spcArmaturaEntity = new SpcArmaturaEntity(shpEntity);
            em.persist(spcArmaturaEntity);
        }else if(shpEntity.getTableName().contains(Constants.SPC_SIECI)) {
            SpcSieciEntity spcSieciEntity = new SpcSieciEntity(shpEntity);
            em.persist(spcSieciEntity);
        }else if(shpEntity.getTableName().contains(Constants.SPC_OBIEKTY)){
            SpcObiektyEntity spcObiektyEntity = new SpcObiektyEntity(shpEntity);
            em.persist(spcObiektyEntity);
        } else if (shpEntity.getTableName().contains(Constants.INNE_ARMATURA)) {
            InneArmaturaEntity entity = new InneArmaturaEntity(shpEntity);
            em.persist(entity);
        } else if (shpEntity.getTableName().contains(Constants.INNE_SIECI)) {
            InneSieciEntity entity = new InneSieciEntity(shpEntity);
            if(entity.hasValidGeom(shpEntity)){
                em.persist(entity);
            } else {
                String message = String.format("Niezgodnosc geometrii obiektu plik: %s, kod: %s %s %s", shpEntity.getPlik(), shpEntity.getKod(), shpEntity.getTableName(), shpEntity.getGeom().getGeometryType());
                LOGGER.warn(message);
                throw new GeometryMismatchException(message);
            }
        } else if (shpEntity.getTableName().contains(Constants.INNE_OBIEKTY)) {
            InneObiektyEntity entity = new InneObiektyEntity(shpEntity);
            if(entity.hasValidGeom(shpEntity)){
                em.persist(entity);
            } else {
                String message = String.format("Niezgodnosc geometrii obiektu plik: %s, kod: %s %s %s", shpEntity.getPlik(), shpEntity.getKod(), shpEntity.getTableName(), shpEntity.getGeom().getGeometryType());
                LOGGER.warn(message);
                throw new GeometryMismatchException(message);            }
        }
    }

}
