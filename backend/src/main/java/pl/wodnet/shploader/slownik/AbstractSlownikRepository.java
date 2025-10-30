package pl.wodnet.shploader.slownik;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface AbstractSlownikRepository<Entity extends SlownikBaseEntity> extends JpaRepository<Entity,String> {
    Optional<Entity> findByOpis(String opis);
}
