package pl.wodnet.shploader.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ImportResultDTO {
    private final String fileName;
    private final Integer totalCount;
    private final Integer savedCount;
    private final List<ImportErrorDTO> errors;

    public ImportResultDTO(String fileName, Integer totalCount, Integer savedCount, List<ImportErrorDTO> errors) {
        this.fileName = fileName;
        this.totalCount = totalCount;
        this.savedCount = savedCount;
        this.errors = errors;
    }
}
