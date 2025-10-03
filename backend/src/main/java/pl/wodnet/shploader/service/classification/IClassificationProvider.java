package pl.wodnet.shploader.service.classification;

import pl.wodnet.shploader.entity.ShpEntity;

public interface IClassificationProvider {

    /**
     Jeśli jest stary kod - po staremu
     Jeśli nie ma starego kodu a jest konfiguracja dla nowego użyj nowego
     Jeśli nie ma starego i nie ma konfiguracji dla nowego a jest kod gesut 2013 próbuj sklasyfikować wg rodzaju geometrii i kodu gesut 2013
     */
    SHPRoot getRoot(String kod);
    SHPGeomType getGeomType(String kod);
    SHPBranza getBranza(String kod);
    SHPObjectType getObiektType(String kod);

}
