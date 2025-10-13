package pl.wodnet.shploader.service.classification;

import lombok.Getter;

@Getter
public class KodResult {
    private final String kod;
    private final KodProvider provider;
    private final G7OpisProvider g7OpisProvider;

    public KodResult(String kod, KodProvider provider, G7OpisProvider g7OpisProvider) {
        this.kod = kod;
        this.provider = provider;
        this.g7OpisProvider = g7OpisProvider;
    }
}
