package pl.wodnet.shploader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wodnet.shploader.entity.gesut7.KanObiektyEntity;

@Repository
public interface KanObiektyRepository extends CrudRepository<KanObiektyEntity, Long> {
}
