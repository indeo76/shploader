package pl.wodnet.shploader.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoinfoKodyDTO{

    public String kod_mnemoniczny;

    public String opis_kodu;

    public Integer rodzaj_geometrii;

    public String tabela;


}
