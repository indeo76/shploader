package pl.wodnet.shploader.slownik;

import org.springframework.stereotype.Service;


@Service
public class MaterialService extends AbstractSlownikService<MaterialEntity> {
    private final MaterialRepository repository;

    public MaterialService(MaterialRepository repository) {
        super(repository);
        this.repository = repository;
    }
}
