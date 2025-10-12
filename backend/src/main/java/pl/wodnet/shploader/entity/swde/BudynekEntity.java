package pl.wodnet.shploader.entity.swde;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.wodnet.shploader.entity.ShpEntity;
import pl.wodnet.shploader.entity.ShpSwdeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "budynki", schema = "swde")
@NoArgsConstructor
public class BudynekEntity extends SWDEBaseEntity{
    private String miejscowosc;
    private String ulica;
    private String nr;
    private String adres;

    @Column(name = "kod_pocztowy")
    private String kodPocztowy;

    @Column(name = "rok_budowy")
    private String rokBudowy;

    @Column(name = "liczba_kondygnacji_n")
    private Integer liczbaKondygnacjiNadziemnych;

    @Column(name = "liczba_kondygnacji_p")
    private Integer liczbaKondygnacjiPodziemnych;


    public BudynekEntity(ShpSwdeEntity shpEntity) {
        super(shpEntity);

        liczbaKondygnacjiNadziemnych = this.parseInteger(shpEntity.getLICZBAKONN());
        liczbaKondygnacjiPodziemnych = this.parseInteger(shpEntity.getLICZBAKONP());
    }

    private Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            // Możesz zalogować błąd lub zwrócić null
            return null;
        }
    }

}
