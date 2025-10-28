package pl.wodnet.shploader.tools;

import com.linuxense.javadbf.DBFReader;
import org.mozilla.universalchardet.UniversalDetector;
import pl.wodnet.shploader.enums.CharsetEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DbfEncodingDetector {


    public static CharsetEnum detectEncodingOld(File dbfFile) throws IOException {
        byte[] buffer = new byte[4096];
        UniversalDetector detector = new UniversalDetector(null);

        try (FileInputStream fis = new FileInputStream(dbfFile)) {
            int nread;
            while ((nread = fis.read(buffer)) > 0 && !detector.isDone()) {
                detector.handleData(buffer, 0, nread);
            }
        }

        detector.dataEnd();
        String detectedCharset = detector.getDetectedCharset();

        if (detectedCharset == null) {
            return null;
        }

        switch (detectedCharset.toUpperCase()) {
            case "UTF-8":
                return CharsetEnum.UTF8;
            case "WINDOWS-1250":
            case "CP1250":
                return CharsetEnum.WINDOWS;
            default:
                return null;
        }
    }

    public static CharsetEnum detectEncoding(File dbfFile) throws IOException {
        try (InputStream inputStream = new FileInputStream(dbfFile)) {
            DBFReader reader = new DBFReader(inputStream);
            reader.setCharactersetName("ISO-8859-1"); // tymczasowe, tylko do odczytu bajtów

            StringBuilder textSample = new StringBuilder();
            Object[] row;

            while ((row = reader.nextRecord()) != null) {
                for (Object field : row) {
                    if (field instanceof String) {
                        textSample.append((String) field).append(" ");
                    }
                }
            }

            byte[] sampleBytes = textSample.toString().getBytes("ISO-8859-1");
            UniversalDetector detector = new UniversalDetector(null);
            detector.handleData(sampleBytes, 0, sampleBytes.length);
            detector.dataEnd();

            String detectedCharset = detector.getDetectedCharset();
            switch (detectedCharset.toUpperCase()) {
                case "UTF-8":
                    return CharsetEnum.UTF8;
                case "WINDOWS-1250":
                case "WINDOWS-1252":
                case "CP1250":
                    return CharsetEnum.WINDOWS;
                default:
                    return null;
            }
        }
    }

}
