package pl.wodnet.shploader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wodnet.shploader.entity.gesut7.WodSieciEntity;

@Repository
public interface WodSieciRepository extends CrudRepository<WodSieciEntity, Long> {
}
