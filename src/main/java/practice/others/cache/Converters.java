package practice.others.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import practice.others.multipleDb.domain.OtherColumns;
import practice.others.multipleDb.domain.info.Information;

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

    @Converter
    public static class JsonConverter implements AttributeConverter<Object, String> {

        private static final ObjectMapper mapper = new ObjectMapper();

        @Override
        public String convertToDatabaseColumn(Object attribute) {
            try {
                return mapper.writeValueAsString(attribute);
            } catch (Exception e) {
                throw new RuntimeException("JsonConverter.convertToDatabaseColumn ", e);
            }
        }

        @Override
        public Object convertToEntityAttribute(String dbData) {
            try {
                Information information = mapper.readValue(dbData, Information.class);
                return information;
            } catch (Exception e) {
                throw new RuntimeException("JsonConverter.convertToEntityAttribute", e);
            }
        }
    }
}
