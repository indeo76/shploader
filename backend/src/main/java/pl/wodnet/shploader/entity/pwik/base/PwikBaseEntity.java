package pl.wodnet.shploader.entity.pwik.base;

import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@MappedSuperclass
public abstract class PwikBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;

    @Column(name = "the_geom")
    private Geometry geom;

    @Column(columnDefinition = "TEXT")
    private String miejscowos;

    @Column(columnDefinition = "TEXT")
    private String ulica;

    @Column(columnDefinition = "TEXT")
    private String no_ulica;

    @Column(columnDefinition = "TEXT")
    private String dzialka;

    @Column(columnDefinition = "TEXT")
    private String adres;

    @Column(columnDefinition = "TEXT")
    private String obreb;

    @Column(columnDefinition = "TEXT")
    private String wlasciciel;

    private Integer rok;
    @Column(columnDefinition = "TEXT")
    private String zrodlo;

    @Column(columnDefinition = "TEXT")
    private String stan;

    @Column(columnDefinition = "TEXT")
    private String typ;

    @Column(columnDefinition = "TEXT")
    private String kerg;

    private Float rz_terenu;
    private Float rz_dna;

    private Float posadowien;

    @Column(columnDefinition = "TEXT")
    private String material;

    private Integer srednic;

    @Column(columnDefinition = "TEXT")
    private String uwagi;

    @Column(columnDefinition = "TEXT")
    private String szkic;

    @Column(columnDefinition = "TEXT")
    private String mapa;

    @Column(columnDefinition = "TEXT")
    private String projekt;

    @Column(columnDefinition = "TEXT")
    private String film;
    @Column(columnDefinition = "TEXT")
    private String zdjecie;
    @Column(columnDefinition = "TEXT")
    private String plik;

    @Column(columnDefinition = "TEXT")
    private String zdarzenie;
    @Column(columnDefinition = "TEXT")
    private String zdarzenie_opis;

    @Column(columnDefinition = "TEXT")
    private String opis;

    @Column(columnDefinition = "TEXT")
    private String tekst;
    @Column(columnDefinition = "TEXT")
    private String dodal;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDate dodal_dnia;
    @Column(columnDefinition = "TEXT")
    private String zmienil;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDate zmienil_dnia;
    @Column(columnDefinition = "TEXT")
    private String nr_inwentarzowy;
    @Column(columnDefinition = "TEXT")
    private String nr_sr_trwalego;

    @Column(columnDefinition = "TEXT")
    private String identyfikator;
    @Column(columnDefinition = "TEXT")
    private String creation_date;
    @Column(columnDefinition = "TEXT")
    private String modification_date;
    private Integer version;
    private Integer status;
    private Integer id;
    private LocalDate odbior;
}
