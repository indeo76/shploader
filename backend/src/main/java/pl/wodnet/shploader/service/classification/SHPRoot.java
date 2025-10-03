package pl.wodnet.shploader.service.classification;

public enum SHPRoot {
    GESUT("GU"),
    NIEZNANY("NIEZNANY");

    private final String label;

    SHPRoot(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static SHPRoot fromLabel(String label) {
        if (label == null) {
            return NIEZNANY;
        }

        for (SHPRoot root : values()) {
            if (root.label.equalsIgnoreCase(label)) {
                return root;
            }
        }

        return NIEZNANY;
    }

    public static SHPRoot safeValueOf(String name) {
        try {
            return SHPRoot.valueOf(name.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            return NIEZNANY;
        }
    }
}

