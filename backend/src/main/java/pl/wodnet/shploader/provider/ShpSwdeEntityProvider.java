package pl.wodnet.shploader.provider;

import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.ShpSwdeEntity;
import pl.wodnet.shploader.service.AbstractEntityProvider;

@Service
public class ShpSwdeEntityProvider extends AbstractEntityProvider<ShpSwdeEntity> {
    @Override
    public ShpSwdeEntity getEntity() {
        return new ShpSwdeEntity();
    }
}
