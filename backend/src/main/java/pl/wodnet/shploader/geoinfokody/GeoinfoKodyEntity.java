package pl.wodnet.shploader.geoinfokody;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "geoinfo7_kody", schema = "public")
public class GeoinfoKodyEntity {
    @Id
    private Integer id;

    @Column(name = "kod_mnemoniczny")
    private String kodMnemoniczny;

    @Column(name = "tabela")
    private String tabela;

    @Column(name = "opis_kodu")
    private String opis_kodu;

    @Column(name = "rodzaj_geometrii")
    private Integer rodzaj_geometrii;
}
