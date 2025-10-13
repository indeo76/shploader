package pl.wodnet.shploader.migration;

import lombok.Getter;

@Getter
public class Migration {
    private final String sqlFile;
    private final String separator;
    private final String description;

    public Migration(String description, String sqlFile, String separator) {
        this.sqlFile = sqlFile;
        this.separator = separator;
        this.description = description;
    }
}
