package pl.wodnet.shploader.entity.pwik;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.pwik.base.PwikArmaturaEntity;
import pl.wodnet.shploader.entity.pwik.base.PwikBaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "kan_armatura", schema = "pwik")
@NoArgsConstructor
public class PwikKanArmaturaEntity extends PwikArmaturaEntity {


}
