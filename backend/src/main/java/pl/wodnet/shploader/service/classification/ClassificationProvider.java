package pl.wodnet.shploader.service.classification;

import org.springframework.stereotype.Service;

@Service
public class ClassificationProvider implements IClassificationProvider {
    @Override
    public SHPRoot getRoot(String kod) {
        return SHPRoot.fromLabel(kod.substring(0, 2));
    }

    @Override
    public SHPGeomType getGeomType(String kod) {
        return SHPGeomType.fromLabel(kod.substring(2, 3));
    }

    @Override
    public SHPBranza getBranza(String kod) {
        return SHPBranza.fromLabel(kod.substring(3, 4));
    }

    @Override
    public SHPObjectType getObiektType(String kod) {
        return SHPObjectType.fromLabel(kod.substring(3,6));
    }
}
