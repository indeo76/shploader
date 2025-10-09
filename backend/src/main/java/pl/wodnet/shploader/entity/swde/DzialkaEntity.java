package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.Constants;
import pl.wodnet.shploader.entity.ShpEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = Constants.DZIALKI, schema = "swde")
@NoArgsConstructor
public class DzialkaEntity extends SWDEBaseEntity {
    private String obreb;
    private String numer;

    public DzialkaEntity(ShpEntity shpEntity) {
        super(shpEntity);
        this.obreb = shpEntity.getOBREB();
        this.numer = resolveNumerDzialki(shpEntity.getIDDZIALKI());
    }

    /**
     *
     * @param terytAndDzialka
     * @return
     * wyciaga nr dzialki z ciagu z terytem np: 303005_5.0343.49/1
     */
    private String resolveNumerDzialki(String terytAndDzialka){
        if(terytAndDzialka != null && terytAndDzialka.trim().length()>0){
            int lastDot = terytAndDzialka.lastIndexOf('.');
            String lastPart = terytAndDzialka.substring(lastDot + 1);
            return lastPart;
        } else {
            return null;
        }
    }
}
