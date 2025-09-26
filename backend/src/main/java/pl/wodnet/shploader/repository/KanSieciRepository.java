package pl.wodnet.shploader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wodnet.shploader.entity.gesut7.KanSieciEntity;

@Repository
public interface KanSieciRepository extends CrudRepository<KanSieciEntity, Long> {

}
