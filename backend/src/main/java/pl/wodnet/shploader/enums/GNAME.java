package pl.wodnet.shploader.enums;

public enum GNAME {
    RODZAJ_PRZEWODU("Rodzaj przewodu");

    private final String label;

    GNAME(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

