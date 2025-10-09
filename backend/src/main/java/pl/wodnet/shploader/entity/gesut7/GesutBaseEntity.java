package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import pl.wodnet.shploader.entity.ShpEntity;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class GesutBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;

    @Column(name = "shp_entity_id")
    private Long shpEntityId;

    @Column(name = "the_geom", columnDefinition = "GEOMETRY")
    private Geometry geom;

    private String zrodlo;
    private String ulica;
    private String miejscowos;
    private String nr_modgik;
    private String obreb;
    private String identyfikator;
    private Integer	version;
    private String kerg;
    private String adres;
    private String dzialka; // todo
    private String creation_date;
    private String modification_date;
    private String g7;
    private Integer	status;
    private String material;
    private String stan;
    private String g7_opis;

    public GesutBaseEntity(ShpEntity shp) {
        shpEntityId = shp.getGid();
        geom = shp.getGeom();
        zrodlo = shp.getMPD_N();
        ulica = shp.getULI_N();
        miejscowos = shp.getMJS_N();
        nr_modgik = shp.getXNUMBER();
        obreb = shp.getXS_OBREB_D();
        identyfikator = shp.getXIDENTIFI1();
        version = shp.getXVERSION_1();
        kerg = shp.getKRG_N();
        adres = shp.getNPD();
        creation_date = shp.getXCREATION1();
        modification_date = shp.getXMODIFICA1();
        g7 = shp.getTyp(); //shp.getXCODE_N(); //todo - tutaj bierze nowy kod!!!!
        status = shp.getXSTATUS();
        material = shp.getMAT_N();
        stan = shp.getSTP_N();
        dzialka = shp.getNDZ();
        g7_opis = shp.getXCODE_D(); //todo - tutaj bierze nowy kod!!!!
        this.assignGNAMEFields(shp);
    }

    /**
     * Przypisuje odpowiednie wartości GNAME/GVALUE do docelowych pól
     */
    protected void assignGNAMEFields(ShpEntity shp) {

    }
}
