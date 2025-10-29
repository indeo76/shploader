package pl.wodnet.shploader.entity.gesut7;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.enums.GNAME;

import javax.persistence.MappedSuperclass;
import java.util.Map;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
public abstract class SieciBase extends GesutBaseEntity{
    private String funkcja;
    private String funkcja_n;
    private String material;
    //private String dzialka;
    private Double srednic; //todo uwaga jaka jednostka!
    private Double srednic_ww; //todo uwaga jaka jednostka!
    private Double srednic_zw; //todo uwaga jaka jednostka!
    private Double dlugosc;
    private String izolacja;
    private String przebieg;
    //private String stan;

    public SieciBase(ShpEntity shp) {
        super(shp);
        funkcja = shp.getFNP_N();
        funkcja_n = shp.getFNP_D();
        material = shp.getMAT_N();
        srednic = getSrednica(shp.getSSN());
        srednic_ww = shp.getSSW();
        srednic_zw = shp.getSSZ();
        dlugosc = shp.getXLENGTH();
        izolacja = shp.getOBD_N();
        przebieg = shp.getPRZ_N();
        this.assignGNAMEFields(shp);
    }

    @Override
    protected void assignGNAMEFields(ShpEntity shp) {
        super.assignGNAMEFields(shp);

        Map<String, String> mapa = shp.extractGNAMEList();
        if(mapa.containsKey(GNAME.MATERIAl.getLabel())){
            this.material = mapa.get(GNAME.MATERIAl.getLabel());
        }
        if(mapa.containsKey(GNAME.PRZEBIEG.getLabel())){
            this.przebieg = mapa.get(GNAME.PRZEBIEG.getLabel());
        }
        if(mapa.containsKey(GNAME.SREDNICA_URZADZENIA.getLabel())){
            try{
                this.srednic = getSrednica(Double.parseDouble(mapa.get(GNAME.SREDNICA_URZADZENIA.getLabel())));
            } catch (Exception ex){
                System.out.println(ex.getMessage());
            }
        }
    }

    private Double getSrednica(Double srednica){
        if(srednica != null){
            return 1000 * srednica;
        } else {
            return srednica;
        }
    }
}
