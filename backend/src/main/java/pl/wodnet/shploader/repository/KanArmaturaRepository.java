package pl.wodnet.shploader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wodnet.shploader.entity.gesut7.KanArmaturaEntity;

@Repository
public interface KanArmaturaRepository extends CrudRepository<KanArmaturaEntity, Long> {
}
