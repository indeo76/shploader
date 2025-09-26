package pl.wodnet.shploader.entity.pwik.base;

import lombok.Getter;
import lombok.Setter;
import pl.wodnet.shploader.entity.pwik.base.PwikBaseEntity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class PwikObiektyEntity extends PwikBaseEntity {
    @Column(columnDefinition = "TEXT")
    String obiekt;
    @Column(columnDefinition = "TEXT")
    String przebieg;

    @Column(columnDefinition = "TEXT")
    String nazwa;
    Float srednic_ww;
    Float srednic_zw;
    Float dlugosc;
}
