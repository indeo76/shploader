package pl.wodnet.shploader.entity.pwik.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class PwikSieciEntity extends PwikBaseEntity{
    @Column(columnDefinition = "TEXT")
    private String funkcja;

    @Column(columnDefinition = "TEXT")
    private String izolacja;
    @Column(columnDefinition = "TEXT")
    private String przebieg;
    Float srednic_ww;
    Float srednic_zw;
    @Column(columnDefinition = "TEXT")
    private String cisnienie;
    private Float dlugosc;

}
