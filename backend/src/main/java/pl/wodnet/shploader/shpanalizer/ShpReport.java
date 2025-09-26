package pl.wodnet.shploader.shpanalizer;

import lombok.Getter;

import java.util.List;

@Getter
public class ShpReport {
    private final List<String> globalProperties;
    private final List<ShpReportRow> rows;

    public ShpReport(List<String> globalProperties, List<ShpReportRow> rows) {
        this.globalProperties = globalProperties;
        this.rows = rows;
    }
}
