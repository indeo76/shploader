package pl.wodnet.shploader.slownik;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.gesut7.SieciBase;

@Service
public class SlownikResolver {
    private final MaterialService materialService;
    protected final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    public SlownikResolver(MaterialService materialService) {
        this.materialService = materialService;
    }

    public void update(SieciBase entity){
        try {
            entity.setMaterial(materialService.getKodByOpis(entity.getMaterialDescription()));
        } catch (Exception ex) {
            LOGGER.error("SlownikResolver update error", ex);
        }
    }
}
