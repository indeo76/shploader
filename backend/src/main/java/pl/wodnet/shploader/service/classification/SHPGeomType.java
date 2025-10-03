package pl.wodnet.shploader.service.classification;

public enum SHPGeomType {
    POINT("P"),
    LINEAR("L"),
    SURFACE("S"),
    NIEZNANY("NIEZNANY");

    private final String label;

    SHPGeomType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static SHPGeomType fromLabel(String label) {
        if (label == null) {
            return NIEZNANY;
        }

        for (SHPGeomType type : values()) {
            if (type.label.equalsIgnoreCase(label)) {
                return type;
            }
        }

        return NIEZNANY;
    }

    public static SHPGeomType safeValueOf(String name) {
        try {
            return SHPGeomType.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return NIEZNANY;
        }
    }
}

