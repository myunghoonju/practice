package practice.others.cache;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
}
