package pl.wodnet.shploader.service.classification;

import lombok.Getter;

@Getter
public class KodResult {
    private final String kod;
    private final KodProvider provider;

    public KodResult(String kod, KodProvider provider) {
        this.kod = kod;
        this.provider = provider;
    }
}
