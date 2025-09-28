package pl.wodnet.shploader.shpanalizer;

import lombok.Getter;
import lombok.Setter;
import pl.wodnet.shploader.dto.GeoinfoKodyDTO;

import java.util.List;

@Getter
@Setter
public class ShpReportRow {
    private String fileName;
    private String kodNowy;
    private String DKP_N;
    private String DKP_D;
    private String GNAME;
    private String GVALUE;
    private String kodStary;
    private Integer XCODE_C;
    private String XCODE_N;
    private String XCODE_D;
    private String rodzajGeometrii;
    private GeoinfoKodyDTO kodGeoinfo;
    private Integer count = 0;
    private List<String> properties;
}
