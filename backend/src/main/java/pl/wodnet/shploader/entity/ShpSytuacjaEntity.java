package pl.wodnet.shploader.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "shp_sytuacja", schema = "shp")
public class ShpSytuacjaEntity extends ShpBaseEntity {
}
