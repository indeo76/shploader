package pl.wodnet.shploader.service;

import org.springframework.stereotype.Service;

@Service
public abstract class AbstractEntityProvider<Entity>{
    public abstract Entity getEntity();
}
