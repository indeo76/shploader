package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "sytuacja", schema = "sytuacja7")
@NoArgsConstructor
public class SytuacjaEntity extends ObiektyBase{
    private String xcode_n;
    private String xcode_d;
    private String mjs_n; //miejscowosc
    private String uli_n; //ulica

    private String poz_d;
    private String poz_n;
    private String rna_d;
    private String rna_n;
    private String grko_d;
    private String grko_n;
    private String txt;
    private String bud;

    public SytuacjaEntity(ShpEntity shp) {
        super(shp);
        xcode_n = shp.getXCODE_N();
        xcode_d = shp.getXCODE_D();
        mjs_n = shp.getMJS_N();
        uli_n = shp.getULI_N();
        poz_d = shp.getPOZ_D();
        poz_n = shp.getPOZ_N();
        rna_d = shp.getRNA_D();
        rna_n = shp.getRNA_N();
        grko_d = shp.getGRKO_D();
        grko_n = shp.getGRKO_N();
        txt = shp.getTXT();
        bud = shp.getBUD();
    }
}
