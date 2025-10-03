package pl.wodnet.shploader.service.classification;

public enum SHPBranza {
    WOD("W"),
    KAN("K"),
    EN("E"),
    TEL("T"),
    CO("C"),
    GAZ("G"),
    I("I"),
    P("P"),
    S("S"),
    X("X"),
    L("L"),
    Z("Z"),
    NIEZNANY("NIEZNANY");

    private final String label;

    SHPBranza(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static SHPBranza fromLabel(String label) {
        if (label == null) {
            return NIEZNANY;
        }

        for (SHPBranza b : values()) {
            if (b.label.equalsIgnoreCase(label)) {
                return b;
            }
        }

        return NIEZNANY;
    }

    public static SHPBranza safeValueOf(String name) {
        try {
            return SHPBranza.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return NIEZNANY;
        }
    }
}

