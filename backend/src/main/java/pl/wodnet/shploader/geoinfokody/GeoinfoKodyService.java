package pl.wodnet.shploader.geoinfokody;

import org.springframework.stereotype.Service;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeoinfoKodyService {
    private final GeoinfoKodyRepository geoinfoKodyRepository;

    public GeoinfoKodyService(GeoinfoKodyRepository geoinfoKodyRepository) {
        this.geoinfoKodyRepository = geoinfoKodyRepository;
    }


    public List<GeoinfoKodyDTO> getFromDatabase() {
        List<GeoinfoKodyDTO> geoinfoKodyDTOS = new ArrayList<>();
        geoinfoKodyRepository.findAll().forEach(geoinfoKodyEntity -> {
            GeoinfoKodyDTO dto = new GeoinfoKodyDTO();
            dto.kod_mnemoniczny = geoinfoKodyEntity.getKodMnemoniczny();
            dto.opis_kodu = geoinfoKodyEntity.getOpis_kodu();
            dto.rodzaj_geometrii = geoinfoKodyEntity.getRodzaj_geometrii();
            dto.tabela =  geoinfoKodyEntity.getTabela();
            geoinfoKodyDTOS.add(dto);
        });
        return geoinfoKodyDTOS;
    }
}
