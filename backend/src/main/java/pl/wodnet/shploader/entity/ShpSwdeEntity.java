package pl.wodnet.shploader.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "shp_swde", schema = "shp")
public class ShpSwdeEntity extends ShpBaseEntity{

    private String LOKALNYID;
    private String IDOBREBU;
    private String NAZWAWLASN;
    private String OBREB;
    private String NR_DZ;
    private String NAZWA;
    private String ADRES;
    private String KW; //ksiega wieczysta
    private String TYP;
    private String UDZIAL;
    private String GMINA;
    private String LICZBAKONN;
    private String LICZBAKONP;
    private String OFU;

}
