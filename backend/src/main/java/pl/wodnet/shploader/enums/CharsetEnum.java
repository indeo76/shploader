package pl.wodnet.shploader.enums;

public enum CharsetEnum {
    UTF8("UTF-8"),
    WINDOWS("Windows-1250");

    private final String charsetName;

    CharsetEnum(String charsetName) {
        this.charsetName = charsetName;
    }

    public String getCharsetName() {
        return charsetName;
    }
}
