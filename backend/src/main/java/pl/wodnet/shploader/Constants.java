package pl.wodnet.shploader;

public class Constants {

    public static String SHP_GESUT_DIRECTORY = System.getenv()
            .getOrDefault("SHP_GESUT_DIRECTORY", "/volume/gesut");

    public static String SHP_SWDE_DIRECTORY = System.getenv()
            .getOrDefault("SHP_SWDE_DIRECTORY", "/volume/swde");

    public static String SHP_SYTUACJA_DIRECTORY = System.getenv()
            .getOrDefault("SHP_SYTUACJA_DIRECTORY", "/volume/swde");

    public static String GEOINFO_KODY_FILE = System.getenv()
            .getOrDefault("GEOINFO_KODY_FILE", "/volume/conf/geoinfo_kody.json");

    public static final String WOD_SIECI = "wod_sieci";
    public static final String WOD_ARMATURA = "wod_armatura";
    public static final String WOD_OBIEKTY = "wod_obiekty";

    public static final String KAN_SIECI = "kan_sieci";
    public static final String KAN_ARMATURA = "kan_armatura";
    public static final String KAN_OBIEKTY = "kan_obiekty";

    public static final String ENG_SIECI = "eng_sieci";
    public static final String ENG_ARMATURA = "eng_armatura";
    public static final String ENG_OBIEKTY = "eng_obiekty";

    public static final String GAZ_SIECI = "gaz_sieci";
    public static final String GAZ_ARMATURA = "gaz_armatura";
    public static final String GAZ_OBIEKTY = "gaz_obiekty";

    public static final String TEL_SIECI = "tel_sieci";
    public static final String TEL_ARMATURA = "tel_armatura";
    public static final String TEL_OBIEKTY = "tel_obiekty";

    public static final String CO_ARMATURA = "co_armatura";
    public static final String CO_SIECI = "co_sieci";
    public static final String CO_OBIEKTY = "co_obiekty";

    public static final String SPC_ARMATURA = "spc_armatura";
    public static final String SPC_SIECI = "spc_sieci";
    public static final String SPC_OBIEKTY = "spc_obiekty";

    public static final String INNE_ARMATURA = "inne_armatura";
    public static final String INNE_SIECI = "inne_sieci";
    public static final String INNE_OBIEKTY = "inne_obiekty";

    public static final String SYTUACJA = "sytuacja";

    public static final String OBREBY = "obreby";
    public static final String BUDYNKI = "budynki";
    public static final String DZIALKI = "dzialki";
    public static final String WLASCICIELE = "wlasciciele";
    public static final String UZYTKI = "uzytki";
    public static final String ADRESY = "adresy";

    public static final int RZEDNA_SCALE = 2;

}
