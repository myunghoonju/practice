package practice.others.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import practice.others.cache.domain.OtherColumns;

import javax.annotation.Nullable;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import javax.print.attribute.standard.MediaSize;
import java.util.UUID;

@Slf4j
public class Converters {

    @Converter
    public static class CryptoConverter implements AttributeConverter<String, String> {

        @Override
        public String convertToDatabaseColumn(String attribute) {
            return "converted attribute";
        }

        @Override
        public String convertToEntityAttribute(String dbData) {
            return dbData;
        }
    }

    @Converter
    public static class InfoConverter implements AttributeConverter<String, String> {

        @Override
        public String convertToDatabaseColumn(String attribute) {
            return "InfoConverter attribute" + Math.random();
        }

        @Override
        public String convertToEntityAttribute(String dbData) {
            return dbData;
        }
    }

    @Converter
    public static class OtherConverter implements AttributeConverter<OtherColumns, String> {

        @Override
        public String convertToDatabaseColumn(OtherColumns attribute) {
            if (attribute == null) {
                return null;
            }

            return attribute.getOtherColumn();
        }

        @Override
        public OtherColumns convertToEntityAttribute(String dbData) {
            return OtherColumns.builder().otherColumn(dbData).build();
        }
    }
}
