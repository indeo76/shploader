package pl.wodnet.shploader.slownik;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public abstract class AbstractSlownikService<Entity extends SlownikBaseEntity> {
    private final AbstractSlownikRepository<Entity> repository;

    protected AbstractSlownikService(AbstractSlownikRepository<Entity> repository) {
        this.repository = repository;
    }


    public Optional<Entity> findByKod(String kod){
        return repository.findById(kod);
    }

    public List<Entity> findAll(){
        return repository.findAll();
    }

    public Optional<Entity> findByOpis(String opis){
        return repository.findByOpis(opis);
    }

    public String getKodByOpis(String opis){
        Optional<Entity> entity = findByOpis(opis);
        if(entity.isPresent()){
            return entity.get().getKod();
        } else {
            return null;
        }
    }
}
