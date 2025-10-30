package pl.wodnet.shploader.slownik;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaterialRepository extends AbstractSlownikRepository<MaterialEntity> {
}
