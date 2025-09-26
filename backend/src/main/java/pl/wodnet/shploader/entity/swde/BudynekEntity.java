package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "budynki", schema = "swde")
@NoArgsConstructor
public class BudynekEntity extends SWDEBaseEntity{
    private String g5msc;
    private String g5ulc;
    private String g5nra;
    private String g5kod;
    private String g5scn;
    private Double g5peu;
    private Double g5pew;
    private String g5lkp;
    private String g5lkn;
    private String g5rbb;
    private Double g5wrt;
    private String g5fuz;
    private String g5dww;
    private String g5idd;
    private String g5rzn;
    private String g5dwr;
    private Long id_bud;
    private String g5idb;
    private String g5radr;
    private String g5rjdr;
    private String nazwa_wlas;
    private String gvibp;
    private String gvfsb;
    private String gvkst;
    private String status;

    public BudynekEntity(ShpEntity shpEntity) {
        super(shpEntity);
        g5msc = shpEntity.getG5MSC();
        g5ulc = shpEntity.getG5ULC();
        g5nra = shpEntity.getG5NRA();
        g5kod = shpEntity.getG5KOD();
        g5scn = shpEntity.getG5SCN(); //?
        g5peu = shpEntity.getG5PEU();
        g5lkp = shpEntity.getG5LKP();
        g5lkn = shpEntity.getG5LKN();
        g5rbb = shpEntity.getG5RBB();
        g5wrt = shpEntity.getG5WRT();
        g5fuz = shpEntity.getG5FUZ();
        g5dww = shpEntity.getG5DWW();
        g5pew = shpEntity.getG5PEW();
        g5idd = shpEntity.getG5IDD();
        g5rzn = shpEntity.getG5RZN();
        g5dwr = shpEntity.getG5DWR();
        id_bud = shpEntity.getID_BUD();
        g5idb = shpEntity.getG5IDB();
        g5radr = shpEntity.getG5RADR();
        g5rjdr = shpEntity.getG5RJDR();
        nazwa_wlas = shpEntity.getNAZWA_WLAS();
        gvibp = shpEntity.getGVIBP();
        gvfsb = shpEntity.getGVFSB();
        gvkst = shpEntity.getGVKST();
        status = shpEntity.getSTATUS();
    }
}
