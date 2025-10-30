package pl.wodnet.shploader.slownik;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class SlownikBaseEntity {

    @Id
    @Column(name = "kod")
    private String kod;

    @Column(name = "opis")
    private String opis;
}
