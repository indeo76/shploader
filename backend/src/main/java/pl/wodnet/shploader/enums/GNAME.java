package pl.wodnet.shploader.enums;

public enum GNAME {
    MATERIAl("Materiał"),
    PRZEBIEG("Przebieg"),
    RODZAJ_PRZEWODU("Rodzaj przewodu"),
    SREDNICA_URZADZENIA("Średnica urządzenia");

    private final String label;

    GNAME(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

