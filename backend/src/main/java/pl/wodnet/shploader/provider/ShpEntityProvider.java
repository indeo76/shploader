package pl.wodnet.shploader.provider;

import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.service.AbstractEntityProvider;

@Service
public class ShpEntityProvider extends AbstractEntityProvider<ShpEntity> {
    @Override
    public ShpEntity getEntity() {
        return new ShpEntity();
    }
}
