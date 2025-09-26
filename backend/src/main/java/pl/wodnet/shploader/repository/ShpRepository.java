package pl.wodnet.shploader.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.wodnet.shploader.entity.ShpEntity;

@Repository
public interface ShpRepository extends CrudRepository<ShpEntity, Long> {

}
