package pl.wodnet.shploader.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
@MappedSuperclass
public class ShpBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gid;
    private String plik;

    @Column(name = "plik_modification_date")
    private String plikModificationDate;

    private java.lang.String BLP_D;
    private java.lang.String BLP_N;
    private java.lang.String DEL;
    private java.lang.Double DEW;
    private java.lang.String DKP_D;
    private java.lang.String DKP_N;
    private java.lang.String DOI;
    private java.lang.String DTP;
    private java.lang.String FNP_D;
    private java.lang.String FNP_N;
    private java.lang.String GROS_D;
    private java.lang.String GROS_N;
    private java.lang.String IDB;
    private java.lang.String IDU_D;
    private java.lang.String IDU_N;
    private java.lang.String IG5;
    private java.lang.String ISW;
    private java.lang.String KPR_D;
    private java.lang.String KPR_N;
    private java.lang.String KRG_D;
    private java.lang.String KRG_N;
    private java.lang.String KSU_D;
    private java.lang.String KSU_N;
    private java.lang.String MAT_D;
    private java.lang.String MAT_N;
    private java.lang.String MJS_D;
    private java.lang.String MJS_N;
    private java.lang.String MPD_D;
    private java.lang.String MPD_N;
    private java.lang.String NPD;
    private java.lang.String NRI;
    private java.lang.String NWS;
    private java.lang.String OBD_D;
    private java.lang.String OBD_N;
    private java.lang.String OPS;
    private java.lang.String OPT_D;
    private java.lang.String OPT_N;
    private java.lang.Integer PGU;
    private java.lang.String PRE;
    private java.lang.String PRZ_D;
    private java.lang.String PRZ_N;
    private java.lang.String RDZ_D;
    private java.lang.String RDZ_N;
    private java.lang.String ROP_D;
    private java.lang.String ROP_N;
    private java.lang.String ROS_D;
    private java.lang.String ROS_N;
    private java.lang.Double RZD;
    private java.lang.Double RZG;
    private java.lang.Double SRU; //kolizja typu Double/Integer
    private java.lang.Double SSN; //srednica nominalna
    private java.lang.Double SSW; // srednica ww
    private java.lang.Double SSZ; //srednica zewn
    private String STP_N;
    private java.lang.String STO_D;
    private java.lang.String STO_N;
    private java.lang.String TST_D;
    private java.lang.String TST_N;
    private java.lang.String ULI_D;
    private java.lang.String ULI_N;
    private java.lang.String ULIOP;
    private java.lang.String WLA;
    private java.lang.Integer WPI;
    private java.lang.Integer WPO;
    private java.lang.String XACCESS_L1;
    private java.lang.String XARCH_DATE;
    private java.lang.Double XAREA;
    private java.lang.String XAUX_COOR1;
    private java.lang.Double XAUX_H;
    private java.lang.Double XAUX_X;
    private java.lang.Double XAUX_Y;
    private java.lang.Double XB;
    private java.lang.String XCHANGE;
    private java.lang.Integer XCODE_C;
    private java.lang.String XCODE_D;
    private java.lang.String XCODE_N;
    private java.lang.String XCODEBYRE1;
    private java.lang.String XCODEBYRE2;
    private java.lang.String XCODEBYLAW;
    private java.lang.String XCREATION1;
    private java.lang.Double XGEODESY_1;
    private java.lang.Double XGEODESY_2;
    private java.lang.Double XH;
    private java.lang.String XIDIIP_SR1;
    private java.lang.String XIDENTIFI1;
    private java.lang.String XIDIIP_LO1;
    private java.lang.String XIDIIP_NA1;
    private java.lang.Double XL;
    private java.lang.Double XLENGTH;
    private java.lang.String XMODIFICA1;
    private java.lang.String XNUMBER;
    private java.lang.String XREMARKS;
    private java.lang.String XS_ARKUSZ1;
    private java.lang.String XS_ARKUSZ2;
    private java.lang.String XS_JEDNOS1;
    private java.lang.String XS_JEDNOS2;
    private java.lang.String XS_OBREB_D; //XS_OBRÊB_D
    private java.lang.String XS_OBREB_N; //XS_OBRÊB_N
    private java.lang.String XS_PANSTW1; //XS_PAÑSTW1
    private java.lang.String XS_PANSTW2; //XS_PAÑSTW2
    private java.lang.String XS_POWIAT1;
    private java.lang.String XS_POWIAT2;
    private java.lang.String XS_WOJEWO1; //XS_WOJEWÓ1
    private java.lang.String XS_WOJEWO2; //XS_WOJEWÓ2
    private java.lang.String XSRC_VERS1;
    private java.lang.Integer XSTATUS;
    private java.lang.String XUSER_DES1;
    private java.lang.String XUSER_IDE1;
    private java.lang.String XUSER_NAME;
    private java.lang.Integer XVERSION_1;
    private java.lang.String NDZ; //dzialka
    private Double XX;
    private Double XY;
    private String OBK;
//    private Integer SRU; //kolizja typu Integer/Double
    private Integer LPW;
    private String ROC_D;
    private String ROC_N;

    private String GNAME; // mapa kluczy danych branżowych
    private String GVALUE;// mapa wartości danych branżowych

    //SWDE:
    private String G5NRO;
    private Double G5PEW;
    private String G5NAZ; //nazwa
    private String NAZWAWLASN; // nazwa nowa (obreby)
    private String IDDZIALKI; //nowa - id dzialki razem z terytem
    private String G5DTW;
    private String G5DTU;
    private String G5RJEW;
    private String G5MSC; //miejscowosc
    private String G5ULC; //ulica
    private String G5NRA; //nr addr
    private String G5KOD; // od pocztowy
    private String G5SCN;
    private Double G5PEU;
    private String G5LKP;
    private String G5LKN;
    private String G5RBB;
    private Double G5WRT;
    private String G5FUZ;

    private String OBREB;
    private String NUMER;
    private String G5DWW;
    private String G5IDD;
    private String G5RZN;
    private String G5DWR;
    private Long ID_BUD;
    private String G5IDB;
    private String G5RADR;
    private String G5RJDR;
    private String NAZWA_WLAS;
    private String GVIBP;
    private String GVFSB;
    private String GVKST;
    private String STATUS;

    //sytuacja
    private String POZ_D;
    private String POZ_N;
    private String RNA_D;
    private String RNA_N;
    private String GRKO_D;
    private String GRKO_N;
    private String TXT;
    private String BUD;

    /**
     * Buduje mapę key->value na podstawie pól GNAME i GVALUE
     * @return
     */
    public Map<String, String> extractGNAMEList() {
        Map<String, String> result = new LinkedHashMap<>();

        if (GNAME == null || GVALUE == null) return result;

        String[] keys = GNAME.split(",");
        String[] values = GVALUE.split(",");

        int length = Math.min(keys.length, values.length);

        for (int i = 0; i < length; i++) {
            result.put(keys[i].trim(), values[i].trim());
        }

        return result;
    }

    public void setPlikModificationDate(File file){
        long lastModified = file.lastModified();
        Date date = new Date(lastModified);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.plikModificationDate = sdf.format(date);
    }


}
