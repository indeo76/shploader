package pl.wodnet.shploader.slownik;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "material", schema = "slowniki")
public class MaterialEntity extends SlownikBaseEntity{
}
