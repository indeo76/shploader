package pl.wodnet.shploader.entity.pwik.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class PwikArmaturaEntity extends PwikBaseEntity{

    @Column(columnDefinition = "TEXT")
    private String g7_kan;
    @Column(columnDefinition = "TEXT")
    private String g7_kan_opis;
    @Column(columnDefinition = "TEXT")
    private String k1_kan;

    @Column(columnDefinition = "TEXT")
    private String armatura;

    private Float rz_wejscia;
    private Float rz_wyjscia;

    private Float srednic_wl;
    private Float srednic_wy;
}
