package pl.wodnet.shploader.provider;

import org.springframework.stereotype.Service;
import pl.wodnet.shploader.entity.ShpSytuacjaEntity;
import pl.wodnet.shploader.service.AbstractEntityProvider;

@Service
public class ShpSytuacjaEntityProvider extends AbstractEntityProvider<ShpSytuacjaEntity>  {
    @Override
    public ShpSytuacjaEntity getEntity() {
        return new ShpSytuacjaEntity();
    }
}
