package pl.wodnet.shploader.dto;

import lombok.Getter;
import pl.wodnet.shploader.enums.FeatureError;

@Getter
public class ImportErrorDTO {
    private final String key;
    private final String value;
    private final FeatureError error;

    public ImportErrorDTO(String key, String value, FeatureError error) {
        this.key = key;
        this.value = value;
        this.error = error;
    }
}
