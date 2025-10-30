package pl.wodnet.shploader.geoinfokody;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;

@Repository
public interface GeoinfoKodyRepository extends JpaRepository<GeoinfoKodyEntity,Integer> {
}
