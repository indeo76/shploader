package pl.wodnet.shploader.service.classification;

import lombok.Getter;

@Getter
public class TargetTableResult {
    private final String tableName;
    private final TargetTableProvider tableNameProvider;

    public TargetTableResult(String tableName, TargetTableProvider tableNameProvider) {
        this.tableName = tableName;
        this.tableNameProvider = tableNameProvider;
    }
}
