package pl.wodnet.shploader.shpanalizer;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shpanalizer")
public class ShpAnalizerController {
    private final ShpAnalizerService service;
    protected final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(getClass());

    public ShpAnalizerController(ShpAnalizerService service) {
        this.service = service;
    }

    @GetMapping(path = "/analizuj")
    public ShpReport analizuj() throws IOException {
        String description = "1. wskazanie katalogu danych" +
                "2. wczytanie listy plików" +
                "3. dla każdego pliku i obiektu odczytać kody i rodzaj geometrii" +
                "4. zbudować statystykę";
        String filePath = "D:\\rozne\\2025-wrzesnia\\SHAPEGESUT\\SHAPE GESUT";
        List<ShpReportRow> rows = service.analizuj(filePath);
        List<String> globalProperties = service.extractGlobalProperties(rows);
        ShpReport shpReport = new ShpReport(globalProperties, rows);
        LOGGER.info("Zakonczono");
        return shpReport;
    }

    @GetMapping(path = "eksportujWgStarychKodow")
    public void eksportujWgStarychKodow(){
        service.eksportujWgStarychKodow("D:\\rozne\\2025-wrzesnia\\SHAPEGESUT\\SHAPE GESUT", "D:\\rozne\\2025-wrzesnia\\SHAPEGESUT\\EXPORT_STARE_KODY");
    }

}
